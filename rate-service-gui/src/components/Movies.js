import React, {Component} from 'react';
import MovieItem from "./MovieItem";
import Pagination from "./Pagination";

class Movies extends Component {
  state = {
    movieList: [],
    currentSize: 10
  };

  componentDidMount = () => {
    this.refreshList()
  };

  refreshList = (size = this.state.currentSize) => {
    fetch(`http://localhost:8080/api/movies?size=${size}`)
    .then(res => res.json())
    .then(data => {
      if (data && Array.isArray(data)) {
        this.transformArrayData(data);
        this.setState({movieList: data, currentSize: size});
      }
    })
  };

  transformArrayData = (data) => {
    data.forEach(
        movie => {
          movie.score = Math.round(movie.score * 100) / 100;
          movie.productionDate = this.formatDate(new Date(movie.productionDate));
        });
  };

  formatDate = (date = new Date()) => {
    let month = String(date.getMonth() + 1);
    let day = String(date.getDate());
    const year = String(date.getFullYear());

    if (month.length < 2) {
      month = '0' + month
    }

    if (day.length < 2) {
      day = '0' + day;
    }

    return `${day}-${month}-${year}`;
  };

  render() {
    const {movieList} = this.state;

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
                  <MovieItem key={movie.id} item={movie} refreshList={this.refreshList}/>
              ))
            }
            </tbody>
          </table>
          <Pagination refreshList={this.refreshList}/>
        </div>

    )
  }
}

export default Movies;
