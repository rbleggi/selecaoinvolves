package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.domain.Alerta;

public interface AlertaGateway {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodos();
}
