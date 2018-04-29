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
    private UsuarioBean usuario_bean;
    private boolean error = false;
    private String mensajeErrorCorreo = "";
    
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
        System.out.println("entro a canLogin");
        System.out.println(usuario_bean.getCorreo() + "   " + usuario_bean.getContrasena());
        Usuario l = usuarioJpaController.findLogin(usuario_bean.getCorreo(), usuario_bean.getContrasena());
        boolean logged = l != null;
        if (logged) {
            Usuario u = usuarioJpaController.findUsuarioByLoginId(l.getId());
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", u);
            this.mensajeErrorCorreo = "";
            error = false;
//            context.getExternalContext().getSessionMap().put("datos", u);
            return "index?faces-redirect=true";
        }else{
            this.mensajeErrorCorreo = "Error! Ingresaste un correo y contraseña incorrectas";
            this.error = true;
            return "";
        }
        
    }
    
    public void existeCorreo(){
        System.out.println("entro a existe correo");
        if(usuarioJpaController.findByCorreo(usuario_bean.getCorreo())){
            System.out.println("entro a no error");
            this.mensajeErrorCorreo = "";
            this.error = false;
        }else{
            System.out.println("entro a error");
            this.mensajeErrorCorreo = "Error! Ingresaste un correo no registrado.";
            this.error = true;
        }
    }
    
    public String errorCorreo(){
        System.out.println("entro a obtener el mensaje  " + this.mensajeErrorCorreo + " <");
        return this.mensajeErrorCorreo;
    }
    
    public boolean error(){
        System.out.println("entro a revisar el metodo");
        return this.error;
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
