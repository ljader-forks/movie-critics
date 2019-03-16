package com.rys.moviecriticts.rate.domain.repository;

import com.rys.moviecriticts.rate.domain.Movie;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, UUID> {

}
