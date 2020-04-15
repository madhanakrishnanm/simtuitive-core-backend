package com.simtuitive.core.oauth2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simtuitive.core.config.RedisConfiguration;
import com.simtuitive.core.model.PasswordResetToken;
import com.simtuitive.core.service.CustomUserDetailsServiceImpl;
import com.simtuitive.core.util.TokenUtil;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	RedisConfiguration redis;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public TokenStore tokenStore() {

		TokenStore tokenstore = new RedisTokenStore(redisConnectionFactory);
		((RedisTokenStore) tokenstore).setAuthenticationKeyGenerator(authenticationKeyGenerator());

		return tokenstore;

	}

	@Bean
	public AuthenticationKeyGenerator authenticationKeyGenerator() {
		return new EnhancedAuthenticationKeyGenerator();

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();
	}

	@Value("${clientId}")
	private String clientid;
	@Value("${secret}")
	private String secret;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientid).secret(passwordEncoder.encode(secret))
				.authorizedGrantTypes("password", "client_credentials", "refresh_token").scopes("all")
				.accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(86400);

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		Map<String, CorsConfiguration> corsConfigMap = new HashMap<>();
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));

		corsConfigMap.put("/oauth/token", config);
		endpoints.getFrameworkEndpointHandlerMapping().setCorsConfigurations(corsConfigMap);
		endpoints.tokenEnhancer(tokenEnhancer()).tokenStore(tokenStore()).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();

		tokenServices.setTokenStore(tokenStore());

		return tokenServices;
	}

	public class CustomTokenEnhancer implements TokenEnhancer {
		String stronger_salt = BCrypt.gensalt(12);
		String value, TokenEnc, refreshTokenEnc, truerefreshtoken = null;
		final Map<String, Object> additionalInfo = new HashMap<>();
		PasswordResetToken redistoken = null;
		StringBuilder sb = new StringBuilder();

		@Override
		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			User user = (User) authentication.getPrincipal();
			System.out.println("accessToken::" + accessToken);
			System.out.println("stronger_salt::" + stronger_salt);
			value = accessToken.toString();
			sb.append(value);
			sb.append(stronger_salt);
			System.out.println("token with salt::" + sb.toString());
			TokenEnc = TokenUtil.encrypt(sb.toString(), secret);
			System.out.println("tokenEncrypted::" + TokenEnc);
			System.out.println("stronger_salt" + stronger_salt);
			truerefreshtoken = accessToken.getRefreshToken().getValue() + stronger_salt;
			refreshTokenEnc = TokenUtil.encrypt(truerefreshtoken, secret);
			additionalInfo.put("role", userDetailsService.getUserDetails(user.getUsername()));
			// Experienced to add extra salt and true token--but client can able to see
			// that//fails our case
			OAuth2AccessToken accessTokenGen = new DefaultOAuth2AccessToken(TokenEnc);
			OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(refreshTokenEnc);
			((DefaultOAuth2AccessToken) accessTokenGen).setAdditionalInformation(additionalInfo);
			((DefaultOAuth2AccessToken) accessTokenGen).setRefreshToken(refreshToken);

			// create token info class have the token information manually by me
			redistoken = new PasswordResetToken(user.getUsername(), accessToken.toString(), stronger_salt,
					new Date(System.currentTimeMillis() + 3600));
			Map<?, ?> ruleHash = new ObjectMapper().convertValue(redistoken, Map.class);
			System.out.println("Ruleshash:::" + ruleHash.toString());
			// Publishing in redis
			redis.redisTemplate().opsForHash().put(accessToken.toString(), accessToken.toString(), ruleHash);

//		 OAuth2AccessToken accessTokenOutput = redisTokenStore().readAccessToken(accessTokenGen.getValue());
//		 System.out.println("Value retrive"+accessTokenOutput.getRefreshToken());
			return accessTokenGen;
		}
	}

}
