import { observable, action } from 'mobx';
//Model
import AlertaModel from '../models/AlertaModel';
//Service
import AlertaService from '../services/AlertaService';

export default class AlertaStore {

  @observable alerta = new AlertaModel();
  @observable alertas = []
  alertaService = new AlertaService();

  @action
  setAlerta = alertaById => {
    this.alerta.setValue('id', alertaById.id);
    this.alerta.setValue('alertaname', alertaById.alertaname);
    this.alerta.setValue('password', alertaById.password);
    this.alerta.setValue('name', alertaById.name);
    this.alerta.setValue('surname', alertaById.surname);
    this.alerta.setValue('is_enabled', alertaById.is_enabled);
    this.alerta.setValue('phone', alertaById.phone);
    this.alerta.setValue('email', alertaById.email);
    this.alerta.setValue('register_date', alertaById.register_date);
  }

  save = () => {
    return this.alertaService.save(this.alerta).then(() => this.reset())
  }
  findById = (id) => {
    return this.alertaService.findById(id).then((alertaById) => this.setAlerta(alertaById))
  }

  findAll = (fieldName, fieldValue) => {
    return this.alertaService.findAll(fieldName, fieldValue).then(alertas => {
      this.alertas = alertas
    })
  }

  deleteAlertaById = (id) => {
    return this.alertaService.deleteAlertaById(id).then(response => { return this.alertas = this.alertas.filter(alerta => alerta.id !== id) });
  }

  reset = () => {
    this.alerta = new AlertaModel();
  }

}
