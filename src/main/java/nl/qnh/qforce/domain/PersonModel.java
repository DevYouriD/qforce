package nl.qnh.qforce.domain;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

/**
 * The class that implements the Person interface and serves PersonModel object functionalities
 */
@JsonPropertyOrder({ "id", "name", "birth_year", "gender", "height", "weight", "movies" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonModel implements Person {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("height")
    private Integer height;

    @JsonAlias({"mass", "weight"})
    private Integer weight;

    @JsonProperty(value = "films")
    private List<String> filmURLs;

    private List<Movie> movies;

    // EMPTY CONSTRUCTOR FOR OBJECT MAPPING
    public PersonModel(){ }

    // CONSTRUCTOR TO CREATE PERSON OBJECTS
    public PersonModel(long id, String name, String birthYear,Gender gender, Integer height, Integer weight, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.movies = movies;
    }

    // GETTERS AND SETTERS
    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Override
    public String getName() { return name; }
    public void setName(String Name) { this.name = Name; }

    @Override
    public String getBirthYear() { return birthYear; }
    public void setBirthYear(String BirthYear) { this.birthYear = BirthYear; }

    @Override
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    @Override
    public Integer getHeight() { return height; }
    public void setHeight(Integer Height) { this.height = Height; }

    @Override
        public Integer getWeight() { return weight; }
    public void setWeight(Integer Weight) { this.weight = Weight; }

    @JsonProperty(value = "films")
    public List<String> getFilmURLs() { return filmURLs; }

    @Override
    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
}