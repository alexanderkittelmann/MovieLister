/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.moviefinder.impl.db;

import de.hsos.kbse.movie.impl.Movie;
import de.hsos.kbse.movie.impl.MovieBuilder;
import de.hsos.kbse.moviefinder.inf.MovieFinder;
import de.hsos.kbse.qualifier.moviefinder.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Alex
 */
@DB
public class DBMovieFinder implements MovieFinder {

    @Inject
    MovieBuilder mb;

    @Override
    @Produces @DB
    public List<Movie> findAllMovies() {
        List<Movie> movies = new ArrayList<>();

        Connection con = null;
//		String strUrl = "jdbc:derby:C:\\Users\\puh\\.netbeans-derby\\Movies";
        String strUrl = "jdbc:derby:Z:\\.Win7_Profile\\.netbeans-derby\\Movies";
        Properties props = new Properties();
        props.put("user", "test");
        props.put("password", "test");
        //Die System-Property derby.system.home wird gesetzt 
        DBMovieFinder.setDBSystemDir();
        try {
            //Verwendet die vorhandene Connection
            con = DriverManager.getConnection(strUrl, props);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DIRECTORS.FIRSTNAME, DIRECTORS.LASTNAME, MOVIES.MOVIENAME FROM DIRECTORS, MOVIES WHERE DIRECTORS.D_ID = MOVIES.D_ID");
            while (rs.next()) {
                movies.add(mb.newMovie(rs.getString(3), rs.getString(1) + " " + rs.getString(2)));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMovieFinder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movies;
    }

    private static void setDBSystemDir() {
        String userHomeDir = System.getProperty("user.home", ".").replace("\\", "/");
        String systemDir = userHomeDir + "/db";
        // Setzen des Derby System Directories
        System.setProperty("derby.system.home", systemDir);
    }
}

