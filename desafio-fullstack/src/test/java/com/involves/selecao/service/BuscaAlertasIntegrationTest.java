package com.involves.selecao.service;

import java.util.List;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.involves.selecao.config.AbstractIntegrationTest;
import com.involves.selecao.service.dto.AlertaDTO;
import com.mongodb.client.MongoCollection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuscaAlertasIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private BuscaAlertasService service;

	@Test
	public void deveBuscaraTodosAlertas() {
		MongoCollection<Document> collection = getDatabase().getCollection("Alertas");
		
		Document doc = new Document("ponto_de_venda", "Angel One Capoeiras")
                .append("descricao", "Campanha de pascoa")
                .append("tipo", 5)
                .append("margem", 3)
                .append("produto", "Carnes")
				.append("categoria", null);
		collection.insertOne(doc);
		Document doc2 = new Document("ponto_de_venda", "Angel One Capoeiras2")
				.append("descricao", "Campanha de pascoa2")
				.append("tipo", 2)
				.append("margem", 5)
				.append("produto", null)
				.append("categoria", "Bolacha");
		collection.insertOne(doc2);

		
		List<AlertaDTO> buscarTodos = service.buscarTodos(new AlertaDTO());

		Assert.assertTrue(buscarTodos.size() == 2);
		Assert.assertTrue(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 2));
		Assert.assertTrue(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 5));
	}
	
	@Test
	public void deveBuscaraAlertasFiltrandoTipo() {
		MongoCollection<Document> collection = getDatabase().getCollection("Alertas");
		
		Document doc = new Document("ponto_de_venda", "Angel One Capoeiras")
				.append("descricao", "Campanha de pascoa")
				.append("tipo", 5)
				.append("margem", 3)
				.append("produto", "Carnes")
				.append("categoria", null);
		collection.insertOne(doc);
		Document doc2 = new Document("ponto_de_venda", "Angel One Capoeiras2")
				.append("descricao", "Campanha de pascoa2")
				.append("tipo", 2)
				.append("margem", 5)
				.append("produto", null)
				.append("categoria", "Bolacha");
		collection.insertOne(doc2);
		Document doc3 = new Document("ponto_de_venda", "Angel One Capoeiras3")
				.append("descricao", "Campanha de pascoa3")
				.append("tipo", 2)
				.append("margem", 5)
				.append("produto", null)
				.append("categoria", "Bolacha");
		collection.insertOne(doc3);
		
		
		AlertaDTO alertaDTO = new AlertaDTO();
		alertaDTO.setFl_tipo(2);
		List<AlertaDTO> buscarTodos = service.buscarTodos(alertaDTO);
		
		Assert.assertTrue(buscarTodos.size() == 2);
		Assert.assertTrue(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 2));
		Assert.assertFalse(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 5));
	}
	
	@Test
	public void deveBuscaraAlertasFiltrandoPontoVenda() {
		MongoCollection<Document> collection = getDatabase().getCollection("Alertas");
		
		Document doc = new Document("ponto_de_venda", "Angel One Capoeiras")
				.append("descricao", "Campanha de pascoa")
				.append("tipo", 5)
				.append("margem", 3)
				.append("produto", "Carnes")
				.append("categoria", null);
		collection.insertOne(doc);
		Document doc2 = new Document("ponto_de_venda", "Ponto do pao")
				.append("descricao", "Campanha de pascoa2")
				.append("tipo", 4)
				.append("margem", 5)
				.append("produto", null)
				.append("categoria", "Bolacha");
		collection.insertOne(doc2);
		Document doc3 = new Document("ponto_de_venda", "Angel One Capoeiras3")
				.append("descricao", "Campanha de pascoa3")
				.append("tipo", 2)
				.append("margem", 5)
				.append("produto", null)
				.append("categoria", "Bolacha");
		collection.insertOne(doc3);
		
		
		AlertaDTO alertaDTO = new AlertaDTO();
		alertaDTO.setPonto_de_venda("one");
		List<AlertaDTO> buscarTodos = service.buscarTodos(alertaDTO);
		
		Assert.assertTrue(buscarTodos.size() == 2);
		Assert.assertTrue(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 2));
		Assert.assertTrue(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 5));
		Assert.assertFalse(buscarTodos.stream().anyMatch(alerta -> alerta.getFl_tipo() == 4));
	}
}