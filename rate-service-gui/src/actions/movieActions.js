import {ADD_RATE, CHANGE_PAGE_SIZE, FETCH_MOVIES} from "./types";
import {formatDate} from "./actionsUtils";

export const fetchMovies = (size = 10) => dispatch =>  {
    fetch(`http://localhost:8080/api/movies?size=${size}`)
    .then(res => res.json())
    .then(data => {
      if (data && Array.isArray(data)) {
        transformArrayData(data);
        dispatch({
          type: FETCH_MOVIES,
          payload: data,
          size: size
        });
      }
    })
};

export const addRate = (rateData, movieId) => dispatch =>  {
  fetch(`http://localhost:8080/api/movies/${movieId}/rates`, {
    method: 'post',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(rateData)
  }).then(() => {
    dispatch({
      type: ADD_RATE
    });
  });
};

export const changePageSize = (size=10) => dispatch => {
  dispatch({
    type: CHANGE_PAGE_SIZE,
    size: size
  })
};

function transformArrayData(data) {
  data.forEach(
      movie => {
        movie.score = Math.round(movie.score * 100) / 100;
        movie.productionDate = formatDate(new Date(movie.productionDate));
      });
}