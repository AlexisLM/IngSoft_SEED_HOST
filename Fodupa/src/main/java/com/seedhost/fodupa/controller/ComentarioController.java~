/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Comentario;
import com.seedhost.fodupa.model.ComentarioJpaController;
import com.seedhost.fodupa.model.ComentarioPK;
import com.seedhost.fodupa.model.Pregunta;
import com.seedhost.fodupa.model.PreguntaJpaController;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;

/* Vista */
import com.seedhost.fodupa.web.ComentarioBean;

import java.io.Serializable;
import java.util.Locale;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author rodd
 */
@ManagedBean
@SessionScoped
public class ComentarioController implements Serializable {

    private EntityManagerFactory emf;
    private UsuarioJpaController u_jpaController;
    private PreguntaJpaController p_jpaController;
    private List<Comentario> comentarios;
    private ComentarioJpaController c_jpaController;
    private ComentarioBean comentario_bean;

    /**
     * Creates a new instance of ComentarioController
     */
    @PostConstruct
    private void init() {
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();

        //Inicializamos el comentario_bean
        comentario_bean = new ComentarioBean();

        //Obtenemos las preguntas
        c_jpaController = new ComentarioJpaController(emf);
        comentarios = c_jpaController.findComentarioEntities();
        Collections.reverse(comentarios);

        //Obtenemos el usuario actual (Esto es del caso de uso de Fer)
        FacesContext context = getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(usuario == null) {
            u_jpaController = new UsuarioJpaController(emf);
            usuario = u_jpaController.findUsuario(1);
            context.getExternalContext().getSessionMap().put("usuario", usuario);
        }

        //Obtenemos la pregunta actual (Esto es del caso de uso de Alexis)
        Pregunta pregunta = (Pregunta) context.getExternalContext().getSessionMap().get("pregunta");
        if(pregunta == null) {
            p_jpaController = new PreguntaJpaController(emf);
            pregunta = p_jpaController.findPregunta(5);
            context.getExternalContext().getSessionMap().put("pregunta", pregunta);
        }
    }
    
    public String createComentario() throws Exception {
                
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();
        
        Usuario usuario = (Usuario)context.getExternalContext().getSessionMap().get("usuario");
        Pregunta pregunta = (Pregunta)context.getExternalContext().getSessionMap().get("pregunta");
        ComentarioPK comentarioPK = new ComentarioPK();

        comentarioPK.setFecha(new Date());
        comentarioPK.setIdpregunta(pregunta.getId());
        comentarioPK.setIdusuario(usuario.getId());

        Comentario comentario = new Comentario(comentarioPK);
        comentarios.add(0, comentario); //Agrega el nuevo comentario a la lista de comentarios

        comentario.setContenido(comentario_bean.getContenido());
        comentario.setPregunta(pregunta);
        comentario.setUsuario(usuario);
        
        c_jpaController = new ComentarioJpaController(emf);
        c_jpaController.create(comentario); //create

        clear();

        return "index?faces-redirect=true";
    }

    public void clear() {
        comentario_bean.setContenido(null);
    }

    public ComentarioBean getComentario() {
        return comentario_bean;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentario(ComentarioBean comentario) {
        this.comentario_bean = comentario;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

}