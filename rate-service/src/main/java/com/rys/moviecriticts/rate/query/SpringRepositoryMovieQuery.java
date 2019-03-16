package com.rys.moviecriticts.rate.query;

import com.rys.moviecriticts.rate.controller.dto.PageableDto;
import com.rys.moviecriticts.rate.domain.Rate;
import com.rys.moviecriticts.rate.domain.repository.MovieRepository;
import com.rys.moviecriticts.rate.query.view.MovieView;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
class SpringRepositoryMovieQuery implements MovieQuery {

    private final MovieRepository movieRepository;

    public SpringRepositoryMovieQuery(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieView> findMovies(final PageableDto pageableDto) {
        return movieRepository.findAll(PageRequest.of(pageableDto.getPage(), pageableDto.getSize()))
            .stream()
            .map((movie -> new MovieView(movie.getId(), movie.getTitle(), movie.getProductionDate(), movie.getGenre(),
                movie.getScore(), movie.getNumberOfVotes(),
                movie.getRates().stream().map(Rate::getScale).collect(Collectors.toList()))))
            .collect(Collectors.toList());
    }
}
