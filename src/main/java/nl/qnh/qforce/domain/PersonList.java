package nl.qnh.qforce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class that contains the logic to extract an embedded list from fetched Api data
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonList {

    @JsonProperty("results")
    public List<PersonModel> results;

    public PersonList() { };
}