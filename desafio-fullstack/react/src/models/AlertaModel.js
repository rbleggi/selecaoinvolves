import { observable, action } from 'mobx';
export default class AlertaModel {

  @observable id = undefined;
  @observable ponto_de_venda = undefined;
  @observable descricao = undefined;
  @observable produto = undefined;
  @observable fl_tipo = undefined;
  @observable margem = undefined;
  @observable data_hora_cadastro = undefined;
  
  @action
  setValue = (name, value) => {
      this[name] = value;
  }
}
