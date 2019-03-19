import React, {Component} from 'react';
import Modal from "react-bootstrap/Modal";
import {connect} from "react-redux";
import {addRate} from "../actions/movieActions";

class RateModal extends Component {
  state = {
    show: false,
    rate: 1
  };

  handleClose = () => this.setState({show: false});

  handleShow = () => {
    return this.setState({show: true, rate: 1});
  };

  changeRate = (e) => this.setState({rate: e.target.value});

  onSubmit = (e) => {
    e.preventDefault();
    const {rate} = this.state;
    const {movieId} = this.props;
    const data = {
      rate
    };

    this.props.addRate(data, movieId);
    this.handleClose();

  };

  render() {
    const {movieTitle} = this.props;
    return (
        <>
          <button className={"btn btn-success"} onClick={this.handleShow}>
            Rate
          </button>

          <Modal show={this.state.show} onHide={this.handleClose} size={"lg"}>
            <Modal.Header closeButton>
              <Modal.Title>Rate Movie: {movieTitle}</Modal.Title>
            </Modal.Header>
            <form onSubmit={this.onSubmit}>
              <Modal.Body>
                <div className={"flex"}>
                  <label className={"flex-3"}>Rate: </label>
                  <input className={"slider flex-6"}
                         type="range"
                         min="1"
                         max="10"
                         value={this.state.rate}
                         id="slider"
                         onChange={this.changeRate}/>
                  <label className={"flex-3 center"}>{this.state.rate}</label>
                </div>
              </Modal.Body>
              <Modal.Footer>
                <button type={"submit"} className={"btn btn-success"}>Rate</button>
                <button className={"btn btn-warning"} onClick={this.handleClose}>Cancel</button>
              </Modal.Footer>
            </form>
          </Modal>
        </>
    );
  }
}

export default connect(null, {addRate})(RateModal);
