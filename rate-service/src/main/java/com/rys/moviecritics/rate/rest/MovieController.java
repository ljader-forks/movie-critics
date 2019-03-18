package com.rys.moviecritics.rate.rest;

import com.rys.moviecritics.rate.command.AddRateCommand;
import com.rys.moviecritics.rate.command.CommandDispatcher;
import com.rys.moviecritics.rate.query.MovieQuery;
import com.rys.moviecritics.rate.query.view.MovieView;
import com.rys.moviecritics.rate.rest.dto.CreationRateDto;
import com.rys.moviecritics.rate.rest.dto.PageableDto;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final CommandDispatcher dispatcher;
    private final MovieQuery movieQuery;

    @Autowired
    public MovieController(final CommandDispatcher dispatcher, final MovieQuery movieQuery) {
        this.dispatcher = dispatcher;
        this.movieQuery = movieQuery;
    }

    @PostMapping("/{id}/rates")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    void addRate(@PathVariable final ObjectId id, @RequestBody final CreationRateDto rateDto) {
        dispatcher.dispatch(new AddRateCommand(rateDto.getRate(), id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    List<MovieView> findMovies(@RequestParam(required = false, defaultValue = "0") final int page,
        @RequestParam(required = false, defaultValue = "10") final int size) {
        return movieQuery.findMovies(new PageableDto(size, page));
    }
}
