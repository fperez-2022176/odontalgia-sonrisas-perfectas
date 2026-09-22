package com.ferp.system.controller;
import com.ferp.system.config.ConexionDB;
import com.ferp.system.dao.UserDAO;
import com.ferp.system.model.User;
import com.ferp.system.utils.ViewFactory;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    private final ViewFactory viewFactory =
            new ViewFactory();

    private User user;

        public void abrirLogin(ActionEvent event) throws IOException {
 
        FXMLLoader loader = new FXMLLoader(

                getClass().getResource(

                        "/com/ferp/system/view/LoginDesarrollador.fxml"

                )

        );

        Parent root = loader.load();
 
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource())

                .getScene()

                .getWindow();
 
        stage.setScene(new Scene(root));
 
        stage.setTitle("Login Desarrollador");

        stage.show();

    }
 
    
    
    
    @FXML
    private void confirmar(ActionEvent event) {

        String nombre =
                txtNombre.getText().trim();

        String apellido =
                txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Debe llenar todos los campos.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

     
        try {

            Connection connection =
                    ConexionDB
                            .getInstanciaConexionDB()
                            .getConnection();

          
            System.out.println(
                    "CONEXION: " + connection
            );

            if (connection == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "No hay conexión con la base de datos.",
                        "Error de conexión",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            user = new User(
                    nombre,
                    apellido
            );

            UserDAO userDAO =
                    new UserDAO(connection);

            
            user = userDAO.guardar(user);

            if (user != null) {

                System.out.println(
                        "Cliente guardado correctamente"
                );

                System.out.println(
                        "ID cliente: "
                        + user.getId_cliente()
                );
                viewFactory.viewTratamientos();


            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo guardar el cliente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el cliente:\n"
                    + e.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }


   
    }
}




    
