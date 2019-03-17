package com.rys.moviecritics.rate.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class Rate {

    @NotNull
    private final int scale;
    @NotNull
    private final LocalDateTime createdAt;
    private int count;

    public Rate(final int scale, final LocalDateTime createdAt) {
        this.scale = scale;
        this.createdAt = createdAt;
        this.count = 0;
    }

    public int getScale() {
        return scale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getCount() {
        return count;
    }
}
