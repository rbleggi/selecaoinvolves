package com.involves.selecao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.service.dto.AlertaDTO;
import com.involves.selecao.service.impl.BuscaAlertasServiceImpl;
import com.involves.selecao.service.impl.ProcessadorAlertasServiceImpl;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private BuscaAlertasServiceImpl buscaAlertasService;
	
	@Autowired
	private ProcessadorAlertasServiceImpl processador;
	
	@GetMapping
    public List<AlertaDTO> alertas(AlertaDTO alertaDTO) {
		return buscaAlertasService.buscarTodos(alertaDTO);
    }
	
	@GetMapping("/processar")
    public void processar() {
		processador.processa();
    }
}
