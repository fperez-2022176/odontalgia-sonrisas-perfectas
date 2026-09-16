
package com.ferp.system;

import com.ferp.system.utils.SceneManager;
import com.ferp.system.utils.ViewFactory;
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

    
    SceneManager.getInstanciaSceneManager().setStagePrincipal(escenarioPrincipal);
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
}
