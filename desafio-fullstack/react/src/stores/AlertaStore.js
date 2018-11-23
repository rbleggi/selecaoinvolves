import { observable } from "mobx";
//Service
import AlertaService from "../services/AlertaService";
export default class AlertaStore {
  @observable alertas = [];
  alertaService = new AlertaService();

  findAll = (fieldName = "todos", fieldValue) => {
    return this.alertaService.findAll(fieldName, fieldValue).then(alertas => {
      this.alertas = alertas;
    });
  };
}
