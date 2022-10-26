package nl.qnh.qforce.domain;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;

/**
 * The class that implements the Movie interface and serves MovieModel object functionalities
 */
@JsonPropertyOrder({"title", "episode", "director", "release_date"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieModel implements Movie {

    @JsonProperty("title")
    private String title;
    @JsonAlias({"episode_id", "episode"})
    private Integer episode;
    @JsonProperty("director")
    private String director;
    @JsonProperty("release_date")
    private String date;

    private LocalDate releaseDate;

    public MovieModel() {
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public Integer getEpisode() { return episode; }

    @Override
    public String getDirector() { return director; }

    @JsonIgnore
    @Override
    public LocalDate getReleaseDate() {
        return null;
    }
}