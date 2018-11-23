package com.involves.selecao.config;


import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.MongoDatabase;

import de.flapdoodle.embed.mongo.distribution.Version;

public abstract class AbstractIntegrationTest {
	
	@MockBean
	private MongoDbFactory mongoDbFactory;
	private MongodForTestsFactory factory;
	
	private MongoDatabase database;

	public MongoDatabase getDatabase() {
		return database;
	}
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		factory = MongodForTestsFactory.with(Version.Main.PRODUCTION);
		database = factory.newMongo().getDatabase("teste");
		Mockito.when(mongoDbFactory.getDb()).thenReturn(database);
	}

	@After
	public void teardown() throws Exception {
		if (factory != null)
			factory.shutdown();
	}
}
