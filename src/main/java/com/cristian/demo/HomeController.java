package com.cristian.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model){
        // First let's create a director
        Director director = new Director();
        Director director1 = new Director();
        Director director2 = new Director();
        Director director3 = new Director();

        director.setName("Steve Bullock");
        director.setGenre("Drama");

        director1.setName("Quentin Tarantino");
        director1.setGenre("Drama");

        director2.setName("David Fincher");
        director2.setGenre("Action");

        director3.setName("Ethan Coen");
        director3.setGenre("Comedy");

        // Now let's create a movie
        Movie movie = new Movie();
        movie.setTitle("At This Hour");
        movie.setYear(2019);
        movie.setDescription("About Weekday News....");

        Movie movie1 = new Movie();
        movie1.setTitle("Once Upon a Time in Hollywood");
        movie1.setYear(2019);
        movie1.setDescription("About Hollywood in the 70's");
        movie1.setDirector(director1);

        Movie movie2 = new Movie();
        movie2.setTitle("Gone Girl");
        movie2.setYear(2014);
        movie2.setDescription("About wife's disappearance having become the focus of an intense media circus");
        movie2.setDirector(director2);

        Movie movie3 = new Movie();
        movie3.setTitle("The Ballad of Buster Scruggs");
        movie3.setYear(2018);
        movie3.setDescription("About Six tales of life and violence in the Old West");
        movie3.setDirector(director3);

        // Add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        Set<Movie> movies1 = new HashSet<Movie>();
        Set<Movie> movies2 = new HashSet<Movie>();
        Set<Movie> movies3 = new HashSet<Movie>();
        movies.add(movie);
        movies1.add(movie1);
        movies2.add(movie2);
        movies3.add(movie3);

        movie = new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar....");
        movies.add(movie);

        // Add the list of movies to the director's movie list
        director.setMovies(movies);
        director1.setMovies(movies1);
        director2.setMovies(movies2);
        director3.setMovies(movies3);

        // Save the director to the database
        directorRepository.save(director);
        directorRepository.save(director1);
        directorRepository.save(director2);
        directorRepository.save(director3);

        // Grab all the directors from the database and send them to
        // the template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}
