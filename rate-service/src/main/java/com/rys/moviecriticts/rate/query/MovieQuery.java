package com.rys.moviecriticts.rate.query;

import com.rys.moviecriticts.rate.controller.dto.PageableDto;
import com.rys.moviecriticts.rate.query.view.MovieView;
import java.util.List;

public interface MovieQuery {

    List<MovieView> findMovies(final PageableDto pageable);
}
