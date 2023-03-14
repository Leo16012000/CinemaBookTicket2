package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.Session;

public class SessionDao extends Dao<Session> {

    UserDao userDao = new UserDao();

    @Override
    public ArrayList<Session> getAll() throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session`  ORDER BY `session`.`start_time` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            session.setUser(userDao.get(session.getUserId()));
            sessions.add(session);
        }
        return sessions;
    }

    @Override
    public Session get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` WHERE `id` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            session.setUser(userDao.get(session.getUserId()));
            return session;
        }
        return null;
    }

    public ArrayList<Session> getSession(int id) throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` WHERE `user_id` = " + id + " ORDER BY `session`.`start_time` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            session.setUser(userDao.get(session.getUserId()));
            sessions.add(session);
        }
        return sessions;
    }

    @Override
    public void save(Session t) throws SQLException {
        if (t == null) {
            throw new SQLException("Shipment rỗng");
        }
        String query = "INSERT INTO `session` (`user_id`, `start_time`, `end_time` , `message`) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getUserId());
        stmt.setTimestamp(2, t.getStartTime());
        stmt.setTimestamp(3, t.getEndTime());
        stmt.setNString(4, t.getMessage());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Session t) throws SQLException {
        if (t == null) {
            throw new SQLException("Shipment rỗng");
        }
        String query = "UPDATE `session` SET `start_time` = ?, `end_time` = ?, `message` = ? WHERE `session`.`id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, t.getStartTime());
        stmt.setTimestamp(2, t.getEndTime());
        stmt.setNString(3, t.getMessage());
        stmt.setInt(4, t.getId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(Session t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Session getLast(int userId) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `session` WHERE `user_id` = " + userId
                + " ORDER BY `id` DESC LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            session.setUser(userDao.get(session.getUserId()));
            return session;
        }
        return null;
    }

    public ArrayList<Session> getAll(Timestamp start, Timestamp end) throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();
        String query = "SELECT * FROM `session` WHERE `message` = ? AND DATE(start_time) >= DATE(?) AND DATE(start_time) <= DATE(?) ORDER BY `session`.`start_time` DESC";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, "logout");
        statement.setTimestamp(2, start);
        statement.setTimestamp(3, end);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Session session = Session.getFromResultSet(rs);
            session.setUser(userDao.get(session.getUserId()));
            sessions.add(session);
        }
        return sessions;
    }

}
