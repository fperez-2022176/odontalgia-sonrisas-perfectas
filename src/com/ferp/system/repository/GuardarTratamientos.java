package com.ferp.system.repository;

import com.ferp.system.config.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuardarTratamientos {

    public boolean guardar(String nombre, String descripcion, double precio) {

        String sql = """
                INSERT INTO servicios (name_service, descripcion, precio)
                VALUES (?, ?, ?)
                """;

        try {

            Connection connection =
                    ConexionDB.getInstanciaConexionDB().getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}