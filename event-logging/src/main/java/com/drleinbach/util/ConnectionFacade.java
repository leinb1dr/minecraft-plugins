package com.drleinbach.util;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/25/13
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionFacade {

    private static final String jdbcURL = "jdbc:mysql://localhost/status";
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "drizzt";

    static {
        DbUtils.loadDriver(jdbcDriver);
    }



    public static Connection getConnection(){

        try {
            return DriverManager.getConnection(jdbcURL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;

    }

}
