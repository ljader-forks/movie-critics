import React, {Component} from 'react';
import './App.css';
import Movies from "./components/Movies";
import Header from "./components/layout/Header";

class App extends Component {
  render() {
    return (
        <div className="App">
          <div className={"App-header"}>
            <Header/>
          </div>
          <div className="container">
            <Movies/>
          </div>
        </div>
    );
  }
}

export default App;
