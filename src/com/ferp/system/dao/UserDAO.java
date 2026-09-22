package com.ferp.system.dao;

import com.ferp.system.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User guardar(User user) throws SQLException {

        String sql = """
            INSERT INTO clientes (name, lastname)
            VALUES (?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {

                    String id =
                            String.valueOf(rs.getInt(1));

                    user.setId_cliente(id);

                    return user;
                }
            }
        }

        throw new SQLException(
                "No se pudo obtener el ID del cliente"
        );
    }
}
