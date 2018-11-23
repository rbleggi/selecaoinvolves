package com.involves.selecao.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.converters.AlertaConverter;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.BuscaAlertasService;
import com.involves.selecao.service.dto.AlertaDTO;

@Service
public class BuscaAlertasServiceImpl implements BuscaAlertasService {
	
	@Autowired
	private AlertaGateway gateway;
	
	public List<AlertaDTO> buscarTodos(AlertaDTO alertaDTO) {
		return gateway.buscarTodos(AlertaConverter.convertToAlerta(alertaDTO)).stream().map(alerta -> AlertaConverter.convertToAlertaDTO(alerta)).collect(Collectors.toList());
	}

}
