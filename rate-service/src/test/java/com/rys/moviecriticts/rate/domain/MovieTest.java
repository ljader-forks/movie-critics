package com.rys.moviecriticts.rate.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.rys.moviecriticts.rate.domain.exception.DomainException;
import java.math.BigDecimal;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class MovieTest {

    @Test
    @DisplayName("Should add rates")
    void shouldAdd_rates() {
        //Given
        final Movie movie = MovieProvider.create(ObjectId.get());

        final int rate1 = 10;
        final int rate2 = 5;
        //When
        movie.addRate(rate1);
        movie.addRate(rate2);
        //Then
        final List<Rate> expectedRates = movie.getRates();
        assertEquals(2, expectedRates.size());
        assertTrue(expectedRates.stream().anyMatch((rate) -> rate.getScale() == rate1));
        assertTrue(expectedRates.stream().anyMatch((rate) -> rate.getScale() == rate2));
        assertEquals(2, movie.getNumberOfVotes());
        assertEquals(new BigDecimal("7.5"), movie.getScore());

    }

    @Test
    @DisplayName("Should throw exception with invalid rate")
    void shouldReject_rate() {
        //Given
        final Movie movie = MovieProvider.create(ObjectId.get());

        final int wrongRate = -1;
        //When
        final Executable executable = () -> movie.addRate(wrongRate);
        //Then
        assertThrows(DomainException.class, executable);
    }
}