/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import static javax.faces.context.FacesContext.getCurrentInstance;

import com.seedhost.fodupa.model.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author adriana
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    private String usuario;
    private String contrasena;
    private String confirmacionContraseña;
    private String correo;
    
        /**
     * Creates a new instance of UserBean
     */
    public UsuarioBean() {
    }

    public boolean isLogged() {
        System.out.println("Entro a logged");
        FacesContext context = getCurrentInstance();
        Usuario l = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        return l != null;
    }

    public Usuario getUsuario() {
        FacesContext context = getCurrentInstance();
        return (Usuario) context.getExternalContext().getSessionMap().get("usuario");
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getConfirmacionContrasena() {
        return confirmacionContraseña;
    }

    public void setConfirmacionContrasena(String confirmacionContraseña) {
        this.confirmacionContraseña = confirmacionContraseña;
    }
}
