package com.simtuitive.core.oauth2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simtuitive.core.config.RedisConfiguration;
import com.simtuitive.core.globalexception.BadArgumentException;
import com.simtuitive.core.model.PasswordResetToken;
import com.simtuitive.core.model.SessionInfo;
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
		endpoints.tokenEnhancer(tokenEnhancer()).tokenStore(tokenStore()).tokenServices(tokenServices())
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	@Bean
	@Primary
	public AuthorizationServerTokenServices tokenServices() {
		final SimtuitiveTokenServices tokenServices = new SimtuitiveTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setTokenEnhancer(tokenEnhancer());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setAccessTokenValiditySeconds(600);

		return tokenServices;
	}

	public class CustomTokenEnhancer implements TokenEnhancer {
		String stronger_salt = BCrypt.gensalt(12);
		String value, TokenEnc, refreshTokenEnc, truerefreshtoken = null;
		final Map<String, Object> additionalInfo = new HashMap<>();
		final Map<String, Object> additionalInfoClient = new HashMap<>();
		PasswordResetToken redistoken = null;
		SessionInfo sessioninfo=null;

		@Override
		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//			User user = (User) authentication.getPrincipal();
			if(validateSameUser(authentication.getPrincipal().toString())) {
				OAuth2AccessToken accessTokenGen = new DefaultOAuth2AccessToken("error");
				additionalInfoClient.put("error", "error");
				additionalInfoClient.put("message", "User already in logged in");
				((DefaultOAuth2AccessToken) accessTokenGen).setAdditionalInformation(additionalInfoClient);
				return accessTokenGen;
			}
			else {
			value = accessToken.getValue();
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			sb.append(BCrypt.gensalt(12));
			System.out.println("token with salt::" + sb.toString());
			TokenEnc = TokenUtil.encrypt(sb.toString(), secret);
			System.out.println("tokenEncrypted::" + TokenEnc);
			System.out.println("stronger_salt" + stronger_salt);
			truerefreshtoken = accessToken.getRefreshToken().getValue() + BCrypt.gensalt(12);
			refreshTokenEnc = TokenUtil.encrypt(truerefreshtoken, secret);
			String role=userDetailsService.getUserDetails(authentication.getPrincipal().toString());
			additionalInfo.put("role", role);
			Map<String, String> customvalues = (Map<String, String>) authentication.getDetails();
			additionalInfo.put("key_super_key", customvalues.get("key_super_key"));
			
			
			// Experienced to add extra salt and true token--but client can able to see
			// that//fails our case
			OAuth2AccessToken accessTokenGen = new DefaultOAuth2AccessToken(TokenEnc);
			OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(refreshTokenEnc);
			
			((DefaultOAuth2AccessToken) accessTokenGen).setRefreshToken(refreshToken);
			//((DefaultOAuth2AccessToken) accessTokenGen).setExpiration(accessToken.getExpiration());
			// create token info class have the token information manually by me
			Date createdtime=new Date();			
			redistoken = new PasswordResetToken(authentication.getPrincipal().toString(), accessToken.toString(), stronger_salt,
					accessToken.getExpiration(),createdtime);
			Map<?, ?> ruleHash = new ObjectMapper().convertValue(redistoken, Map.class);
			System.out.println("Ruleshash:::" + ruleHash.toString());
			// Publishing in redis
			Date SessionExpire=new Date((System.currentTimeMillis()+300000L));
			redis.redisTemplate().opsForHash().put(accessToken.toString(), accessToken.toString(), ruleHash);
			sessioninfo=new SessionInfo(authentication.getPrincipal().toString(), userDetailsService.getUserDetails(authentication.getPrincipal().toString()), customvalues.get("key_super_key"), createdtime, createdtime,SessionExpire);
			Map<?, ?> ruleHash1 = new ObjectMapper().convertValue(sessioninfo, Map.class);
			redis.redisTemplate().opsForHash().put(authentication.getPrincipal().toString()+role,authentication.getPrincipal().toString()+role,ruleHash1);
			Map<?, ?> sessioninfo = (Map<?, ?>) redis.redisTemplate().opsForHash().get(authentication.getPrincipal().toString()+role,
					authentication.getPrincipal().toString()+role);
			System.out.println("sessioninfo"+sessioninfo.toString());
			additionalInfo.put("sessionExpire", SessionExpire);
			((DefaultOAuth2AccessToken) accessTokenGen).setAdditionalInformation(additionalInfo);
//		 OAuth2AccessToken accessTokenOutput = redisTokenStore().readAccessToken(accessTokenGen.getValue());
//		 System.out.println("Value retrive"+accessTokenOutput.getRefreshToken());
			return accessTokenGen;
		}
		}
	}
	private Boolean validateSameUser(String initusername) {		 
		String initrole = userDetailsService.getUserDetails(initusername);			
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
				//throw new BadArgumentException(initrole+" User "+initusername+"is already in logged in");
				return true;
								
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
}
