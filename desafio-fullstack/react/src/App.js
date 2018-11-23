import React, { Component } from "react";
import { Segment } from "semantic-ui-react";
import HeaderExemplo from "./views/HeaderExemplo";
import AlertaListController from "./views/AlertasList/AlertaListController";
import { Route, BrowserRouter } from "react-router-dom";
import "./App.css";

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="ui container">
          <Segment>
            <HeaderExemplo />
            <BrowserRouter>
              <div className="content">
                <Segment>
                  <Route path="/" component={AlertaListController} />
                </Segment>
              </div>
            </BrowserRouter>
          </Segment>
        </div>
      </div>
    );
  }
}

export default App;
