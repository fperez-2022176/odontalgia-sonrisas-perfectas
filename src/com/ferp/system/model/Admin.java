/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ferp.system.model;

/**
 *
 * @author informatica
 */
public class Admin {
    private String nameClave;
    private String passAdmin;
    
    public Admin(){
        
    }
    
    public Admin(String nameClave, String passAdmin){
        this.nameClave = nameClave;
        this.passAdmin = passAdmin;
    }

    public String getNameClave() {
        return nameClave;
    }

    public void setNameClave(String nameClave) {
        this.nameClave = nameClave;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }
    
    
    
    
}
