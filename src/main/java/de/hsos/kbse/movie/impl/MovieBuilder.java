/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.movie.impl;

/**
 *
 * @author Alex
 */
public class MovieBuilder {
    
    private MovieBuilder() {
    }
    
    public static MovieBuilder getInstance() {
        return MovieBuilderHolder.INSTANCE;
    }
    
    private static class MovieBuilderHolder {

        private static final MovieBuilder INSTANCE = new MovieBuilder();
    }
    
    public Movie newMovie(String directorName, String title){
        Movie m = new Movie();
        m.setDirector(directorName);
        m.setTitle(title);
                
        return m;
    }
}
