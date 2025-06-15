package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.MovieService;

@Component
public class MovieValidator implements Validator {

	@Autowired
	private MovieService movieService;
	
	// Validazione del titolo e dell'anno del film, se gia presenti da errore movie.duplicate
	@Override
	public void validate(Object o, Errors errors) {
		Movie movie = (Movie)o;
		if (movie.getTitle()!=null && movie.getYear()!=null && movieService.existsByTitleAndYear(movie.getTitle(), movie.getYear())) 
			errors.reject("movie.duplicate");
	
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Movie.class.equals(aClass);
	}
}
