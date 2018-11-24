import React, { Component } from "react";
import { Input, Select, Button } from "semantic-ui-react";
import { observer } from "mobx-react";
import { observable, action } from "mobx";

@observer
class AlertasList extends Component {
  @observable
  fieldName = "todos";
  @observable
  fieldValue = "";

  @action
  setFieldName = fieldName => (this.fieldName = fieldName);
  @action
  setFieldValue = fieldValue => (this.fieldValue = fieldValue);

  buscar = () => {
    this.props.buscar(this.fieldName, this.fieldValue);
  };
  _setFieldValue = (e, { name, value }) => this.setFieldValue(value);

  _setFieldValueTipo = (e, { name, value }) => {
    this.setFieldValue(value);
    this.buscar();
  };
  _setFieldName = (e, { name, value }) => {
    this.setFieldName(value);
    if (value === "fl_tipo") this.setFieldValue("1");
    if (value !== "ponto_de_venda") this.buscar();
  };

  render() {
    const options = [
      { key: "todos", text: "Todos", value: "todos" },
      {
        key: "ponto_de_venda",
        text: "Ponto de venda",
        value: "ponto_de_venda"
      },
      { key: "fl_tipo", text: "Tipo", value: "fl_tipo" }
    ];
    const optionsTipo = [
      { key: "ruptura", text: "Ruptura detectada!", value: "1" },
      { key: "precoAcima", text: "Preço acima do estipulado!", value: "2" },
      { key: "precoAbaixo", text: "Preço abaixo do estipulado!", value: "3" },
      {
        key: "participacaoAcima",
        text: "Participação superior ao estipulado",
        value: "5"
      },
      {
        key: "participacaoAbaixo",
        text: "Participação inferior ao estipulado",
        value: "4"
      }
    ];
    return (
      <div>
        <p />

        <Input
          name="searchValues"
          type="text"
          placeholder="Digite o ponto de venda..."
          action
          onChange={this._setFieldValue}
        >
          {this.fieldName === "ponto_de_venda" && (
            <input hidden={this.fieldName === "todos"} />
          )}
          {this.fieldName === "fl_tipo" && (
            <Select              hidden={this.fieldName !== "fl_tipo"}              compact              options={optionsTipo}              name="fieldTipo"
              defaultValue="1"
              onChange={this._setFieldValueTipo}
            />
          )}
          <Select
            compact
            options={options}
            name="fieldName"
            defaultValue="todos"
            onChange={this._setFieldName}
          />
          {this.fieldName === "ponto_de_venda" && (
            <Button onClick={this.buscar}>Buscar</Button>
          )}
        </Input>

        <p />
        <p />
      </div>
    );
  }
}

export default AlertasList;
