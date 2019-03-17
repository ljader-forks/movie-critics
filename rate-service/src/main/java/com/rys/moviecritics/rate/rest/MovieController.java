package com.rys.moviecritics.rate.rest;

import com.rys.moviecritics.rate.command.handler.CommandHandler;
import com.rys.moviecritics.rate.query.MovieQuery;
import com.rys.moviecritics.rate.query.view.MovieView;
import com.rys.moviecritics.rate.rest.dto.CreationRateDto;
import com.rys.moviecritics.rate.command.AddRateCommand;
import com.rys.moviecritics.rate.rest.dto.PageableDto;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final CommandHandler<AddRateCommand> handler;
    private final MovieQuery movieQuery;

    @Autowired
    public MovieController(final CommandHandler<AddRateCommand> handler, final MovieQuery movieQuery) {
        this.handler = handler;
        this.movieQuery = movieQuery;
    }

    @PostMapping("/{id}/rates")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    void addRate(@PathVariable final ObjectId id, @RequestBody final CreationRateDto rateDto) {
        handler.handle(new AddRateCommand(rateDto.getRate(), id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    List<MovieView> findMovies(@RequestParam(required = false, defaultValue = "0") final int page,
        @RequestParam(required = false, defaultValue = "10") final int size) {
        return movieQuery.findMovies(new PageableDto(size, page));
    }
}
