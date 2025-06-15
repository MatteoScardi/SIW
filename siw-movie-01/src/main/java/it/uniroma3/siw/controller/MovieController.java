package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.validator.MovieValidator;


@Controller
public class MovieController {
	
	@Autowired MovieService movieService;
	@Autowired MovieValidator movieValidator;
	
	@GetMapping("/")
    public String home() {
        return "home.html"; // Nome del template HTML da restituire
    }
	
	@GetMapping("/formNewMovie")
	public String formNewMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "formNewMovie.html";
	}
	
	@PostMapping("/movie")
	public String newMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model) {
		
	this.movieValidator.validate(movie, bindingResult);
	
	
	if (bindingResult.hasErrors()) { 		// sono emersi errori nel binding
		return "formNewMovie.html";
	
	} else { 								// NON sono emersi errori nel binding
		this.movieService.save(movie);
		model.addAttribute("movie", movie);
		return "redirect:movie/"+movie.getId();
	 }
	}
	
		
	@GetMapping ("/movie/{id}")		// associ il metodo al URL
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", this.movieService.getMovieById(id));
		return "movie.html";
	}
	
	@GetMapping ("/movie")
	public String showMovies(Model model) {
		model.addAttribute("movies", this.movieService.getAllMovies());
		return "movies.html";
	}
}
