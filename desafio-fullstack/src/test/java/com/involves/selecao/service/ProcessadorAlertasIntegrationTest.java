package com.involves.selecao.service;

import java.util.Arrays;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.involves.selecao.config.AbstractIntegrationTest;
import com.involves.selecao.domain.Pesquisa;
import com.involves.selecao.domain.Resposta;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessadorAlertasIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private ProcessadorAlertasService service;

	@MockBean
	private BuscaDePesquisasService buscaDePesquisasService;

	@Test
	public void deveCadastrarAlertaPrecoInferior() {
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setRotulo("Campanha de pascoa");
		pesquisa.setNotificante("João");
		pesquisa.setPonto_de_venda("Angel One Capoeiras");
		pesquisa.setProduto("Angel One Capoeiras");
		pesquisa.setPreco_estipulado("100");
		Resposta resposta = new Resposta();
		resposta.setPergunta("Qual o preço do produto?");
		resposta.setResposta("95");
		pesquisa.setRespostas(Arrays.asList(resposta));

		Mockito.when(buscaDePesquisasService.getPesquisasFromJSON()).thenReturn(Arrays.asList(pesquisa));
		service.processa();
		Document first = getDatabase().getCollection("Alertas").find().first();
		Assert.assertTrue(first.getString("descricao").equals("Preço abaixo do estipulado!"));
		Assert.assertTrue(first.getInteger("tipo").equals(3));
		Assert.assertTrue(first.getInteger("margem").equals(5));
	}

	@Test
	public void deveCadastrarAlertaPrecoSuperior() {
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setRotulo("Campanha de pascoa");
		pesquisa.setNotificante("João");
		pesquisa.setPonto_de_venda("Angel One Capoeiras");
		pesquisa.setProduto("Angel One Capoeiras");
		pesquisa.setPreco_estipulado("50");
		Resposta resposta = new Resposta();
		resposta.setPergunta("Qual o preço do produto?");
		resposta.setResposta("95");
		pesquisa.setRespostas(Arrays.asList(resposta));

		Mockito.when(buscaDePesquisasService.getPesquisasFromJSON()).thenReturn(Arrays.asList(pesquisa));
		service.processa();
		Document first = getDatabase().getCollection("Alertas").find().first();
		Assert.assertTrue(first.getString("descricao").equals("Preço acima do estipulado!"));
		Assert.assertTrue(first.getInteger("tipo").equals(2));
		Assert.assertTrue(first.getInteger("margem").equals(-45));
	}

	@Test
	public void deveCadastrarAlertaParticipacaoInferior() {
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setRotulo("Campanha de pascoa");
		pesquisa.setNotificante("João");
		pesquisa.setPonto_de_venda("Angel One Capoeiras");
		pesquisa.setCategoria("Bolachas");
		pesquisa.setParticipacao_estipulada("50");
		Resposta resposta = new Resposta();
		resposta.setPergunta("%Share");
		resposta.setResposta("45");
		pesquisa.setRespostas(Arrays.asList(resposta));

		Mockito.when(buscaDePesquisasService.getPesquisasFromJSON()).thenReturn(Arrays.asList(pesquisa));
		service.processa();
		Document first = getDatabase().getCollection("Alertas").find().first();
		Assert.assertTrue(first.getString("descricao").equals("Participação inferior ao estipulado"));
		Assert.assertTrue(first.getInteger("tipo").equals(4));
		Assert.assertTrue(first.getInteger("margem").equals(5));
	}

	@Test
	public void deveCadastrarAlertaParticipacaoSuperior() {
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setRotulo("Campanha de pascoa");
		pesquisa.setNotificante("João");
		pesquisa.setPonto_de_venda("Angel One Capoeiras");
		pesquisa.setCategoria("Carnes");
		pesquisa.setParticipacao_estipulada("50");
		Resposta resposta = new Resposta();
		resposta.setPergunta("%Share");
		resposta.setResposta("55");
		pesquisa.setRespostas(Arrays.asList(resposta));

		Mockito.when(buscaDePesquisasService.getPesquisasFromJSON()).thenReturn(Arrays.asList(pesquisa));
		service.processa();
		Document first = getDatabase().getCollection("Alertas").find().first();
		Assert.assertTrue(first.getString("descricao").equals("Participação superior ao estipulado"));
		Assert.assertTrue(first.getInteger("tipo").equals(5));
		Assert.assertTrue(first.getInteger("margem").equals(-5));
	}
}