import React, { Component } from 'react';
import { Input, Select, Button} from 'semantic-ui-react';
import { observer } from 'mobx-react';
import { observable, action } from 'mobx';

@observer
class AlertasList extends Component {
    @observable
    fieldName = "todos";
    @observable
    fieldValue = "";

    @action
    setFieldName = fieldName => this.fieldName= fieldName;
    @action
    setFieldValue = fieldValue => this.fieldValue=fieldValue;

    findAll = () => {
        this.props.findAll(this.fieldName, this.fieldValue)
    }
    _setFieldValue=(e, { name, value }) =>{
       this.setFieldValue(value);
    }
    _setFieldName = (e, { name, value }) => {
        this.setFieldName(value);
    }
  
    render() {
        const options = [
            { key: 'ponto_de_venda', text: 'Ponto de venda', value: 'ponto_de_venda' },
            { key: 'fl_tipo', text: 'Tipo', value: 'fl_tipo' },
          ]
        return (
            <div>
                <p/>
                
                <Input  name='searchValues' type='text' placeholder='Search...' action onChange={this._setFieldValue}>
                    <input disabled={this.fieldName === "todos"} />
                    <Select  compact options={options} name='fieldName' defaultValue='todos' onChange={this._setFieldName} />
                    <Button  onClick={this.findAll} >Search</Button>
                </Input>
                
                <p></p>
                <p></p>
            </div>
        );
    }
}

export default AlertasList;
