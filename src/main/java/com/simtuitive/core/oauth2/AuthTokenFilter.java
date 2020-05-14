package com.simtuitive.core.oauth2;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simtuitive.core.common.Constants;
import com.simtuitive.core.config.RedisConfiguration;
import com.simtuitive.core.globalexception.BadArgumentException;
import com.simtuitive.core.model.SessionInfo;
import com.simtuitive.core.service.CustomUserDetailsServiceImpl;
import com.simtuitive.core.util.TokenUtil;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	RedisConfiguration redis;
	@Value("${secret}")
	private String secret;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private CustomUserDetailsServiceImpl customuserdetail;
	
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
		
		String initrole=null;
		String username=null;
		try {			
			String clientToken = parseJwt(request);//
			System.out.println("clientToken::" + clientToken);
			System.out.println("Changes for checking");			
			System.out.println("Changes for query string"+request.getTrailerFields().toString());
			System.out.println("Changes for query string"+request.getHeader("username"));
			System.out.println("Changes for query string"+request.getParameter("username"));
			
			System.out.println("Changes for query string"+request.getServletContext().getRequestCharacterEncoding());
			System.out.println("Changes for query string"+request.getHeaderNames().toString());
			System.out.println("Changes for query string"+request.getRequestURI().toString());
			if(!request.getParameter("username").isEmpty()&&clientToken==null) {
			Boolean isSameUserRoleInLoggedIn = validateSameUser(request);
			if (isSameUserRoleInLoggedIn) {
				System.out.println("sameuser logged in");//DuplicateSessionException
			}			
			}
			
			if (clientToken != null && TokenUtil.validate(clientToken, secret)) {				
				String clientTrueToken = TokenUtil.getToken(clientToken);//				
				Map<?, ?> newtoken = (Map<?, ?>) redis.redisTemplate().opsForHash().get(clientTrueToken,
						clientTrueToken);
				if(newtoken!=null) { 
				String truetoken = (String) newtoken.get(Constants.STR_AUTH_TOKEN);//
				System.out.println("truetoken::" + truetoken);
				username = (String) newtoken.get(Constants.STR_AUTH_EMAIL);
				initrole = customuserdetail.getUserDetails(username);
				String sessionkey=username+initrole;
				Map<?, ?> sessioninfo = (Map<?, ?>) redis.redisTemplate().opsForHash().get(sessionkey, sessionkey);
				Long time = (Long) newtoken.get("expirationTime");
				Long sessiontime=(Long) sessioninfo.get("sessionCreatedTime");
				Long sessiontime1=(Long) sessioninfo.get("sessionExpiryTime");
				Date expirationTime = new java.util.Date(time);
				Date sessionexpiretime= new java.util.Date(sessiontime1);
				System.out.println("welcome sessionexpiretime"+sessionexpiretime);		
				Date sessioncreatedtime = new java.util.Date(sessiontime);
				System.out.println("welcome sessioncreatedtime"+sessioncreatedtime);
				Date now = new Date();
				System.out.println("now"+now);
				if (truetoken.matches(clientTrueToken)) {
					if (now.after(expirationTime)) {
						System.out.println("cominghere");
						throw new InvalidTokenException("Token expired");
					} else {
						if(now.after(sessioncreatedtime)&&now.before(sessionexpiretime)) {
							System.out.println("coming session here");
							UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
							System.out.println("userDetails"+userDetails.getUsername());
							UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(authentication);
							updateinRedis(username);
						}else {
							throw new InvalidTokenException("session Time out by token");
						}						
					}
				} else {
					throw new InvalidTokenException("Invalid Token "+clientToken);
				}
			}
				else {
					throw new InvalidTokenException("Invalid Token "+clientToken);
				}

			} else {
//				Boolean isSameUserRoleInLoggedIn = validateSameUser(request);
//				if (isSameUserRoleInLoggedIn) {
//					System.out.println("sameuser logged in");//DuplicateSessionException
//					
//				} else {
//					//need to check time out//proceed further
//					System.out.println("veryfirst login");
//				}
			}
		} catch (BadArgumentException e) {
			throw new BadArgumentException(e.getMessage());
		}
		catch (InvalidTokenException e) {
			throw new InvalidTokenException(e.getMessage());
		}
		
		filterChain.doFilter(request, response);
		System.out.println("Changes for after filter string"+request.getParameter("username"));
	}

	private void updateinRedis(String username) {
		// TODO Auto-generated method stub
				System.out.println("updated in redis method");
				String initrole = customuserdetail.getUserDetails(username);			
				String key = username + initrole;
				Map<?, ?> sessioninfo = (Map<?, ?>) redis.redisTemplate().opsForHash().get(key, key);
				String sessionid=(String)sessioninfo.get("sessionId");
				Date createdTime=new Date();
				Date SessionExpire=new Date((System.currentTimeMillis()+300000L));
				SessionInfo sessioninfoupdate=new SessionInfo(username, initrole, sessionid, createdTime, createdTime,SessionExpire);
				Map<?, ?> ruleHash1 = new ObjectMapper().convertValue(sessioninfoupdate, Map.class);
				redis.redisTemplate().opsForValue().getOperations().delete(key);//deleting		
				redis.redisTemplate().opsForHash().put(key,key,ruleHash1);//adding				
				Map<?, ?> sessioninfoafterupdate = (Map<?, ?>) redis.redisTemplate().opsForHash().get(key, key);
				System.out.println("sessioninfoafterupdate"+sessioninfoafterupdate);
	}

	private Boolean validateSameUser(HttpServletRequest request) {
		String initusername = request.getParameter("username");
		String initrole = customuserdetail.getUserDetails(initusername);			
		String key = initusername + initrole;
		Map<?, ?> sessioninfo = (Map<?, ?>) redis.redisTemplate().opsForHash().get(key, key);
		if(sessioninfo!=null&&!sessioninfo.isEmpty()&&(initrole.equalsIgnoreCase("Client")||initrole.equalsIgnoreCase("Learner"))) {
		String sessionuser=(String) sessioninfo.get("emailId");		
		Long time=(Long) sessioninfo.get("sessionCreatedTime");
		Long time1=(Long) sessioninfo.get("sessionExpiryTime");//shortway adding 30 min created
		Date sessionexpiretime= new java.util.Date(time1);
		System.out.println("welcome sessionexpiretime"+sessionexpiretime);		
		Date sessioncreatedtime = new java.util.Date(time);
		System.out.println("welcome sessioncreatedtime"+sessioncreatedtime);
		Date now = new Date();
		if (sessionuser.equalsIgnoreCase(initusername)) {
			System.out.println("sessioncreatedtime"+sessioncreatedtime);
			System.out.println("true in same name"+sessionuser+"inituser"+initusername);
			if(now.after(sessioncreatedtime)&&now.before(sessionexpiretime)) {
				throw new BadArgumentException(initrole+" User "+initusername+"is already in logged in");
			}else {	//previous session expired trying for re-login			
				return false;	
			}
		}
		else {
			return false;	
		}
		}else {
			return false;	
		}	
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(Constants.STR_AUTH_AUTHORIZATION);

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(Constants.STR_AUTH_BEARER)) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}

}
