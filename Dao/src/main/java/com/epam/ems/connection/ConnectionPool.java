//NO NEED
/*package com.epam.ems.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionPool {

    private static DataSource DS;


    public static Connection getConnection() throws SQLException {
        return DS.getConnection();
    }

    @Autowired
    private ConnectionPool(DataSource dataSource) {
        DS = dataSource;
    }
}*/
