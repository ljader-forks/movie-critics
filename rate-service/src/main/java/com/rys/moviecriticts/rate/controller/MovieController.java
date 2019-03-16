package com.rys.moviecriticts.rate.controller;

import com.rys.moviecriticts.rate.command.AddRateCommand;
import com.rys.moviecriticts.rate.command.handler.AddRateCommandHandler;
import com.rys.moviecriticts.rate.controller.dto.CreationRateDto;
import com.rys.moviecriticts.rate.controller.dto.PageableDto;
import com.rys.moviecriticts.rate.query.MovieQuery;
import com.rys.moviecriticts.rate.query.view.MovieView;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final AddRateCommandHandler handler;
    private final MovieQuery movieQuery;

    @Autowired
    public MovieController(final AddRateCommandHandler handler, final MovieQuery movieQuery) {
        this.handler = handler;
        this.movieQuery = movieQuery;
    }

    @PostMapping("/{id}/rates")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    void addRate(@PathVariable final UUID id, @RequestBody final CreationRateDto rateDto) {
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
