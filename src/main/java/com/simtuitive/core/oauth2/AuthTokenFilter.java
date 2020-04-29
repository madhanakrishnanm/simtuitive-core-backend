package com.simtuitive.core.oauth2;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.simtuitive.core.common.Constants;
import com.simtuitive.core.config.RedisConfiguration;
import com.simtuitive.core.util.TokenUtil;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	RedisConfiguration redis;
	@Value("${secret}")
	private String secret;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	private UserDetailsService customUserDetailsService;

	public AuthTokenFilter(UserDetailsService userDetailsService) {
		this.customUserDetailsService = userDetailsService;
	}

	public TokenStore redisTokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			String clientToken = parseJwt(request);//
			System.out.println("clientToken::"+clientToken);
			if (clientToken != null && TokenUtil.validate(clientToken, secret)) {
				System.out.println("validateclientToken::"+clientToken);
				String clientTrueToken = TokenUtil.getToken(clientToken);//
				System.out.println("clientTrueToken::"+clientTrueToken);
				Map<?, ?> newtoken = (Map<?, ?>) redis.redisTemplate().opsForHash().get(clientTrueToken,
						clientTrueToken);
				String truetoken = (String) newtoken.get(Constants.STR_AUTH_TOKEN);//
				System.out.println("truetoken::"+truetoken);
				String username = (String) newtoken.get(Constants.STR_AUTH_EMAIL);
				if (truetoken.matches(clientTrueToken)) {
					UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					throw new InvalidTokenException(clientTrueToken);
				}

			}
		} catch (Exception e) {
			throw new InvalidRequestException(request.toString());
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(Constants.STR_AUTH_AUTHORIZATION);

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(Constants.STR_AUTH_BEARER)) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}

}
