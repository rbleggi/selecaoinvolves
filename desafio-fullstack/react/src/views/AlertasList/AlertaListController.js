import React, { Component } from "react";
import { observer } from "mobx-react";
import AlertaStore from "../../stores/AlertaStore";
import AlertasList from "./AlertasList";
import { Container } from "semantic-ui-react";
import AlertaCard from "./AlertaCard";
import { Button } from "semantic-ui-react";

@observer
class AlertaListController extends Component {
  alertaStore = new AlertaStore();

  componentDidMount() {
    return this.alertaStore.buscar();
  }

  buscar = (fieldName, fieldValue) =>
    this.alertaStore.buscar(fieldName, fieldValue);

  processar = () =>
    this.alertaStore.processar().then(() => this.alertaStore.buscar());

  remover = () =>
    this.alertaStore.remover().then(() => this.alertaStore.buscar());

  render() {
    return (
      <div className="AlertaListController">
        <div className="container">
          <Button onClick={this.processar}>Processar Dados</Button>
          <Button onClick={this.remover}>Remover Alertas</Button>
          <AlertasList buscar={this.buscar} />
        </div>
        <div>
          {
            <Container textAlign="left">
              <div className="ui four cards">
                {this.alertaStore.alertas &&
                  this.alertaStore.alertas.map(alerta => (
                    <AlertaCard key={alerta.id} alerta={alerta} />
                  ))}{" "}
              </div>
            </Container>
          }
        </div>
      </div>
    );
  }
}
export default AlertaListController;
