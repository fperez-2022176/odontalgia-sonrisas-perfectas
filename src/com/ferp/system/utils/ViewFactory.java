/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ferp.system.utils;

import com.ferp.system.Principal;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;

/**
 *
 * @author informatica
 */
public class ViewFactory {
    private final String PATH_VIEWS = "/com/ferp/system/view/";
    
    public Scene loadFileFXML(String nameFile, int width, int height){
        String pathOFFile = PATH_VIEWS + nameFile;
        try {
            FXMLLoader loadFXML = new FXMLLoader();
            
            URL urlFile = Principal.class.getResource(pathOFFile);
            loadFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loadFXML.setLocation(urlFile);
            
            return new javafx.scene.Scene(loadFXML.load(), width, height);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public void loadScene(String nameFile){
        Scene scene = null;
        try {
            switch (nameFile) {
                case "login" -> scene = loadFileFXML("LoginView.fxml",400,500);
                
                default      -> scene = loadFileFXML("LoginView.fxml",0,0); 
            }
            SceneManager.getInstanciaSceneManager().changeScene(scene);
        } catch (NullPointerException e) {
            System.out.println("Error load scene");
        }
    }
    
    public void viewLogin(){
        loadScene("login");
    }
}
