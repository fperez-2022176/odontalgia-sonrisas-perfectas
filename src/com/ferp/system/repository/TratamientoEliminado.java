package com.ferp.system.repository;

import com.ferp.system.config.ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TratamientoEliminado {

    public static boolean eliminarTratamiento(int idServicio) {

        String sql = "{CALL eliminar_servicio(?)}";

        try (
            Connection conn =
                    ConexionDB.getInstanciaConexionDB().getConnection();

            CallableStatement cs =
                    conn.prepareCall(sql)
        ) {

            cs.setInt(1, idServicio);

            cs.execute();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}

