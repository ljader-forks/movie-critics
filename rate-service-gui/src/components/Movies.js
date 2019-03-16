import React, {Component} from 'react';
import MovieItem from "./MovieItem";

class Movies extends Component {
  state = {
    movieList: []
  };

  componentDidMount = () => {
    fetch('http://localhost:8080/api/movies')
    .then(res => res.json())
    .then(data => this.setState({movieList: data}))
  };

  render() {
    const {movieList} = this.state;

    return movieList.map((movie) => (
        <MovieItem key={movie.id} item={movie}/>
    ));
  }
}

export default Movies;
