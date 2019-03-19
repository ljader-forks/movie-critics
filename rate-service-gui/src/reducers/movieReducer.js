import {ADD_RATE, CHANGE_PAGE_SIZE, FETCH_MOVIES} from "../actions/types";

const initialState = {
  movieList: [],
  size: 10,
  refreshMovies: false

};

export default function (state = initialState, action) {
  switch (action.type) {
    case FETCH_MOVIES:
      return {
        ...state,
        movieList: action.payload,
        refreshMovies: false
      };
    case ADD_RATE:
      return {
        ...state,
        refreshMovies: true
      };
    case CHANGE_PAGE_SIZE:
      return {
        ...state,
        size: action.size,
        refreshMovies: true
      };
    default:
      return state;
  }
}