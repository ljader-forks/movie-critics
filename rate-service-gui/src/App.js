import React, {Component} from 'react';
import './App.css';
import Movies from "./components/Movies";
import Header from "./components/layout/Header";
import {Provider} from 'react-redux'

import store from './store';

class App extends Component {
  render() {
    return (
        <Provider store={store}>
          <div className="App">
            <div className={"App-header"}>
              <Header/>
            </div>
            <div className="container">
              <Movies/>
            </div>
          </div>
        </Provider>
    );
  }
}

export default App;
