package com.rys.moviecritics.rate.query;

import com.rys.moviecritics.rate.query.view.MovieView;
import com.rys.moviecritics.rate.rest.dto.PageableDto;
import java.util.List;

public interface MovieQuery {

    List<MovieView> findMovies(final PageableDto pageable);
}
