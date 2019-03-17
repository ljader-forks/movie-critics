package com.rys.moviecriticts.rate.command.handler;

import com.rys.moviecriticts.rate.command.AddRateCommand;
import com.rys.moviecriticts.rate.domain.Movie;
import com.rys.moviecriticts.rate.domain.repository.MovieRepository;
import com.rys.moviecriticts.rate.exception.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AddRateCommandHandler implements CommandHandler<AddRateCommand> {

    private final MovieRepository movieRepository;

    @Autowired
    public AddRateCommandHandler(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public synchronized void handle(final AddRateCommand command) {
        final Movie movie = getMovie(command);
        movie.addRate(command.getRate());
        movieRepository.save(movie);
    }

    private Movie getMovie(final AddRateCommand command) {
        return movieRepository.findById(command.getMovieId())
            .orElseThrow(
                () -> new NotFoundResourceException("Could not find move with given id = " + command.getMovieId()));
    }
}
