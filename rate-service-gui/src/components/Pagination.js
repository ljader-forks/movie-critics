import React, {Component} from 'react';
import {connect} from 'react-redux';
import {changePageSize} from "../actions/movieActions";

class Pagination extends Component {

  state = {
    size: 10
  };

  componentWillReceiveProps = (nextProps) => {
    const {newSize} = nextProps.size
    if (newSize !== this.props.size) {
      this.setState({size: newSize});
    }
  };

  componentDidMount = () => this.setState({size: this.props.size});

  changeSize = (e) => this.setState({size: e.target.value});

  refresh = () => {
    const {size} = this.state;

    this.props.changePageSize(size);
  };

  render() {
    const {size} = this.state;
    return (
        <div className={"col-md-2"}>
          <div className={"input-group"}>
            <input type={"text"} value={size} onChange={this.changeSize} className={"form-control"}/>
            <span className="input-group-btn">
              <button className={"btn btn-info"} onClick={this.refresh}>Size</button>
            </span>
          </div>
        </div>

    )

  }
}

const mapStateToProps = state => ({
  size: state.movieReducer.size
});

export default connect(mapStateToProps, {changePageSize})(Pagination);
