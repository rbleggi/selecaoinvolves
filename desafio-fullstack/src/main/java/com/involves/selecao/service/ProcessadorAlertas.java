package com.involves.selecao.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	private static final String URL_PESQUISAS = "http://selecao-involves.agilepromoter.com/pesquisas";
	private static final String MSG_RUPTURA_DETECTADA = "Ruptura detectada!";
	private static final String MSG_PRECO_ABAIXO_DO_ESTIPULADO = "Preço abaixo do estipulado!";
	private static final String MSG_PRODUTO_AUSENTE_NA_GONDOLA = "Produto ausente na gondola";
	private static final String MSG_PRECO_ACIMA_DO_ESTIPULADO = "Preço acima do estipulado!";
	private static final String MSG_SHARE = "%Share";
	private static final String MSG_QUAL_O_PRECO_DO_PRODUTO = "Qual o preço do produto?";
	private static final String MSG_QUAL_A_SITUACAO_DO_PRODUTO = "Qual a situação do produto?";
	private static final String MSG_PARTICIPACAO_INFERIOR_ESTIPULADO = "Participação inferior ao estipulado";
	private static final String MSG_PARTICIPACAO_SUPERIOR_ESTIPULADO = "Participação superior ao estipulado";

	@Autowired
	private AlertaGateway gateway;

	public void processa() throws IOException {
		Pesquisa[] ps = this.getPesquisasFromJSON();
		for (int i = 0; i < ps.length; i++) {
			for (int j = 0; j < ps[i].getRespostas().size(); j++) {

				Resposta resposta = ps[i].getRespostas().get(j);
				String produto = ps[i].getProduto();
				String pontoVenda = ps[i].getPonto_de_venda();
				String pergunta = resposta.getPergunta();
				switch (pergunta) {
				case MSG_QUAL_A_SITUACAO_DO_PRODUTO:
					criarAlertaPerguntaSituacaoProduto(resposta, pontoVenda, produto);
					break;

				case MSG_QUAL_O_PRECO_DO_PRODUTO:
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(ps[i].getPreco_estipulado());
					if (precoColetado != precoEstipulado)
						criarAlertaPerguntaPrecoProduto(precoColetado, precoEstipulado, pontoVenda, produto);
					break;

				case MSG_SHARE:
					int participacaoColetado = Integer.parseInt(resposta.getResposta());
					int participacaoEstipulada = Integer.parseInt(ps[i].getParticipacao_estipulada());
					if (participacaoColetado != participacaoEstipulada)
						criarAlertaPerguntaShare(participacaoColetado, participacaoEstipulada, pontoVenda, produto);
					break;

				default:
					break;
				}
			}
		}
	}

	private void criarAlertaPerguntaSituacaoProduto(Resposta resposta, String pontoVenda, String produto) {
		if (resposta.getResposta().equals(MSG_PRODUTO_AUSENTE_NA_GONDOLA)) {
			Alerta alerta = new Alerta(pontoVenda, MSG_RUPTURA_DETECTADA, produto, 1, null);
			gateway.salvar(alerta);
		}
	}

	private void criarAlertaPerguntaPrecoProduto(int precoColetado, int precoEstipulado, String pontoVenda, String produto) {
		Integer flTipo = null;
		String descricao = null;
		int margem = precoEstipulado - precoColetado;
		if (precoColetado > precoEstipulado) {
			descricao = MSG_PRECO_ACIMA_DO_ESTIPULADO;
			flTipo = 2;
		} else if (precoColetado < precoEstipulado) {
			descricao = MSG_PRECO_ABAIXO_DO_ESTIPULADO;
			flTipo = 3;
		}
		// PODE SALVAR UM ALERTA PASSANDO FLTIPO NULL?
		Alerta alerta = new Alerta(pontoVenda, descricao, produto, flTipo, margem);
		gateway.salvar(alerta);
	}

	private void criarAlertaPerguntaShare(int participacaoColetado, int participacaoEstipulada, String pontoVenda, String produto) {
		Integer flTipo = null;
		String descricao = null;
		int margem = participacaoEstipulada - participacaoColetado;
		if (participacaoColetado < participacaoEstipulada) {
			descricao = MSG_PARTICIPACAO_INFERIOR_ESTIPULADO;
			flTipo = 4;
		} else if (participacaoColetado > participacaoEstipulada) {
			descricao = MSG_PARTICIPACAO_SUPERIOR_ESTIPULADO;
			flTipo = 5;
		}
		Alerta alerta = new Alerta(pontoVenda, descricao, produto, flTipo, margem);
		gateway.salvar(alerta);
	}

	private Pesquisa[] getPesquisasFromJSON() throws IOException {
		URL url = new URL(URL_PESQUISAS);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		// TODO -- remover
		String retorno = "[{\"id\":\"1\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"João\",\"ponto_de_venda\":\"Angel One Capoeiras\",\"produto\":\"Ovo de Pascoa Kinder 48\",\"respostas\":[{\"pergunta\":\"Qual a situação do produto?\",\"resposta\":\"Produto está na gondola\"}]},{\"id\":\"2\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"José\",\"ponto_de_venda\":\"Padaria do seu João\",\"produto\":\"Ovo de Pascoa Kinder 48\",\"preco_estipulado\":\"100\",\"respostas\":[{\"pergunta\":\"Qual o preço do produto?\",\"resposta\":\"95\"}]},{\"id\":\"3\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"Malaquias\",\"ponto_de_venda\":\"Padaria do Alemão\",\"produto\":\"Ovo de Pascoa Barbie 48\",\"preco_estipulado\":\"100\",\"respostas\":[{\"pergunta\":\"Qual o preço do produto?\",\"resposta\":\"105\"}]},{\"id\":\"4\",\"rotulo\":\"Acompanhamento de evolução dos refrigerantes\",\"notificante\":\"Malaquias\",\"ponto_de_venda\":\"Padaria do Alemão\",\"categoria\":\"Refrigerantes\",\"participacao_estipulada\":\"50\",\"respostas\":[{\"pergunta\":\"%Share\",\"resposta\":\"48\"}]},{\"id\":\"5\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"Malaquias\",\"ponto_de_venda\":\"Angel One Capoeiras\",\"produto\":\"Ovo de Pascoa Diamante Negro 48\",\"respostas\":[{\"pergunta\":\"Qual a situação do produto?\",\"resposta\":\"Produto ausente na gondola\"}]},{\"id\":\"6\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"João\",\"ponto_de_venda\":\"Angel One Capoeiras\",\"produto\":\"Ovo de Pascoa Laka 48\",\"respostas\":[{\"pergunta\":\"Qual a situação do produto?\",\"resposta\":\"Produto ausente na gondola\"}]},{\"id\":\"7\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"João\",\"ponto_de_venda\":\"Angel One Capoeiras\",\"produto\":\"Ovo de Pascoa NeymarJr 48\",\"respostas\":[{\"pergunta\":\"Qual a situação do produto?\",\"resposta\":\"Produto ausente na gondola\"}]},{\"id\":\"8\",\"rotulo\":\"Campanha de pascoa\",\"notificante\":\"João\",\"ponto_de_venda\":\"Angel One Capoeiras\",\"produto\":\"Ovo de Pascoa Crunch 48\",\"respostas\":[{\"pergunta\":\"Qual a situação do produto?\",\"resposta\":\"Produto ausente na gondola\"}]},{\"id\":\"9\",\"rotulo\":\"Acompanhamento de evolução dos refrigerantes\",\"notificante\":\"Paulo\",\"ponto_de_venda\":\"Padaria do Alemão\",\"categoria\":\"Sabonetes\",\"participacao_estipulada\":\"25\",\"respostas\":[{\"pergunta\":\"%Share\",\"resposta\":\"27\"}]},{\"id\":\"10\",\"rotulo\":\"Acompanhamento de evolução dos refrigerantes\",\"notificante\":\"Paulo\",\"ponto_de_venda\":\"Padaria do Alemão\",\"categoria\":\"Shampoo\",\"participacao_estipulada\":\"30\",\"respostas\":[{\"pergunta\":\"%Share\",\"resposta\":\"10\"}]}]";

		Gson gson = new Gson();
		Pesquisa[] ps = gson.fromJson(retorno, Pesquisa[].class);
		return ps;
	}

}
