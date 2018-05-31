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
    private UsuarioJpaController uJpaController;
    private List<Usuario> usuarios;
    private UsuarioBean usuario;

    /**
     * Creates a new instance of UsuarioController.
     */
    public UsuarioController() {

        FacesContext.getCurrentInstance().getViewRoot()
                    .setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        //Obtenemos los usuarios
        uJpaController = new UsuarioJpaController(emf);
        usuarios = uJpaController.findUsuarioEntities();
        //Inicializamos el usuario bean 
        usuario = new UsuarioBean();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public String sendUsuarios() {
        return "/views/admUsuario?faces-redirect=true";
    }

    public String deleteUsuario(Usuario uRef)
        throws IllegalOrphanException, NonexistentEntityException {
        System.out.println(uRef.toString());
        List<Pregunta> preguntas = uRef.getPreguntaList();
        PreguntaController controlerP = new PreguntaController();
        for (Pregunta p : preguntas) {
            controlerP.deletePregunta(p);
        }  

        List<Comentario> comentarios =  uRef.getComentarioList();
        ComentarioController controlerC = new ComentarioController();
        for (Comentario c : comentarios) {
            controlerC.deleteComentario(c);
        }

        uJpaController.destroy(uRef.getId());
        System.out.println("salio del metodo delete usuario");
        return "/views/admUsuario?faces-redirect=true"; 
    }

    public String deleteComentarios(Usuario uRef)
        throws IllegalOrphanException, NonexistentEntityException {        
        List<Comentario> comentarios =  uRef.getComentarioList();
        ComentarioController controlerC = new ComentarioController();
        for (Comentario c : comentarios) {
            controlerC.deleteComentario(c);
        }
        return "/views/admUsuario?faces-redirect=true";
    }

    public String deletePreguntas(Usuario uRef)
        throws IllegalOrphanException, NonexistentEntityException {
        List<Pregunta> preguntas = uRef.getPreguntaList();
        PreguntaController controlerP = new PreguntaController();
        for (Pregunta p : preguntas) {
            controlerP.deletePregunta(p);
        }  
        return "/views/admUsuario?faces-redirect=true"; 
    }
    
}
