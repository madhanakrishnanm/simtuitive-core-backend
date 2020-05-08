package com.simtuitive.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

import com.simtuitive.core.model.PasswordResetToken;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration extends AbstractSecurityWebApplicationInitializer {
	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;
	@Value("${spring.redis.port}")
	private int REDIS_PORT;
	
	@Value("${spring.redis.password}")
	private String REDIS_PASSWORD;

	@Bean
	protected JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
		configuration.setPassword(REDIS_PASSWORD);
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public JedisPool getConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		JedisPool jedisPool = new JedisPool();		
		config.setMaxIdle(30);
		config.setMinIdle(10);		
		config.setTestOnBorrow(true);
		
		jedisPool = new JedisPool(config, REDIS_HOSTNAME, REDIS_PORT, 50000);
		
		return jedisPool;
	}

	@Bean
	public RedisTemplate<String, PasswordResetToken> redisTemplate() {
		final RedisTemplate<String, PasswordResetToken> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}

}