package name.max_ferrara.car_application;

import java.sql.*;

public class DataBaseWorker {
    private Connection connection;
    private final String dataBasePath;

    public DataBaseWorker(String dataBasePath) {
        this.dataBasePath = dataBasePath;
    }

    public void openDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+ dataBasePath);
        } catch (SQLException exception) {
            System.out.println("data base is not found");
        } catch (ClassNotFoundException exception) {
            System.out.println("check you JDBC driver");
        }
    }

    public void closeDataBase() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
