package com.agtrack.utilities;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

    private static String url = "";
    private static String profile = "";
    private static String password = "";


    public ConnectionManager(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, profile, password);
    }

    static {

        try {

            Class.forName(System.getProperty("com.agtrack.connection.driver"));
            url = System.getProperty("com.agtrack.connection.url");
            String authorization = System.getProperty("com.agtrack.connection.authorization");
            profile = Authorization.decodeProfile(authorization);
            password = Authorization.decodePassword(authorization);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
