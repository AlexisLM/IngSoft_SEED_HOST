/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.controller;

import com.seedhost.fodupa.model.Comentario;
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Pregunta;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.model.exceptions.IllegalOrphanException;
import com.seedhost.fodupa.model.exceptions.NonexistentEntityException;

import com.seedhost.fodupa.web.UsuarioBean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adrisan
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    private EntityManagerFactory emf;
    private UsuarioJpaController u_jpaController;
    private List<Usuario> usuarios;
    private UsuarioBean usuario;
    
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        emf = EntityProvider.provider();
        
        //Obtenemos los usuarios
        u_jpaController = new UsuarioJpaController(emf);
        usuarios = u_jpaController.findUsuarioEntities();
        
        //Inicializamos el usuario bean 
        usuario = new UsuarioBean();
        
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public String sendUsuarios(){
        return "/views/admUsuario?faces-redirect=true";
    }
    
    public String deleteUsuario(Usuario u_ref) throws IllegalOrphanException, NonexistentEntityException{
        System.out.println(u_ref.toString());
        List<Pregunta> preguntas = u_ref.getPreguntaList();
        PreguntaController controlerP = new PreguntaController();
        for(Pregunta p: preguntas){
            controlerP.deletePregunta(p);
        }  
        
        List<Comentario> comentarios =  u_ref.getComentarioList();
        ComentarioController controlerC = new ComentarioController();
        for(Comentario c: comentarios){
            controlerC.deleteComentario(c);
        }
        
        u_jpaController.destroy(u_ref.getId());
        System.out.println("salio del metodo delete usuario");
        return "/views/admUsuario?faces-redirect=true"; 
    }
    
    public String deleteComentarios(Usuario u_ref) throws IllegalOrphanException, NonexistentEntityException{        
        List<Comentario> comentarios =  u_ref.getComentarioList();
        ComentarioController controlerC = new ComentarioController();
        for(Comentario c: comentarios){
            controlerC.deleteComentario(c);
        }
        return "/views/admUsuario?faces-redirect=true"; 
    }
    
    public String deletePreguntas(Usuario u_ref) throws IllegalOrphanException, NonexistentEntityException{
        List<Pregunta> preguntas = u_ref.getPreguntaList();
        PreguntaController controlerP = new PreguntaController();
        for(Pregunta p: preguntas){
            controlerP.deletePregunta(p);
        }  
        return "/views/admUsuario?faces-redirect=true"; 
    }
    
}
