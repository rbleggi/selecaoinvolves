import React, { Component } from 'react';
import { Segment } from 'semantic-ui-react';
import HeaderExemplo from './views/HeaderExemplo';
import UserListController from './views/UserList/UserListController';
import FormUsersController from './views/FormUser/FormUsersController';
import { Route,NavLink,BrowserRouter } from "react-router-dom";
import './App.css';

class App extends Component {

  render() {

    return (
      <div className="App">
        <div className="ui container">
          <Segment>
            <HeaderExemplo />
            <BrowserRouter>
              <div>
                <div className="ui menu">
                  <NavLink to="/AlertasList" name="Lista" className="item" >Listar Alertas</NavLink>
                </div>
                <div className="content">
                  <Segment>
                    <Route path="/AlertasList" component={AlertaListController} />
                  </Segment>
                </div>
              </div>
            </BrowserRouter>
          </Segment>
        </div>
      </div>
    );
  }
}

export default App;
