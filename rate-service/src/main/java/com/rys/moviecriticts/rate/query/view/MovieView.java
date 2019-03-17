package com.rys.moviecriticts.rate.query.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

public class MovieView {

    private ObjectId id;
    private String title;
    private LocalDate productionDate;
    private String genre;
    private BigDecimal score;
    private int numberOfVoters;
    private List<Integer> rates;

    public MovieView(final ObjectId id, final String title, final LocalDate productionDate, final String genre,
        final BigDecimal score, final int numberOfVoters, final List<Integer> rates) {
        this.id = id;
        this.title = title;
        this.productionDate = productionDate;
        this.genre = genre;
        this.score = score;
        this.numberOfVoters = numberOfVoters;
        this.rates = rates;
    }

    public MovieView() {
    }

    public ObjectId getId() {
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

    public BigDecimal getScore() {
        return score;
    }

    public int getNumberOfVoters() {
        return numberOfVoters;
    }

    public List<Integer> getRates() {
        return Collections.unmodifiableList(rates);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MovieView movieView = (MovieView) o;
        return numberOfVoters == movieView.numberOfVoters && Objects.equals(id, movieView.id) && Objects.equals(title,
            movieView.title) && Objects.equals(productionDate, movieView.productionDate) && Objects.equals(genre,
            movieView.genre) && Objects.equals(score, movieView.score) && Objects.equals(rates, movieView.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, productionDate, genre, score, numberOfVoters, rates);
    }
}
