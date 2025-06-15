package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie getMovieById(Long id) {
		return movieRepository.findById(id).get();
	}
	
	public Iterable<Movie> getAllMovies(){
		return movieRepository.findAll();
	}
	
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	public boolean existsByTitleAndYear(String title, Integer year) {
		return movieRepository.existsByTitleAndYear(title, year);
	}

	
}
