package com.involves.selecao.service.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AlertaDTO {

	private String id;
	private String ponto_de_venda;
	private String descricao;
	private String produto;
	private String categoria;
	private Integer fl_tipo;
	private Integer margem;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private ZonedDateTime data_hora_cadastro;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPonto_de_venda() {
		return ponto_de_venda;
	}

	public void setPonto_de_venda(String ponto_de_venda) {
		this.ponto_de_venda = ponto_de_venda;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getFl_tipo() {
		return fl_tipo;
	}

	public void setFl_tipo(Integer fl_tipo) {
		this.fl_tipo = fl_tipo;
	}

	public Integer getMargem() {
		return margem;
	}

	public void setMargem(Integer margem) {
		this.margem = margem;
	}

	public ZonedDateTime getData_hora_cadastro() {
		return data_hora_cadastro;
	}

	public void setData_hora_cadastro(ZonedDateTime data_hora_cadastro) {
		this.data_hora_cadastro = data_hora_cadastro;
	}

}
