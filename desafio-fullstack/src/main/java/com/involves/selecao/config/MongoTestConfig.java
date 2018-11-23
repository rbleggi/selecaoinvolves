package com.involves.selecao.config;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.involves.selecao.gateway.mongo.MongoDbFactory;

@Profile("MongoTest-test")
@Configuration
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class MongoTestConfig {
	
	@Bean
	@Primary
	public MongoDbFactory MongoDbFactory() {
		return Mockito.mock(MongoDbFactory.class);
	}
	
}
