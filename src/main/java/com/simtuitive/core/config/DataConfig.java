package com.simtuitive.core.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoClientOptionsFactoryBean;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories("com.simtuitive.core.repository")
public class DataConfig extends AbstractMongoConfiguration {

	private static final String uri = "mongodb://simtuitivedev:cYxhwHNjJpmLdh7J@52.66.197.93:27017/simtuitive?authSource=admin&authMechanism=SCRAM-SHA-1";
//	@Value("${spring.data.mongodb.uri}")
//    private String uri;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Override
	public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
		return super.mongoMappingContext();
	}

	@Override
	protected String getDatabaseName() {
		return database;
	}

	public MongoClientOptions mongoClientOptions() {
		try {
			final MongoClientOptionsFactoryBean bean = new MongoClientOptionsFactoryBean();
			bean.setSocketKeepAlive(true);
			bean.afterPropertiesSet();
			return bean.getObject();
		} catch (final Exception e) {
			throw new BeanCreationException(e.getMessage(), e);
		}
	}

	@Override
	public com.mongodb.MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new com.mongodb.MongoClient(new MongoClientURI(uri, mongoClientOptions().builder()));
	}
}
