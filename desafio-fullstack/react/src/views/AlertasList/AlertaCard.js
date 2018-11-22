import React, { Component } from 'react';
import {observer} from "mobx-react";

@observer
class AlertaCard extends Component {

  render() {
    const alerta = this.props.alerta
    return (
      <div className='ui card'>
        <div className='content'>
          <div className='header'>{alerta.nome}</div>
          <div className='meta'>
            <span className='date'>Ponto de Venda: {alerta.ponto_de_venda}</span>
          </div>
          <div className='meta'>
            <span className='date'>Data: {alerta.data_hora_cadastro}</span>
          </div>
          <div className='meta'>
            <span className='date'>Produto: {alerta.produto}</span>
          </div>
          {alerta.fl_tipo !== 1 &&
            <div className='meta'>
              <span className='date'>Margem: {alerta.margem}</span>
            </div>
          }
        </div>
      </div>
    )
  }
}
export default AlertaCard