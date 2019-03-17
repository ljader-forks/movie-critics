package com.rys.moviecritics.rate.command;

import org.bson.types.ObjectId;

public class AddRateCommand implements Command {

    private final int rate;
    private final ObjectId movieId;

    public AddRateCommand(final int rate, final ObjectId movieId) {
        this.rate = rate;
        this.movieId = movieId;
    }

    public int getRate() {
        return rate;
    }

    public ObjectId getMovieId() {
        return movieId;
    }
}
