/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private EntityManagerFactory emf;
    private LoginJpaController jpaController;
    private Usuario usuario;

    public LoginController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        jpaController = new LoginJpaController(emf);
        usuario = new Usuario();
    }

    public Usuario getusuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String canLogin() {
        Login l = jpaController.findLogin(usuario.getCorreo(), usuario.getContrasena());
        boolean logged = l != null;
        if (logged) {
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", l);
            return "secured/inicio?faces-redirect=true";
        }
        return "content?faces-redirect=true";
    }
    
    public String registra(){
        return "registra?faces-redirect=true";
    }

    public String logout() {
        FacesContext context = getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}
