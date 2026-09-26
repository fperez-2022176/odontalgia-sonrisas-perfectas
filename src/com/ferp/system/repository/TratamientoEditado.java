package com.ferp.system.repository;

import com.ferp.system.config.ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TratamientoEditado {

    public static boolean actualizarTratamiento(
            int idServicio,
            String nombre,
            String descripcion,
            double precio) {

        String sql = "{CALL editar_servicio(?, ?, ?, ?)}";
        try (
                Connection conn
                = ConexionDB.getInstanciaConexionDB().getConnection(); CallableStatement cs
                = conn.prepareCall(sql)) {

            cs.setInt(1, idServicio);
            cs.setString(2, nombre);
            cs.setString(3, descripcion);
            cs.setDouble(4, precio);

            int filasAfectadas = cs.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
