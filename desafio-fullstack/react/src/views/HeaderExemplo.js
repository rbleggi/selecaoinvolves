import React, { Component } from 'react';
import { Icon, Header } from 'semantic-ui-react';


class HeaderExemplo extends Component {


    render() {
        return (
            <Header as='h2' icon>
                <Icon name='address card outline' />
                Seleção Involves - Frontend Listagem Alertas
              <Header.Subheader>
                    Feito em React.
              </Header.Subheader>
            </Header>
        );
    }
}

export default HeaderExemplo;