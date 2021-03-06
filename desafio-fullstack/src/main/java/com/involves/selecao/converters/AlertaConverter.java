package com.involves.selecao.converters;

import com.involves.selecao.domain.Alerta;
import com.involves.selecao.service.dto.AlertaDTO;

public class AlertaConverter {
	
	public static Alerta convertToAlerta(AlertaDTO alertaDTO) {
		if (alertaDTO != null) {
			Alerta alerta = new Alerta();
			alerta.setId(alertaDTO.getId());
			alerta.setDescricao(alertaDTO.getDescricao());
			alerta.setDataHoraCadastro(alertaDTO.getData_hora_cadastro());
			alerta.setFlTipo(alertaDTO.getFl_tipo());
			alerta.setMargem(alertaDTO.getMargem());
			alerta.setPontoDeVenda(alertaDTO.getPonto_de_venda());
			alerta.setProduto(alertaDTO.getProduto());
			alerta.setCategoria(alertaDTO.getCategoria());
			return alerta;
		}
		return null;
	}

	public static AlertaDTO convertToAlertaDTO(Alerta alerta) {
		AlertaDTO alertaDTO = new AlertaDTO();
		alertaDTO.setId(alerta.getId());
		alertaDTO.setDescricao(alerta.getDescricao());
		alertaDTO.setData_hora_cadastro(alerta.getDataHoraCadastro());
		alertaDTO.setFl_tipo(alerta.getFlTipo());
		alertaDTO.setMargem(alerta.getMargem());
		alertaDTO.setPonto_de_venda(alerta.getPontoDeVenda());
		alertaDTO.setProduto(alerta.getProduto());
		alertaDTO.setCategoria(alerta.getCategoria());
		return alertaDTO;
	}

}
