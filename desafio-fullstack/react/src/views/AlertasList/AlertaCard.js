import React, { Component } from 'react';
import { NavLink } from "react-router-dom";
import {Icon} from "semantic-ui-react";
import {observer} from "mobx-react";
@observer
class AlertaCard extends Component {

  geraRandom(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }
  render() {

    const alerta = this.props.alerta

    const avatars = ['https://semantic-ui.com/images/avatar2/large/kristy.png',
      'https://semantic-ui.com/images/avatar2/large/matthew.png',
      'https://semantic-ui.com/images/avatar2/large/molly.png',
      'https://semantic-ui.com/images/avatar2/large/elyse.png',
      'https://semantic-ui.com/images/avatar/large/steve.jpg']

    return (
      <div className='ui card'>
        <img alt='Imagem de avatar' src={avatars[this.geraRandom(0, 4)]} className='ui image' />
        <div className='content'>
          <div className='header'>{alerta.nome}</div>
          <div className='meta'>
            <span className='date'>Ponto de Venda: {alerta.pontoDeVenda}</span>
          </div>
          <div className='meta'>
            <span className='date'>Data: {alerta.dataHoraCadastro}</span>
          </div>
          <div className='meta'>
            <span className='date'>Produto: {alerta.produto}</span>
          </div>
        </div>
      </div>
    )
  }
}
export default AlertaCard