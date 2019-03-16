package com.rys.moviecriticts.rate.domain;

import com.rys.moviecriticts.rate.domain.exception.DomainException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {

    @Id
    private final UUID id;
    @NotEmpty
    private final String title;
    @NotNull
    private final LocalDate productionDate;
    @NotEmpty
    private final String genre;
    @Valid
    private List<Rate> rates;

    public Movie(final UUID id, final String title, final LocalDate productionDate, final String genre) {
        this.id = id;
        this.title = title;
        this.productionDate = productionDate;
        this.genre = genre;
        this.rates = new ArrayList<>();
    }

    public void addRate(final int rate) {
        if (rate < 0 || rate > 10) {
            throw new DomainException("Rate " + rate + " is greater than 10 or less than 0");
        }
        this.rates.add(new Rate(rate, LocalDateTime.now()));
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public String getGenre() {
        return genre;
    }

    public List<Rate> getRates() {
        return Collections.unmodifiableList(rates);
    }
}
