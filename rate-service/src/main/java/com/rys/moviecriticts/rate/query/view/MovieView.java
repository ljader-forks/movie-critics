package com.rys.moviecriticts.rate.query.view;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class MovieView {

    private UUID id;
    private String title;
    private LocalDate productionDate;
    private String genre;

    public MovieView(final UUID id, final String title, final LocalDate productionDate, final String genre) {
        this.id = id;
        this.title = title;
        this.productionDate = productionDate;
        this.genre = genre;
    }

    public MovieView() {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MovieView movieView = (MovieView) o;
        return Objects.equals(id, movieView.id) && Objects.equals(title, movieView.title) && Objects.equals(
            productionDate, movieView.productionDate) && Objects.equals(genre, movieView.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, productionDate, genre);
    }
}
