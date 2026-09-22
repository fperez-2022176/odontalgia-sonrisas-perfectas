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


    // Botón para volver al Login
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


    // Botón "INICIAR SESIÓN"
    @FXML
    public void irAOpciones(ActionEvent event) throws IOException {

        // Obtener los datos escritos por el usuario
        String usuario = txtUsuario.getText();
        String contrasena = pwdClave.getText();


        // Comprobar campos vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {

            mostrarAlerta(
                    "Campos vacíos",
                    "Debes ingresar usuario y contraseña."
            );

            return;
        }


        // Consulta a la tabla admin
        String sql = """
                SELECT *
                FROM admin
                WHERE name_clave = ?
                AND pass_admin = ?
                """;


        try {

            // Obtener la conexión que ya tienes creada
            Connection conexion =
                    ConexionDB.getInstanciaConexionDB().getConnection();


            // Preparar la consulta
            PreparedStatement ps =
                    conexion.prepareStatement(sql);


            // Colocar los datos en los ?
            ps.setString(1, usuario);
            ps.setString(2, contrasena);


            // Ejecutar consulta
            ResultSet rs = ps.executeQuery();


            // Comprobar si encontró al usuario
            if (rs.next()) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/com/ferp/system/view/opciones.fxml"
                        )
                );

                Parent root = loader.load();


                // Obtener ventana actual
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();


                // Cambiar a opciones.fxml
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


    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
