package com.rys.moviecriticts.rate.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.rys.moviecriticts.rate.controller.dto.PageableDto;
import com.rys.moviecriticts.rate.domain.Movie;
import com.rys.moviecriticts.rate.domain.MovieProvider;
import com.rys.moviecriticts.rate.domain.repository.MovieRepository;
import com.rys.moviecriticts.rate.query.view.MovieView;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class SpringDataMovieQueryTest {

    private MovieQuery movieQuery;
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        movieQuery = new SpringRepositoryMovieQuery(movieRepository);
    }

    @Test
    @DisplayName("Should return views")
    void findMovies() {
        //Given
        final Movie firstMovie = MovieProvider.create(UUID.randomUUID());
        final Movie secondMovie = MovieProvider.create(UUID.randomUUID());
        final List<Movie> movies = List.of(firstMovie, secondMovie);
        final PageableDto pageableDto = new PageableDto(10, 0);

        when(movieRepository.findAll(PageRequest.of(pageableDto.getPage(), pageableDto.getSize()))).thenReturn(
            new PageImpl<>(movies));
        //When
        final List<MovieView> movieViews = movieQuery.findMovies(pageableDto);
        //Then
        assertEquals(2, movieViews.size());
        assertTrue(movieViews.containsAll(List.of(createMovieView(firstMovie), createMovieView(secondMovie))));
    }

    private MovieView createMovieView(final Movie firstMovie) {
        return new MovieView(firstMovie.getId(), firstMovie.getTitle(), firstMovie.getProductionDate(),
            firstMovie.getGenre());
    }
}