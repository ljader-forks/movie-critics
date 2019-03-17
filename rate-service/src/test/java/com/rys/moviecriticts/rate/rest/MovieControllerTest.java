package com.rys.moviecriticts.rate.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rys.moviecriticts.rate.rest.dto.CreationRateDto;
import com.rys.moviecriticts.rate.domain.MovieProvider;
import com.rys.moviecriticts.rate.domain.repository.MovieRepository;
import com.rys.moviecriticts.rate.query.view.MovieView;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

@Tag("IT")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Autowired
    private MovieRepository movieRepository;

    @TestConfiguration
    public static class MongoConfig {

        private static final String MONGO_DB_URL = "localhost";
        private static final String MONGO_DB_NAME = "embeded_db";

        @Bean
        public MongoTemplate mongoTemplate() throws IOException {
            EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
            mongo.setBindIp(MONGO_DB_URL);
            return new MongoTemplate(mongo.getObject(), MONGO_DB_NAME);
        }
    }

    @AfterEach
    @BeforeEach
    void cleanUp() {
        movieRepository.deleteAll();
    }

    @Test
    @DisplayName("Should add rate")
    void shouldAdd_rate() {
        //Given
        final CreationRateDto rateDto = new CreationRateDto(5);
        final ObjectId id = ObjectId.get();

        movieRepository.save(MovieProvider.create(id));
        //When
        final ResponseEntity<String> response = testRestTemplate.postForEntity(getRateEndpoint(id), rateDto,
            String.class, Map.of());
        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return 400 when movie not found")
    void shouldReturn_400() {
        //Given
        final CreationRateDto rateDto = new CreationRateDto(5);
        //When
        final ResponseEntity<String> response = testRestTemplate.postForEntity(getRateEndpoint(ObjectId.get()), rateDto,
            String.class, Map.of());
        //Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Should find movies")
    void shouldFind_movies() {
        //Given
        movieRepository.save(MovieProvider.create(ObjectId.get()));
        movieRepository.save(MovieProvider.create(ObjectId.get()));
        //When
        final ResponseEntity<List<MovieView>> exchange = testRestTemplate.exchange(getMovieEndpoint(), HttpMethod.GET,
            null, getListMovieViewsType(), Map.of());
        //Then
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals(2, exchange.getBody().size());
    }

    private ParameterizedTypeReference<List<MovieView>> getListMovieViewsType() {
        return new ParameterizedTypeReference<>() {
        };
    }

    private String getMovieEndpoint() {
        return getMoviePath().toUriString();
    }

    private UriComponentsBuilder getMoviePath() {
        return UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(port).path("/api/movies");
    }

    private String getRateEndpoint(final ObjectId id) {
        return getMoviePath().path("/{id}/rates").buildAndExpand(id).toUriString();
    }

}