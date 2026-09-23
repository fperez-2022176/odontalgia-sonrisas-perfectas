package com.ferp.system.controller;

import com.ferp.system.config.ConexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearTratamiento {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private void guardarTratamiento(ActionEvent event) {

        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String precioTexto = txtPrecio.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Campo vacío",
                    "Debe ingresar el nombre del tratamiento."
            );
            return;
        }

        if (precioTexto.isEmpty()) {
            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Campo vacío",
                    "Debe ingresar el precio del tratamiento."
            );
            return;
        }

        double precio;

        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Precio inválido",
                    "El precio debe ser un número."
            );
            return;
        }

        if (precio < 0) {
            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Precio inválido",
                    "El precio no puede ser negativo."
            );
            return;
        }

        String sql = """
                INSERT INTO servicios (name_service, descripcion, precio)
                VALUES (?, ?, ?)
                """;

        try {

            Connection connection
                    = ConexionDB.getInstanciaConexionDB().getConnection();

            PreparedStatement statement
                    = connection.prepareStatement(sql);

            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);

            statement.executeUpdate();

            mostrarMensaje(
                    Alert.AlertType.INFORMATION,
                    "Tratamiento guardado",
                    "El tratamiento se guardó correctamente."
            );

            cerrarVentana();

        } catch (SQLException e) {

            e.printStackTrace();

            mostrarMensaje(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo guardar el tratamiento."
            );
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensaje(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
