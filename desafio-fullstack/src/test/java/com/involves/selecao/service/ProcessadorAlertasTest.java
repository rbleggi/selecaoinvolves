package com.involves.selecao.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.involves.selecao.gateway.mongo.MongoDbFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProcessadorAlertasTest {

	@Autowired
	private ProcessadorAlertas processador;

	@Autowired
	private MongoDbFactory mongoFactory;
	

	@Before
	public void setUp() {
//		mongoFactory.getDb().getCollection("Alertas").
	}

	@Test
	public void deveAdicionarVeiculo() throws IOException {
		
		processador.processa();
		
	}
	
}
