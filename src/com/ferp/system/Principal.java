
package com.ferp.system;

import com.ferp.system.utils.SceneManager;
import com.ferp.system.utils.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class Principal extends Application {

 
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
    SceneManager.getInstanciaSceneManager().setStagePrincipal(escenarioPrincipal);
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

}
