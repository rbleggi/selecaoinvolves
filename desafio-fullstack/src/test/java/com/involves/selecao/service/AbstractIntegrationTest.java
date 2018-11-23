package com.involves.selecao.service;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import de.flapdoodle.embed.mongo.distribution.Version;

@ActiveProfiles("MongoTest-test")
public class AbstractIntegrationTest {
	
	@Autowired
	private MongoDbFactory mongoDbFactory;
	private MongodForTestsFactory factory;
	
	MongoDatabase database;

	@Before
	public void setup() throws Exception {
		factory = MongodForTestsFactory.with(Version.Main.PRODUCTION);
		MongoClient mongo = factory.newMongo();
		database = mongo.getDatabase("teste");
		Mockito.when(mongoDbFactory.getDb()).thenReturn(database);
	}

	@After
	public void teardown() throws Exception {
		if (factory != null)
			factory.shutdown();
	}
}
