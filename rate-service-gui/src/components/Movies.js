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

    return (
        <table className={"table table-striped table-hover"}>
          <thead>
          <tr>
            <th>Title</th>
            <th>Production Date</th>
            <th>Genre</th>
            <th>Score</th>
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
    )
  }
}

export default Movies;
