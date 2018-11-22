package com.involves.selecao.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.domain.Alerta;
import com.involves.selecao.service.impl.BuscaAlertasService;
import com.involves.selecao.service.impl.ProcessadorAlertas;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private BuscaAlertasService buscaAlertasService;
	
	@Autowired
	private ProcessadorAlertas processador;
	
	@GetMapping
    public List<Alerta> alertas() {
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
