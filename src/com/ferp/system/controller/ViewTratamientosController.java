
package com.ferp.system.controller;

import com.ferp.system.config.ConexionDB;
import com.ferp.system.model.Servicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class ViewTratamientosController {
 
    @FXML private TableView<Servicio> tablaTratamientos;
    @FXML private TableColumn<Servicio, String> colTratamiento;
    @FXML private TableColumn<Servicio, String> colDescripcion;
    @FXML private TableColumn<Servicio, Double> colPrecio;
    @FXML private Button btnAgregar;
 
    @FXML
    public void initialize() {
       
        colTratamiento.setCellValueFactory(new PropertyValueFactory<>("nameService"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
 
        
        ObservableList<Servicio> servicios = FXCollections.observableArrayList(
            new Servicio("limpieza profunda", "tratamiento odontologico destinado a eliminar placa bacteria...", 1200.00),
            new Servicio("ortodoncia", "tratamiento odontologico especializado para corregir la posici...", 18000.00),
            new Servicio("extraccion dental", "procedimiento odontologico para remover una pieza dental c...", 500.00)
        );
 
        tablaTratamientos.setItems(servicios);
    }
 
    @FXML
    private void agregarTratamiento() {
      
        Servicio seleccionado = tablaTratamientos.getSelectionModel().getSelectedItem();
 
        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tratamiento Seleccionado");
            alert.setHeaderText("¡Servicio agregado exitosamente!");
            alert.setContentText("Tratamiento: " + seleccionado.getNameService() +
                                 "\nPrecio: Q" + seleccionado.getPrecio());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un servicio de la tabla antes de hacer clic en Agregar.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void guardarTratamiento(){

        Servicio seleccionado = tablaTratamientos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText(
                "Por favor, selecciona un tratamiento antes de guardar."
            );
            alert.showAndWait();
            return;
        }

        String sql = "INSERT INTO tratamientos (nombre, descripcion, precio) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, seleccionado.getNameService());
            ps.setString(2, seleccionado.getDescripcion());
            ps.setDouble(3, seleccionado.getPrecio());

            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Tratamiento guardado");
            alert.setContentText(
                "Tratamiento: " + seleccionado.getNameService()
                + "\nPrecio: Q" + seleccionado.getPrecio()
            );
            alert.showAndWait();

        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo guardar el tratamiento");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

