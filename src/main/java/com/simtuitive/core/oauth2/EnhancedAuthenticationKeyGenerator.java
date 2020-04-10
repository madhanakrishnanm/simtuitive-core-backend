package com.simtuitive.core.oauth2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

public class EnhancedAuthenticationKeyGenerator extends DefaultAuthenticationKeyGenerator {

	public static final String PARAM_CLIENT_INSTANCE_ID = "client_instance_id";

	private final String KEY_SUPER_KEY = "key_super_key";
	private static final String KEY_CLIENT_INSTANCE_ID = PARAM_CLIENT_INSTANCE_ID;

	@Override
	public String extractKey(final OAuth2Authentication authentication) {
		String superKey = UUID.randomUUID().toString();
		final OAuth2Request authorizationRequest = authentication.getOAuth2Request();
		final Map<String, String> requestParameters = authorizationRequest.getRequestParameters();
		final Map<String, String> values = new LinkedHashMap<>(2);
		final String clientInstanceId = requestParameters != null ? requestParameters.get(PARAM_CLIENT_INSTANCE_ID)
				: null;
		if (clientInstanceId == null || clientInstanceId.length() == 0) {
			values.put(KEY_SUPER_KEY, superKey);
//			return superKey;
		}else {
			values.put(KEY_SUPER_KEY, superKey);
			values.put(KEY_CLIENT_INSTANCE_ID, clientInstanceId);
		}
		
		values.put(KEY_SUPER_KEY, superKey);
		values.put(KEY_CLIENT_INSTANCE_ID, clientInstanceId);

		return generateKey(values);
	}
}
