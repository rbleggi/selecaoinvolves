package com.involves.selecao.service;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.client.FindIterable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessadorAlertasTest extends AbstractIntegrationTest {

	@Autowired
	private ProcessadorAlertasService service;
	
	@Test
	public void teste() {
		service.processa();
		FindIterable<Document> find = database.getCollection("Alertas").find();
		Assert.assertTrue(find.first().getString("descricao").equals("Participação inferior ao estipulado"));
	}
}