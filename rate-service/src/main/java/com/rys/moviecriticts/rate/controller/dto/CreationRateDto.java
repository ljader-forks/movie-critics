package com.rys.moviecriticts.rate.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreationRateDto {

    private final int rate;

    @JsonCreator
    public CreationRateDto(@JsonProperty("rate") final int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
