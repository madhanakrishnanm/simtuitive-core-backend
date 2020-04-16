package com.simtuitive.core.oauth2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

public class EnhancedAuthenticationKeyGenerator extends DefaultAuthenticationKeyGenerator {

	private final String KEY_SUPER_KEY = "key_super_key";

	@Override
	public String extractKey(final OAuth2Authentication authentication) {

		final Map<String, String> values = new LinkedHashMap<>(2);
		System.out.println("value veera" + authentication.getDetails().toString());
		final Map<String, String> customvalues = (Map<String, String>) authentication.getDetails();

		if (customvalues != null) {
			String superKey = customvalues.get(KEY_SUPER_KEY);
			if (StringUtils.isNotEmpty(superKey)) {
				System.out.println("superKeyVeera" + superKey);
				values.put(KEY_SUPER_KEY, superKey);
			}
			else {
				System.out.println("uniqueId is null or empty");
			}
		}
		else {
			System.out.println("uniqueId is not set on authentication");
		}

		return generateKey(values);
	}
}
