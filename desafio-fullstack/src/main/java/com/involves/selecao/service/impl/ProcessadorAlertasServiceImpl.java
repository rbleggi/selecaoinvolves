package com.involves.selecao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.domain.Alerta;
import com.involves.selecao.domain.Pesquisa;
import com.involves.selecao.domain.Resposta;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.BuscaDePesquisasService;
import com.involves.selecao.service.ProcessadorAlertasService;

@Service
public class ProcessadorAlertasServiceImpl implements ProcessadorAlertasService {

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

	@Autowired
	private BuscaDePesquisasService buscaDePesquisasService;

	public void processa() {
		List<Pesquisa> pesquisasFromJSON = buscaDePesquisasService.getPesquisasFromJSON();
		pesquisasFromJSON.stream().forEach(pesquisa -> pesquisa.getRespostas().stream().forEach(resposta -> {
			String produto = pesquisa.getProduto();
			String pontoVenda = pesquisa.getPonto_de_venda();
			switch (resposta.getPergunta()) {
			case MSG_QUAL_A_SITUACAO_DO_PRODUTO:
				if (resposta.getResposta().equals(MSG_PRODUTO_AUSENTE_NA_GONDOLA))
					criarAlertaPerguntaSituacaoProduto(resposta, pontoVenda, produto);
				break;
			case MSG_QUAL_O_PRECO_DO_PRODUTO:
				int precoResposta = Integer.parseInt(resposta.getResposta());
				int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
				criarAlertaPerguntaPrecoProduto(precoResposta, precoEstipulado, pontoVenda, produto);
				break;
			case MSG_SHARE:
				int participacaoResposta = Integer.parseInt(resposta.getResposta());
				int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacao_estipulada());
				String categoria = pesquisa.getCategoria();
				criarAlertaPerguntaShare(participacaoResposta, participacaoEstipulada, pontoVenda, categoria);
				break;
			default:
				break;
			}
		}));
	}

	private void criarAlertaPerguntaSituacaoProduto(Resposta resposta, String pontoVenda, String produto) {
		gateway.salvar(new Alerta(pontoVenda, MSG_RUPTURA_DETECTADA, produto, null, 1, null));
	}

	private void criarAlertaPerguntaPrecoProduto(int precoColetado, int precoEstipulado, String pontoVenda, String produto) {
		int margem = precoEstipulado - precoColetado;
		if (precoColetado > precoEstipulado)
			gateway.salvar(new Alerta(pontoVenda, MSG_PRECO_ACIMA_DO_ESTIPULADO, produto, null, 2, margem));
		else if (precoColetado < precoEstipulado)
			gateway.salvar(new Alerta(pontoVenda, MSG_PRECO_ABAIXO_DO_ESTIPULADO, produto, null, 3, margem));
	}

	private void criarAlertaPerguntaShare(int participacaoColetado, int participacaoEstipulada, String pontoVenda, String categoria) {
		int margem = participacaoEstipulada - participacaoColetado;
		if (participacaoColetado < participacaoEstipulada)
			gateway.salvar(new Alerta(pontoVenda, MSG_PARTICIPACAO_INFERIOR_ESTIPULADO, null, categoria, 4, margem));
		else if (participacaoColetado > participacaoEstipulada)
			gateway.salvar(new Alerta(pontoVenda, MSG_PARTICIPACAO_SUPERIOR_ESTIPULADO, null, categoria, 5, margem));
	}

}
