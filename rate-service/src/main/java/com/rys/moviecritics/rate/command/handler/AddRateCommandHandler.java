package com.rys.moviecritics.rate.command.handler;

import com.rys.moviecritics.rate.command.AddRateCommand;
import com.rys.moviecritics.rate.domain.Movie;
import com.rys.moviecritics.rate.domain.repository.MovieRepository;
import com.rys.moviecritics.rate.exception.NotFoundResourceException;
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
