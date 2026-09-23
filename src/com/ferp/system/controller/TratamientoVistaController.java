package com.ferp.system.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.ferp.system.dao.TratamientoDAO;
import com.ferp.system.model.Tratamiento;
import com.ferp.system.utils.AlertasCatalogo;
import com.ferp.system.utils.ViewFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TratamientoVistaController implements Initializable {

    @FXML
    private TableView<Tratamiento> tablaTratamientos;
    @FXML
    private TableColumn<Tratamiento, String> colCodigo;
    @FXML
    private TableColumn<Tratamiento, String> colNombre;
    @FXML
    private TableColumn<Tratamiento, String> colDescripcion;
    @FXML
    private TableColumn<Tratamiento, Double> colCosto;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtCosto;
    @FXML
    private Label lblAvisoAdmin;

    private final ObservableList<Tratamiento> tratamientos = FXCollections.observableArrayList();
    private final TratamientoDAO tratamientoDAO = new TratamientoDAO();
    private final ViewFactory viewFactory = new ViewFactory();

 @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        
        tablaTratamientos.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        if (lblAvisoAdmin != null) {
            lblAvisoAdmin.setVisible(false);
            lblAvisoAdmin.setManaged(false);
        }

        cargarDatosDesdeBaseDeDatos();
        tablaTratamientos.setItems(tratamientos);

        
        tablaTratamientos.setOnMouseClicked(event -> {
            ObservableList<Tratamiento> seleccionados = tablaTratamientos.getSelectionModel().getSelectedItems();
            if (seleccionados.size() == 1) {
                cargarEnFormulario(seleccionados.get(0));
            }
        });
    }

    private void cargarDatosDesdeBaseDeDatos() {
        tratamientos.clear();
        List<Tratamiento> listaDB = tratamientoDAO.obtenerServicios();
        if (listaDB != null) {
            tratamientos.addAll(listaDB);
        }
    }

  @FXML
    private void onGuardar(MouseEvent event) {
        try {
            
            String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText() == null ? "" : txtDescripcion.getText().trim();
            String costoTexto = txtCosto.getText() == null ? "" : txtCosto.getText().trim();

           
            if (nombre.isEmpty() || descripcion.isEmpty() || costoTexto.isEmpty()) {
                AlertasCatalogo.mostrarAdvertencia("Campos incompletos", "Todos los campos (Nombre, Descripción y Costo) son obligatorios.");
                return;
            }

            double costo;
            try {
                costo = Double.parseDouble(costoTexto.replace(",", "."));
                if (costo <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                AlertasCatalogo.mostrarAdvertencia("Costo inválido", "El costo debe ser un número mayor a cero.");
                return;
            }

            Tratamiento nuevo = new Tratamiento("0", nombre, descripcion, costo);

            if (tratamientoDAO.insertarServicio(nuevo)) {
                
                cargarDatosDesdeBaseDeDatos();
                
                AlertasCatalogo.mostrarExito("Servicio Guardado",
                        String.format("Se registró correctamente en la base de datos:%n- Nombre: %s%n- Costo: Q%.2f",
                                nuevo.getNombre(), nuevo.getCosto()));

                limpiarCampos();
            } else {
                AlertasCatalogo.mostrarError("Error", "No se pudo guardar el servicio en la base de datos.");
            }

        } catch (Exception e) {
            AlertasCatalogo.mostrarError("Error crítico", "Ocurrió un error: " + e.getMessage());
        }
    }

    @FXML
    private void onActualizar(MouseEvent event) {
        ObservableList<Tratamiento> seleccionados = tablaTratamientos.getSelectionModel().getSelectedItems();
        if (seleccionados.isEmpty()) {
            AlertasCatalogo.mostrarAdvertencia("Sin selección", "Selecciona al menos un tratamiento para procesar.");
            return;
        }

        if (seleccionados.size() > 1) {
            StringBuilder sb = new StringBuilder("Ha seleccionado " + seleccionados.size() + " tratamientos:\n");
            double total = 0;
            for (Tratamiento t : seleccionados) {
                sb.append(String.format("• [%s] %s (Q%.2f)%n", t.getCodigo(), t.getNombre(), t.getCosto()));
                total += t.getCosto();
            }
            sb.append(String.format("%nCosto Total Acumulado: Q%.2f", total));
            AlertasCatalogo.mostrarExito("Resumen de Selección Múltiple", sb.toString());
        } else {
            AlertasCatalogo.mostrarExito("Tratamiento Seleccionado", "Tratamiento único seleccionado: " + seleccionados.get(0).getNombre());
        }
    }

    @FXML
    private void onLimpiar(MouseEvent event) {
        limpiarCampos();
    }

    @FXML
    private void onVolver(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ferp/system/view/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tablaTratamientos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sonrisas Perfectas - Login");
            stage.show();
        } catch (Exception e) {
            AlertasCatalogo.mostrarError("Error de navegación", "No se pudo regresar al login: " + e.getMessage());
        }
    }

    private void cargarEnFormulario(Tratamiento t) {
        if (t == null) {
            return;
        }
        txtCodigo.setText(t.getCodigo());
        txtNombre.setText(t.getNombre());
        txtDescripcion.setText(t.getDescripcion());
        txtCosto.setText(String.valueOf(t.getCosto()));
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtCosto.clear();
        tablaTratamientos.getSelectionModel().clearSelection();
    }
}
