package com.mycompany.movielisterweld;

import de.hsos.kbse.movie.impl.MovieLister;
import org.jboss.weld.environment.se.Weld;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MovieLister ml = new Weld().initialize().instance().select(MovieLister.class).get();
        ml.start();
    }
}
