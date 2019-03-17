import React, {Component} from 'react';

class Pagination extends Component {
  state = {
    size: 10
  };

  changeSize = (e) => this.setState({size: e.target.value});

  refreshList = (e) => {
    e.preventDefault();
    this.props.refreshList(this.state.size);
  };

  render() {
    return (
        <div className={"col-md-2"}>
          <div className={"input-group"}>
            <input type={"number"} value={this.state.size} onChange={this.changeSize} className={"form-control"}/>
            <span className="input-group-btn">
              <button className={"btn btn-info"} onClick={this.refreshList}>Size</button>
            </span>
          </div>
        </div>

    )

  }
}

export default Pagination;
