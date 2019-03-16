package com.rys.moviecriticts.rate.domain;

import java.time.LocalDate;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;

public class MovieProvider {

    public static Movie create(final UUID id) {
        return new Movie(id, RandomStringUtils.random(20), LocalDate.now(), RandomStringUtils.random(5));
    }
}
