package com.rys.moviecritics.rate.command.handler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.rys.moviecritics.rate.command.AddRateCommand;
import com.rys.moviecritics.rate.domain.Movie;
import com.rys.moviecritics.rate.domain.repository.MovieRepository;
import com.rys.moviecritics.rate.exception.NotFoundResourceException;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class AddRateCommandHandlerTest {

    private MovieRepository movieRepository;
    private AddRateCommandHandler commandHandler;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        commandHandler = new AddRateCommandHandler(movieRepository);
    }

    @Test
    @DisplayName("Should handle command correctly")
    void handle() {
        //Given
        final Movie movie = mock(Movie.class);
        final ObjectId id = ObjectId.get();
        final int rate = 5;

        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        //When
        commandHandler.handle(new AddRateCommand(rate, id));
        //Then
        verify(movie, times(1)).addRate(rate);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    @DisplayName("Should throw exception when no movie was found")
    void shouldThrow_exception() {
        //Given
        final ObjectId id = ObjectId.get();

        when(movieRepository.findById(id)).thenReturn(Optional.empty());
        //When
        final Executable exec = () -> commandHandler.handle(new AddRateCommand(5, id));
        //Then
        assertThrows(NotFoundResourceException.class, exec);
    }
}