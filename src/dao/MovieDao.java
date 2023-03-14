package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Movie;

public class MovieDao extends Dao<Movie> {
	private static MovieDao instance;
    @Override
    public ArrayList<Movie> getAll() throws SQLException {
        ArrayList<Movie> Movies = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `movies`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Movie movie = Movie.getFromResultSet(rs);
            Movies.add(movie);
        }
        return Movies;
    }

    @Override
    public Movie get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `movies` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Movie movie = Movie.getFromResultSet(rs);
            return movie;
        }
        return null;
    }

    @Override
    public void save(Movie t) throws SQLException {
        if (t == null) {
            throw new SQLException("Empty Movie");
        }
        String query = "INSERT INTO `movies` (`price`, `duration_time`, `title`, `country`) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getPrice());
        stmt.setInt(2, t.getDurationTime());
        stmt.setNString(3, t.getTitle());
        stmt.setNString(4, t.getCountry());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Movie t) throws SQLException {
        if (t == null) {
            throw new SQLException("Movie rỗng");
        }
        String query = "UPDATE `movies` SET `price` = ?, `duration_time` = ?, `title` = ?, `country` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getPrice());
        stmt.setInt(2, t.getDurationTime());
        stmt.setNString(3, t.getTitle());
        stmt.setNString(4, t.getCountry());
        stmt.setInt(5, t.getId());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(Movie t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `movies` WHERE `id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `movies` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Movie> searchByKey(String key, String word) throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `movies` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Movie movie = Movie.getFromResultSet(rs);
            movies.add(movie);
        }
        return movies;
    }
    
    public static MovieDao getInstance() {
    	if (instance == null) {
    		return new MovieDao();
    	}
    	return instance;
    }
}
