
package com.ferp.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Principal extends Application {

 
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal)throws Exception {

    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/ferp/system/view/opciones.fxml")
    );

    Scene scene = new Scene(loader.load());

    escenarioPrincipal.setScene(scene);
    escenarioPrincipal.setTitle("Sistema");
    escenarioPrincipal.show();

    
    }
}
