package com.involves.selecao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.domain.Alerta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class BuscaAlertasService {
	
	@Autowired
	private AlertaGateway gateway;
	
	public List<Alerta> buscarTodos() {
		return gateway.buscarTodos();
	}

}
