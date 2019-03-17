import React, {Component} from 'react';

class Pagination extends Component {

  changeSize = (e) => this.props.changeSize(e.target.value);

  render() {
    const {refreshList} = this.props;
    return (
        <div className={"col-md-2"}>
          <div className={"input-group"}>
            <input type={"text"} value={this.props.size} onChange={this.changeSize} className={"form-control"}/>
            <span className="input-group-btn">
              <button className={"btn btn-info"} onClick={refreshList}>Size</button>
            </span>
          </div>
        </div>

    )

  }
}

export default Pagination;
