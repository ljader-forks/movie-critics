package com.rys.moviecriticts.rate.command;

import java.util.UUID;

public class AddRateCommand {

    private final int rate;
    private final UUID movieId;

    public AddRateCommand(final int rate, final UUID movieId) {
        this.rate = rate;
        this.movieId = movieId;
    }

    public int getRate() {
        return rate;
    }

    public UUID getMovieId() {
        return movieId;
    }
}
