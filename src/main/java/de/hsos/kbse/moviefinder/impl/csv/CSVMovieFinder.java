/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.moviefinder.impl.csv;

import de.hsos.kbse.movie.impl.Movie;
import de.hsos.kbse.movie.impl.MovieBuilder;
import de.hsos.kbse.moviefinder.inf.MovieFinder;
import de.hsos.kbse.qualifier.moviefinder.CSV;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Alex
 */
@CSV
public class CSVMovieFinder implements MovieFinder {
        
    @Inject 
    MovieBuilder mb;
    
    private final String filename = "movies.txt";

    @Override
    @Produces @CSV 
    public List<Movie> findAllMovies() {
        List<Movie> movies = new ArrayList<>();
		FileReader fr;
		BufferedReader br;
		String line;
		String[] lines;
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				lines = line.split(":");
				movies.add(mb.newMovie(lines[1], lines[0]));
			}
			br.close();

		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
		} catch (IOException ex) {
			System.out.println("IOException!");
		}
		return movies;
    }
}
