/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import com.seedhost.fodupa.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author adrisan
 */

@SessionScoped
public class UsuarioBean implements Serializable {
    private String correo;    
    private String contrasena;
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    

}
