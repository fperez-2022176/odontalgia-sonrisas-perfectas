package com.ferp.system.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ferp.system.model.Tratamiento;
import com.ferp.system.model.TratamientoData;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MostrarInfoController implements Initializable {

    @FXML
    private TableView<Tratamiento> tablaServicios;

    @FXML
    private TableColumn<Tratamiento, String> colServicio;

    @FXML
    private TableColumn<Tratamiento, String> colDescripcion;

    @FXML
    private TableColumn<Tratamiento, Double> colCosto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colServicio.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );

        colCosto.setCellValueFactory(
                new PropertyValueFactory<>("costo")
        );

        ObservableList<Tratamiento> tratamientos =
                TratamientoData.getTratamientos();

        tablaServicios.setItems(tratamientos);
    }

    @FXML
    private void crearTratamiento(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ferp/system/view/CrearTratamiento.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle("Crear Tratamiento");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
