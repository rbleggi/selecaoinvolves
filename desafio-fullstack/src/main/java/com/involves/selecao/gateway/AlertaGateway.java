package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.domain.Alerta;

public interface AlertaGateway {
	
	public void salvar(Alerta alerta);

	public List<Alerta> buscar(Alerta alerta);
	
	public void remover(Alerta alerta);
}
