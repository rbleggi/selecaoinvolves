package com.involves.selecao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.service.AlertasService;
import com.involves.selecao.service.ProcessadorAlertasService;
import com.involves.selecao.service.dto.AlertaDTO;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private AlertasService alertaService;
	
	@Autowired
	private ProcessadorAlertasService processadorService;
	
	@GetMapping
    public List<AlertaDTO> alertas(AlertaDTO alertaDTO) {
		return alertaService.buscar(alertaDTO);
    }
	
	@GetMapping("/processar")
    public void processar() {
		processadorService.processa();
    }
	
	@DeleteMapping
	public void limparBase(AlertaDTO alertaDTO) {
		alertaService.remover(alertaDTO);
	}
}
