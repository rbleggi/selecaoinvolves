import React, { Component } from 'react'
import { observer } from 'mobx-react'
import AlertaStore from '../../stores/AlertaStore'
import AlertasList from './AlertasList'
import { Container } from 'semantic-ui-react';
import AlertaCard from './AlertaCard'

@observer
class AlertaListController extends Component {

    alertaStore = new AlertaStore()

    componentDidMount() {
        return this.alertaStore.findAll("todos", null);
    }

    findAll = (fieldName, fieldValue) => {
        return this.alertaStore.findAll(fieldName, fieldValue);
    }

    render() {
        return (
            <div className='AlertaListController'>
                <div className='container'>
                    <AlertasList
                        findAll={this.findAll} />
                </div>
                <div>{
                    <Container textAlign='left'>
                        <div className="ui four cards" >
                            {this.alertaStore.alertas && this.alertaStore.alertas.map((alerta) => <AlertaCard key={alerta.id} alerta={alerta} />)}  </div>
                    </Container>
                }</div>

            </div>
        )
    }
}
export default AlertaListController;
