package data.db;

import java.sql.*;


public class DB {

    private static DB instance = new DB();
    private static final String URL = "jdbc:mysql://localhost/TreningsDagbok?useSSL=false";
    private static final String USER = "kakemann";
    private static final String PASSWORD = "jeghaterbarnmedraraksent";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private DB() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection lagKobling() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.toString());

            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.lagKobling();
    }

}