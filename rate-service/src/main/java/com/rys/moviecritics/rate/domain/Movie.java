package com.rys.moviecritics.rate.domain;

import com.rys.moviecritics.rate.domain.exception.DomainException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {

    @Id
    private final ObjectId id;
    @NotEmpty
    private final String title;
    @NotNull
    private final LocalDate productionDate;
    @NotEmpty
    private final String genre;
    @Valid
    private List<Rate> rates;
    private BigDecimal score;
    private int numberOfVotes;

    public Movie(final ObjectId id, final String title, final LocalDate productionDate, final String genre) {
        this.id = id;
        this.title = title;
        this.productionDate = productionDate;
        this.genre = genre;
        this.rates = new ArrayList<>();
        this.numberOfVotes = 0;
    }

    public synchronized void addRate(final int rate) {
        if (rate < 0 || rate > 10) {
            throw new DomainException("Rate " + rate + " is greater than 10 or less than 0");
        }
        this.rates.add(new Rate(rate, LocalDateTime.now()));
        calculateScore(new BigDecimal(rate), new BigDecimal(this.numberOfVotes));
        numberOfVotes++;
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

    public List<Rate> getRates() {
        return Collections.unmodifiableList(rates);
    }

    public BigDecimal getScore() {
        return score;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    private void calculateScore(final BigDecimal rate, final BigDecimal numberOfVotes) {
        if (score == null) {
            score = rate;
        } else {
            score = score.multiply(numberOfVotes, MathContext.DECIMAL128)
                .add(rate)
                .divide(numberOfVotes.add(BigDecimal.ONE), MathContext.DECIMAL128);
        }
    }
}
