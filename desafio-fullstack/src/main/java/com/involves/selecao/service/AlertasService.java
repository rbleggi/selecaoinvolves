package com.involves.selecao.service;

import java.util.List;

import com.involves.selecao.service.dto.AlertaDTO;

public interface AlertasService {
	
	public List<AlertaDTO> buscar(AlertaDTO alertaDTO);
	public void remover(AlertaDTO alertaDTO);

}
