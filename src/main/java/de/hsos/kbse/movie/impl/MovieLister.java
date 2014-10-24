/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.movie.impl;

import de.hsos.kbse.movie.inf.GetMovies;
import de.hsos.kbse.moviefinder.inf.MovieFinder;
import de.hsos.kbse.qualifier.moviefinder.CSV;
import de.hsos.kbse.qualifier.moviefinder.DB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alex
 */
public class MovieLister implements GetMovies {
    
    List<Movie> movies; 
    
    @Inject @DB
    MovieFinder mf;
        
    private MovieLister() {
    }
    
    public static MovieLister getInstance() {
        return MovieListerHolder.INSTANCE;
    }
    
    private static class MovieListerHolder {

        private static final MovieLister INSTANCE = new MovieLister();
    }
    
    public void start() {
            String input;
            movies = mf.findAllMovies();
            System.out.println("Regisseur eingeben:\n");
            input = readInput();
            moviesDirectedBy(input);
        }
    
    private static String readInput() {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		try {
			line = console.readLine();
		} catch (IOException e) {
			System.err.println("IOException");
		}
		return line;
	}
        
	@Override
	public List<Movie> moviesDirectedBy(String directorName) {
		
		ArrayList<Movie> movieListDirector = new ArrayList<>();
		for (Movie m : movies) {
			if (m.getDirector().equals(directorName)) {
				movieListDirector.add(m);
			}
		}
		                
                for(Movie m : movieListDirector) {
                    System.out.println(m.getTitle());
                }
                                
		return movieListDirector;
	}

    public List<Movie> getMovies() {
        return movies;
    }
        
}
