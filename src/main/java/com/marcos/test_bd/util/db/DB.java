package com.marcos.test_bd.util.db;

import com.marcos.test_bd.exception.DbException;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = loadProperties().getProperty("dburl");
                connection = DriverManager.getConnection(url, loadProperties());
                return connection;
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        } else return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar conexão. Causado por: " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar ResultSet. Causado por: " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){
        try (FileInputStream fr = new FileInputStream("./src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(fr);
            return properties;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

}
