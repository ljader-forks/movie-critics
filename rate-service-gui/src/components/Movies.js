import React, {Component} from 'react';
import MovieItem from "./MovieItem";
import Pagination from "./Pagination";
import {connect} from 'react-redux';
import {fetchMovies} from "../actions/movieActions";

class Movies extends Component {

  changeSize = (size = this.state.size) => this.setState({size: size});

  componentDidMount() {
    this.props.fetchMovies(this.props.size);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.refreshMovies) {
      this.props.fetchMovies(nextProps.size ? nextProps.size : this.props.size);
    }
  }

  render() {
    const {movieList, size} = this.props;

    return (
        <div>
          <table className={"table table-striped table-hover"}>
            <thead>
            <tr>
              <th>Title</th>
              <th>Production Date</th>
              <th>Genre</th>
              <th>Score (Votes)</th>
              <th>Action</th>
            </tr>
            </thead>
            <tbody>
            {
              movieList.map((movie) => (
                  <MovieItem key={movie.id} item={movie}/>
              ))
            }
            </tbody>
          </table>
          <Pagination size={size}/>
        </div>

    )
  }
}

const mapStateToProps = state => ({
  movieList: state.movieReducer.movieList,
  size: state.movieReducer.size,
  refreshMovies: state.movieReducer.refreshMovies
});

export default connect(mapStateToProps, {fetchMovies})(Movies);
