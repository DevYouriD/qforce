package nl.qnh.qforce.service;

import static org.junit.jupiter.api.Assertions.*;
import nl.qnh.qforce.domain.Person;
import org.junit.jupiter.api.Test;
import nl.qnh.qforce.domain.Movie;
import java.util.Optional;
import java.util.List;

class PersonServiceImplTest {

    /**
     * Test if fetchDataByName method returns a list containing Person object(s)
     */
    @Test
    void fetchDataByName() {
        PersonServiceImpl personService = new PersonServiceImpl();
        List<Person> response = personService.fetchDataByName("Darth Vader"); //Act

        assertFalse(response.isEmpty());
    }

    /**
     * Test if fetchDataByIp method returns a list containing Person object(s)
     */
    @Test
    void fetchDataByIp() {
        PersonServiceImpl personService = new PersonServiceImpl();
        Optional<Person> response = personService.fetchDataByIp(1); //Act

        assertFalse(response.isEmpty());
    }

    /**
     * Test if fetchMovie method returns a Movie object
     */
    @Test
    void fetchMovie() {
        PersonServiceImpl personService = new PersonServiceImpl();
        Movie response = personService.fetchMovie("https://swapi.dev/api/films/1"); //Act

        assertFalse(response.equals(null));
    }
}