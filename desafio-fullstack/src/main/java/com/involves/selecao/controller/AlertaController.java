package com.involves.selecao.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.service.dto.AlertaDTO;
import com.involves.selecao.service.impl.BuscaAlertasServiceImpl;
import com.involves.selecao.service.impl.ProcessadorAlertasImpl;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private BuscaAlertasServiceImpl buscaAlertasService;
	
	@Autowired
	private ProcessadorAlertasImpl processador;
	
	@GetMapping
    public List<AlertaDTO> alertas() {
		return buscaAlertasService.buscarTodos();
    }
	
	@GetMapping("/processar")
    public void processar() {
		try {
			processador.processa();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
