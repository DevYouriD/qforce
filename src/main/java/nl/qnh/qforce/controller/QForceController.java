package nl.qnh.qforce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import nl.qnh.qforce.service.PersonServiceImpl;
import nl.qnh.qforce.domain.Person;
import java.util.Optional;
import java.util.List;

/***
 * The rest controller that handles the api calls for people by name and people by id
 */
@RestController
public class QForceController {
    public QForceController() { }
    private PersonServiceImpl personService;
    @Autowired
    public QForceController(PersonServiceImpl personService) { this.personService = personService; }
    /**
     * @param query query to filter objects by name
     * @return returns a list of persons objects according to the given name
     */
    @GetMapping("/persons")
    public List<Person> getPeople(@RequestParam("q") String query) { return personService.search(query); }
    /**
     * @param id query to filter objects by id
     * @return returns an optional of a person object according to the given id
     */
    @GetMapping("/persons/{id}")
    public Optional<Person> getPerson(@PathVariable long id) { return personService.get(id); }
}