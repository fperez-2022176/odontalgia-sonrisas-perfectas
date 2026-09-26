package com.ferp.system.dao;

import com.ferp.system.model.Tratamiento;
import com.ferp.system.config.ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO {

    public boolean insertarServicio(Tratamiento t) {
        // Solo 3 parámetros porque id_servicio ahora es auto_increment en la base de datos
        String sql = "{CALL crear_servicio(?, ?, ?)}"; 

        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, t.getNombre());
            stmt.setString(2, t.getDescripcion());
            stmt.setDouble(3, t.getCosto());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al insertar servicio con procedimiento: " + e.getMessage());
            return false;
        }
    }

    public List<Tratamiento> obtenerServicios() {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT id_servicio, name_service, descripcion, precio FROM servicios";

        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Tratamiento(
                    String.valueOf(rs.getInt("id_servicio")),
                    rs.getString("name_service"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener servicios: " + e.getMessage());
        }
        return lista;
    }
}