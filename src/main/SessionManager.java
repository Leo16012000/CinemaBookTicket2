package main;

import dao.SessionDao;
import java.sql.SQLException;
import java.sql.Timestamp;
import models.User;
import models.Session;

public class SessionManager {

    public static Session session;
    static SessionDao sessionDao = new SessionDao();

    public SessionManager() {
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        SessionManager.session = session;
    }

    public static void create(User user) throws SQLException {
        if (user == null) {
            throw new SQLException("Nguoi dung khong hop le!");
        }
        Session ss = new Session();
        ss.setUser(user);
        ss.setMessage("login");
        ss.setStartTime(new Timestamp(System.currentTimeMillis()));
        ss.setEndTime(new Timestamp(System.currentTimeMillis() + 7200000));
        sessionDao.save(ss);
        Session sss = sessionDao.getLast(user.getId());
        setSession(sss);
    }

    public static void update() throws SQLException {
        if (session == null) {
            throw new SQLException("Ban chua dang nhap!");
        }
        session.setMessage("logout");
        session.setEndTime(new Timestamp(System.currentTimeMillis()));
        sessionDao.update(session);
        setSession(null);
    }

}
