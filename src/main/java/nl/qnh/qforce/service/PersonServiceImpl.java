package nl.qnh.qforce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import nl.qnh.qforce.domain.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.*;

/**
 * Class that contains the logic for fetching data from an external Api and returning it to the controllers
 */
@Service
public class PersonServiceImpl implements PersonService {
    DB_Connection db_connection = new DB_Connection();
    /**
     * Method that returns a list of Person objects provided by the fetchDataByName method
     * @param query query to filter objects by name
     * @return returns a list of Person objects
     */
    @Override
    public List<Person> search(String query) { return fetchDataByName(query); }
    /**
     * Method that returns an Optional of Person objects provided by the fetchDataById method
     * @param id query to filter objects by id
     * @return returns a list of Person objects
     */
    @Override
    public Optional<Person> get(long id) { return fetchDataByIp(id); }
    /**
     * Method that provides the functionality to pull data from an external api, map it to a PersonModel and return it in a list
     * @param query query to filter objects by name
     * @return returns a list of Person objects
     */
    public List<Person> fetchDataByName(String query) {
        DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(myDateTimeFormat);
        List<Person> people = new ArrayList<>();
        PersonList personList;
        String data = "";
        Integer id = 0;
        try {
            URL url = new URL("https://swapi.dev/api/people/?format=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    data += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                if (data != null) {
                    try{
                        personList = new ObjectMapper().readValue(data, PersonList.class);
                    } catch (Exception e) { return null; };

                    for (int i = 0; i < personList.results.size(); i++) {
                        if(personList.results.get(i).getName().toUpperCase(Locale.ROOT).equals(query.toUpperCase())){

                            personList.results.get(i).setId(i);
                            id += 1;

                            List<String> filmURLs = personList.results.get(i).getFilmURLs();
                            List<Movie> movies = new ArrayList<>();

                            for (String filmURL : filmURLs) {
                                movies.add(fetchMovie(filmURL));
                            }
                            personList.results.get(i).setMovies(movies);

                            people.add(personList.results.get(i));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db_connection.insertIntoTableDataByName("The Api has been called with paramter: " + query + " at: " + formattedDateTime);
        return people;
    }
    /**
     * Method that provides the functionality to pull data from an external api, map it to a PersonModel and return it in a list
     * @param id query to filter objects by id
     * @return returns a list of Person objects
     */
    public Optional<Person> fetchDataByIp(long id) {
        DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(myDateTimeFormat);
        PersonModel person = new PersonModel();
        String data = "";
        try {
            URL url = new URL("https://swapi.dev/api/people/" + id + "/?format=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    data += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                if (data != null) {
                    try{
                        person = new ObjectMapper().readValue(data, PersonModel.class);
                    } catch (Exception e) { return null; };

                    person.setId(id);

                    List<String> filmURLs = person.getFilmURLs();
                    List<Movie> movies = new ArrayList<>();

                    for (String filmURL : filmURLs) {
                        movies.add(fetchMovie(filmURL));
                    }
                    person.setMovies(movies);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404, Person with this id does not exist. Try a different id between 1 and 82.");
        }
        db_connection.insertIntoTableDataById("The Api has been called with id: " + id + " at: " + formattedDateTime);
        return Optional.of(person);
    }
    /**
     * Method to fetch a Movie object based on given url
     * @param filmURL url string to search for specific movie and its data
     * @return returns a single movie object
     */
    public Movie fetchMovie(String filmURL) {
        MovieModel movie = new MovieModel();
        String data = "";
        try {
            URL url = new URL(filmURL + "/?format=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    data += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                if (data != null) {
                    movie = new ObjectMapper().readValue(data, MovieModel.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }
}