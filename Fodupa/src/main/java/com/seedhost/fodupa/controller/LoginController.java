/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.controller;

import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.web.UsuarioBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adrisan
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {        
    private EntityManagerFactory emf;
    private UsuarioJpaController usuarioJpaController;
    private UsuarioBean usuarioBean;
    private boolean error = false;
    private String mensajeErrorCorreo = "";

    @PostConstruct
    private void init() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(
                                                                 "es-Mx"));
        emf = EntityProvider.provider();
        usuarioJpaController = new UsuarioJpaController(emf);
        usuarioBean = new UsuarioBean();
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuario) {
        this.usuarioBean = usuario;
    }

    /**
     * Recibe una bandera y apartir de ésta toma los parámetros para iniciar 
     * sesión.
     * @param token la bandera. Busca el usuario por token en caso de que sea
     * true, por correo y contraseña en otro caso.
     * @return Inicio de sesión
     */
    public String canLogin(boolean token) {
        System.out.println("TOKEN LOGIN CONTROLLER");
        if (token) {
            System.out.println(token);
        } else {
            System.out.println("NULLLLLLLLLLLL");
        }
        Usuario l;
        //Meter el getSha de registra controller pasar resultado a find login
        if (!token) {
            String con = RegistraController.getSha256(usuarioBean
                                                      .getContrasena());
            l = usuarioJpaController.findLogin(usuarioBean.getCorreo(), con);
        } else {
            //Busca usuario por token (del link de confirmación)
            l = usuarioJpaController.findUsuarioByToken();
            //Indica que el usuario es válido ya que confirmó su cuenta.
            l.setValido(true);
        }

        boolean logged = l != null;
        if (logged) {
            FacesContext context = getCurrentInstance();
            this.mensajeErrorCorreo = "";
            error = false;
            if (l.getCorreo().equals("alexis-blm@ciencias.unam.mx")) {
                context.getExternalContext().getSessionMap().put("adm", l);
            } else {
                context.getExternalContext().getSessionMap().put("usuario", l);
            }
            //context.getExternalContext().getSessionMap().put("datos", u);
            //Sólo cambia la cabecera indicando que esta la sesión iniciada con
            //los botones "Perfil" y "Cerrar Sesión".
            //return "/views/header_sesion?faces-redirect=true";
            return "";
        } else {
            this.mensajeErrorCorreo = "Error! Ingresaste un correo y "
                                      + "contraseña incorrectas";
            this.error = true;
            return "";
        }
    }

    public void existeCorreo() {
        if (usuarioJpaController.findByCorreo(usuarioBean.getCorreo())) {
            this.mensajeErrorCorreo = "";
            this.error = false;
        } else {
            this.mensajeErrorCorreo = "Error! Ingresaste un correo no "
                                      + "registrado.";
            this.error = true;
        }
    }

    public String errorCorreo() {
        return this.mensajeErrorCorreo;
    }

    public boolean error() {
        return this.error;
    }

    public String logout() {
        FacesContext context = getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public boolean isLogged() {
        FacesContext context = getCurrentInstance();
        Usuario l = (Usuario) context.getExternalContext().getSessionMap()
            .get("usuario");
        return l != null;
    }

    public Usuario getUsuario() {
        FacesContext context = getCurrentInstance();
        return (Usuario) context.getExternalContext().getSessionMap()
                                                     .get("usuario");
    }

    public boolean isAdmin() {
        FacesContext context = getCurrentInstance();
        Usuario l = (Usuario) context.getExternalContext().getSessionMap()
                                                          .get("adm");
        return l != null;
    }

    public String sendUsuarios() {
        return "/views/admUsuario?faces-redirect=true";
    }
}