package com.involves.selecao.alerta;

public class Alerta {
	
	private String pontoDeVenda;
	private String descricao;
	private String produto;
	private Integer flTipo;
	private Integer margem;
	
	public Alerta(){}

	public Alerta(String pontoDeVenda,String descricao,String produto,Integer flTipo,Integer margem){
		this.pontoDeVenda = pontoDeVenda;
		this.descricao = descricao;
		this.produto = produto;
		this.flTipo = flTipo;
		this.margem = margem;
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
	public Integer getFlTipo() {
		return flTipo;
	}
	public void setFlTipo(Integer flTipo) {
		this.flTipo = flTipo;
	}
	public Integer getMargem(){
		return margem;
	}
	public void setMargem(Integer margem){
		this.margem = margem;
	}
}
