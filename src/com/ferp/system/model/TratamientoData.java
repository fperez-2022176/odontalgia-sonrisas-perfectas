package com.ferp.system.model;

import com.ferp.system.config.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TratamientoData {

    private static final ObservableList<Tratamiento> tratamientos =
            FXCollections.observableArrayList();

    public static ObservableList<Tratamiento> getTratamientos() {

        tratamientos.clear();

        String sql = """
            SELECT id_servicio, name_service, descripcion, precio
            FROM servicios
            """;

        try (
            Connection conn =
                    ConexionDB.getInstanciaConexionDB().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                int idServicio = rs.getInt("id_servicio");
                String nombre = rs.getString("name_service");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");

                // Como servicios no tiene una columna codigo,
                // generamos uno temporalmente a partir del ID.
                String codigo = "TRT-" + idServicio;

                Tratamiento tratamiento = new Tratamiento(
                        idServicio,
                        codigo,
                        nombre,
                        descripcion,
                        precio
                );

                tratamientos.add(tratamiento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tratamientos;
    }

    
}


