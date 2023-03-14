package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Auditorium;

public class AuditoriumDao extends Dao<Auditorium> {
	public static AuditoriumDao instance = null; 
    @Override
    public ArrayList<Auditorium> getAll() throws SQLException {
        ArrayList<Auditorium> auditoriums = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `auditoriums`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Auditorium auditorium = Auditorium.getFromResultSet(rs);
            auditoriums.add(auditorium);
        }
        return auditoriums;
    }

    @Override
    public Auditorium get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `auditoriums` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Auditorium auditorium = Auditorium.getFromResultSet(rs);
            return auditorium;
        }
        return null;
    }

    @Override
    public void save(Auditorium t) throws SQLException {
        if (t == null) {
            throw new SQLException("Empty Auditorium");
        }
        String query = "INSERT INTO `auditoriums` (`auditorium_num`, `seats_num`) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getAuditoriumNum());
        stmt.setInt(2, t.getSeatsNum());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Auditorium t) throws SQLException {
        if (t == null) {
            throw new SQLException("Rỗng");
        }
        String query = "UPDATE `auditoriums` SET `auditorium_num` = ?, `seats_num` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getAuditoriumNum());
        stmt.setInt(2, t.getSeatsNum());
        int row = stmt.executeUpdate();
        
    }

    @Override
    public void delete(Auditorium t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `Auditoriums` WHERE `id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `Movies` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Auditorium> searchByKey(String key, String word) throws SQLException {
        ArrayList<Auditorium> auditoriums = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `Auditoriums` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Auditorium auditorium = Auditorium.getFromResultSet(rs);
            auditoriums.add(auditorium);
        }
        return auditoriums;
    }
    
    public static AuditoriumDao getInstance() {
    	if (instance == null) {
    		instance = new AuditoriumDao();
    	}
    	return instance;
    }

}
