import { observable } from "mobx";
//Service
import AlertaService from "../services/AlertaService";
export default class AlertaStore {
  @observable alertas = [];
  alertaService = new AlertaService();

  buscar = (fieldName = "todos", fieldValue) => {
    return this.alertaService.buscar(fieldName, fieldValue).then(alertas => {
      this.alertas = alertas;
    });
  };

  processar = () => this.alertaService.processar();

  remover = () => this.alertaService.remover();
}
