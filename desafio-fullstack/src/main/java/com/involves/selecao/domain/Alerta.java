package com.involves.selecao.domain;

import java.time.ZonedDateTime;

public class Alerta {

	private String id;
	private String pontoDeVenda;
	private String descricao;
	private String produto;
	private String categoria;
	private Integer flTipo;
	private Integer margem;
	private ZonedDateTime dataHoraCadastro;

	public Alerta() {
	}

	public Alerta(String pontoDeVenda, String descricao, String produto, String categoria, Integer flTipo, Integer margem) {
		this.pontoDeVenda = pontoDeVenda;
		this.descricao = descricao;
		this.produto = produto;
		this.categoria = categoria;
		this.flTipo = flTipo;
		this.margem = margem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPontoDeVenda() {
		return pontoDeVenda;
	}

	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
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

	public Integer getFlTipo() {
		return flTipo;
	}

	public void setFlTipo(Integer flTipo) {
		this.flTipo = flTipo;
	}

	public Integer getMargem() {
		return margem;
	}

	public void setMargem(Integer margem) {
		this.margem = margem;
	}

	public ZonedDateTime getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(ZonedDateTime dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

}
