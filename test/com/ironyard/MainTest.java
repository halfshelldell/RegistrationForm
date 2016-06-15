package com.ironyard;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by illladell on 6/15/16.
 */
public class MainTest {

    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTables(conn);
        return conn;
    }

    @Test
    public void testUser() throws SQLException {
        Connection conn = startConnection();
        User user = new User(1, "Alice", "123 red lane", "dasd@gmail.com");
        Main.insertUser(conn, user);
        ArrayList<User> userList = Main.selectUsers(conn);
        conn.close();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        Connection conn = startConnection();
        User user = new User(1, "Alice", "123 red lane", "aaa@gmail.com");
        Main.insertUser(conn, user);
        Main.updateUser(conn, "Bob", "345 university blvd", "alice@gmail.com", 1);
        ArrayList<User> userArrayList = Main.selectUsers(conn);
        conn.close();
        assertTrue(userArrayList.get(0).username.equals("Bob"));

    }

    @Test
    public void testDeleteUser() throws SQLException {
        Connection conn = startConnection();
        User user = new User(1, "Alice", "123 red lane", "aaa@gmail.com");
        Main.insertUser(conn, user);
        Main.deleteUser(conn, 1);
        ArrayList<User> userArrayList = Main.selectUsers(conn);
        conn.close();
        assertTrue(userArrayList.size() == 0);
    }

}