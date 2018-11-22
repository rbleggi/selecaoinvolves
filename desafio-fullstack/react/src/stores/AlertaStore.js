import { observable, action } from 'mobx';
//Model
import AlertaModel from '../models/AlertaModel';
//Service
import AlertaService from '../services/AlertaService';

export default class AlertaStore {

  @observable alerta = new AlertaModel();
  @observable alertas = []
  alertaService = new AlertaService();

  @observable id = undefined;
  @observable ponto_de_venda = undefined;
  @observable descricao = undefined;
  @observable produto = undefined;
  @observable fl_tipo = undefined;
  @observable margem = undefined;
  @observable data_hora_cadastro = undefined;

  @action
  setAlerta = alerta => {
    this.alerta.setValue('id', alerta.id);
    this.alerta.setValue('ponto_de_venda', alerta.ponto_de_venda);
    this.alerta.setValue('descricao', alerta.descricao);
    this.alerta.setValue('produto', alerta.produto);
    this.alerta.setValue('fl_tipo', alerta.fl_tipo);
    this.alerta.setValue('margem', alerta.margem);
    this.alerta.setValue('data_hora_cadastro', alerta.data_hora_cadastro);
  }

  findAll = (fieldName, fieldValue) => {
    return this.alertaService.findAll(fieldName, fieldValue).then(alertas => {
      this.alertas = alertas
    })
  }

}
