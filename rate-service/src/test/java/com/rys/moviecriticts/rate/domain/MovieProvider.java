package com.rys.moviecriticts.rate.domain;

import java.time.LocalDate;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;

public class MovieProvider {

    public static Movie create(final ObjectId id) {
        return new Movie(id, RandomStringUtils.random(20), LocalDate.now(), RandomStringUtils.random(5));
    }
}
