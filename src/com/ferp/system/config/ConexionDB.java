package com.ferp.system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionDB {

    private static ConexionDB instanciaConexionDB;
    public static Object getConnection; 
    private Connection connection;

    private ConexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Enviroment.LOCATION_SERVICE + "/" + Enviroment.DATA_BASE, Enviroment.USER, Enviroment.PASSWORD);

        } catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null, "Error clase no encontrada");
        } catch (SQLException sqlEXception) {
            JOptionPane.showMessageDialog(null, "Error de conexion a db");

        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Error padre"+ e.getMessage());   
        }
    }

    public static ConexionDB getInstanciaConexionDB() {
        if (instanciaConexionDB == null) {
            instanciaConexionDB = new ConexionDB();
        }
        return instanciaConexionDB;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}