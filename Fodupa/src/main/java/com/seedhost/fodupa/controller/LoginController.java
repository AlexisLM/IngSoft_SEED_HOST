/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import java.io.Serializable;
import java.util.Locale;

/* Vista */
import com.seedhost.fodupa.web.UsuarioBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author adriana
 */

@ManagedBean
@ViewScoped
public class LoginController implements Serializable {
    private final EntityManagerFactory emf;
    private final UsuarioJpaController usuarioJpaController;
    private UsuarioBean usuario_bean;
    
    public LoginController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        usuarioJpaController = new UsuarioJpaController(emf);
        usuario_bean = new UsuarioBean();
    }
    
    public UsuarioBean getusuario() {
        return usuario_bean;
    }
    
    public void setUsuario(UsuarioBean usuario) {        
        this.usuario_bean = usuario;
    }
    
    public String canLogin() {
        Usuario l = usuarioJpaController.findLogin(usuario_bean.getCorreo(), usuario_bean.getContrasena());
        boolean logged = l != null;
        if (logged) {
            Usuario u = usuarioJpaController.findUsuarioByLoginId(l.getId());
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", u);
            context.getExternalContext().getSessionMap().put("datos", u);
            return "index?faces-redirect=true";
        }
        return "index?faces-redirect=true";
    }
    
    public String logout() {
        FacesContext context = getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
    
    public boolean isLogged() {
        FacesContext context = getCurrentInstance();
        Usuario l = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        return l != null;
    }

    public Usuario getUsuario() {
        FacesContext context = getCurrentInstance();
        return (Usuario) context.getExternalContext().getSessionMap().get("usuario");
    }
}
