package com.ferp.system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instanciaConexionDB;

    private ConexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: clase MySQL no encontrada.");
            e.printStackTrace();
        }
    }

    public static ConexionDB getInstanciaConexionDB() {

        if (instanciaConexionDB == null) {
            instanciaConexionDB = new ConexionDB();
        }

        return instanciaConexionDB;
    }

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                "jdbc:mysql://"
                        + Enviroment.LOCATION_SERVICE
                        + "/"
                        + Enviroment.DATA_BASE,
                Enviroment.USER,
                Enviroment.PASSWORD
        );
    }
}

