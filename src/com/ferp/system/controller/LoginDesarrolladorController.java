package com.ferp.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ferp.system.config.ConexionDB;

public class LoginDesarrolladorController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pwdClave;

    public void volverLogin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/ferp/system/view/LoginView.fxml"
                )
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("Login");

        stage.show();
    }

    @FXML
    public void irAOpciones(ActionEvent event) throws IOException {

        String usuario = txtUsuario.getText();
        String contrasena = pwdClave.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {

            mostrarAlerta(
                    "Campos vacíos",
                    "Debes ingresar usuario y contraseña."
            );

            return;
        }

        String sql = """
                SELECT *
                FROM admin
                WHERE name_clave = ?
                AND pass_admin = ?
                """;

        try {

            Connection conexion
                    = ConexionDB.getInstanciaConexionDB().getConnection();

            PreparedStatement ps
                    = conexion.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/com/ferp/system/view/opciones.fxml"
                        )
                );

                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();

                stage.setScene(new Scene(root));

                stage.setTitle("Opciones de Desarrollador");

                stage.show();

            } else {
                mostrarAlerta(
                        "Inicio de sesión",
                        "Usuario o contraseña incorrectos."
                );
            }

            // Cerrar recursos
            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();

            mostrarAlerta(
                    "Error",
                    "Ocurrió un error al consultar la base de datos."
            );
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
