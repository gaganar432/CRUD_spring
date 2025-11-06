package com.tcs.Crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*; // read ,write , etc (for crud)
import com.tcs.Crud.entity.MovieEntity;
import com.tcs.Crud.repository.MovieRepository;


@Controller
public class MovieController {

    @Autowired
    private MovieRepository repo;

    @GetMapping({"","/"})
    public String index() {
    	return "index";
    }
    

    @GetMapping("/viewmovies")                    // for all movies page
    public String viewMovies(Model model) {
    	List<MovieEntity> movies = repo.findAll();
    	model.addAttribute("movies", movies);
    	return "viewmovies";
    }
    
                                                   
    @GetMapping("/addmovies")
    public String showAddMovieForm(Model model) {
    	model.addAttribute("movie", new MovieEntity());
    	return "addmovies";
    }
    
                                        
    @PostMapping("/addmovies")
    public String addMovie(@ModelAttribute MovieEntity movie) {
    	repo.save(movie);
    	return "redirect:/viewmovies";
    }
                                              
    @GetMapping("/editmovie/{id}")
    public String showEditMovieForm(@PathVariable("id") int id, Model model) {
        MovieEntity movie = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
        model.addAttribute("movie", movie);
        return "editmovie"; 
    }

                                           
    @PostMapping("/updateMovie/{id}")
    public String updateMovie(@PathVariable("id") int id, @ModelAttribute MovieEntity updatedMovie) {
        MovieEntity movie = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));

        
        movie.setMovieName(updatedMovie.getMovieName());
        movie.setHero(updatedMovie.getHero());
        movie.setReleaseDate(updatedMovie.getReleaseDate());
        movie.setDescription(updatedMovie.getDescription());
        movie.setImage(updatedMovie.getImage());

        repo.save(movie);
        return "redirect:/viewmovies";
    }
 
    @GetMapping("/deletemovie/{id}")
    public String deleteMovie(@PathVariable("id") int id) {
        repo.deleteById(id);
        return "redirect:/viewmovies";
    }


}