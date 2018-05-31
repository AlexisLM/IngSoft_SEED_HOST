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
import com.seedhost.fodupa.model.exceptions.NonexistentEntityException;

/* Vista */
import com.seedhost.fodupa.web.ComentarioBean;

import java.io.Serializable;
import java.util.Locale;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

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
    private UsuarioJpaController uJpaController;
    private PreguntaJpaController pJpaController;
    private List<Comentario> comentarios;
    private ComentarioJpaController cJpaController;
    private ComentarioBean comentarioBean;

    /**
     * Creates a new instance of ComentarioController.
     */
    public ComentarioController() {
        FacesContext.getCurrentInstance().getViewRoot()
                                         .setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();

        //Inicializamos el comentarioBean
        comentarioBean = new ComentarioBean();

        //Obtenemos las preguntas
        cJpaController = new ComentarioJpaController(emf);
        comentarios = cJpaController.findComentarioEntities();
        Collections.reverse(comentarios);

        //Obtenemos el usuario actual (Esto es del caso de uso de Fer)
        FacesContext context = getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext()
                                           .getSessionMap().get("usuario");
        usuario = usuario == null ? (Usuario) context.getExternalContext().
            getSessionMap().get("adm") : usuario;
//        if (usuario == null) {
//            uJpaController = new UsuarioJpaController(emf);
//            usuario = uJpaController.findUsuario(1);
//            context.getExternalContext().getSessionMap().put("adm", usuario);
//            context.getExternalContext().getSessionMap()
//                                        .put("usuario", usuario);
//        }

        //Obtenemos la pregunta actual (Esto es del caso de uso de Alexis)
        /*Pregunta pregunta = (Pregunta) context.getExternalContext()
                               .getSessionMap().get("pregunta");
        if (pregunta == null) {
            pJpaController = new PreguntaJpaController(emf);
            pregunta = pJpaController.findPregunta(5);
            context.getExternalContext().getSessionMap()
                                        .put("pregunta", pregunta);
        }*/
    }

    public String createComentario(Pregunta preguntaRef) throws Exception {
        FacesContext.getCurrentInstance().getViewRoot()
                                         .setLocale(new Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();

        Usuario usuario = (Usuario) context.getExternalContext()
                                           .getSessionMap().get("usuario");
        usuario = usuario == null ? (Usuario) context.getExternalContext()
                  .getSessionMap().get("adm") : usuario;
        Pregunta pregunta = preguntaRef;
        ComentarioPK comentarioPK = new ComentarioPK();

        comentarioPK.setFecha(new Date());
        comentarioPK.setIdpregunta(pregunta.getId());
        comentarioPK.setIdusuario(usuario.getId());

        Comentario comentario = new Comentario(comentarioPK);
        if (comentarios == null) {
            comentarios = new ArrayList<>();
        }
        //Agrega el nuevo comentario a la lista de comentarios
        comentarios.add(0,comentario);

        comentario.setContenido(comentarioBean.getContenido());
        comentario.setPregunta(pregunta);
        comentario.setUsuario(usuario);

        cJpaController = new ComentarioJpaController(emf);
        cJpaController.create(comentario); //create

        clear();

        return "index?faces-redirect=true";
    }

    public void clear() {
        comentarioBean.setContenido(null);
    }

    public ComentarioBean getComentario() {
        return comentarioBean;
    }

    public List<Comentario> getComentarios(Pregunta pregunta) {
        cJpaController = new ComentarioJpaController(emf);
        comentarios = cJpaController.findComentarioByPregunta(pregunta);
        return comentarios;
    }

    public void setComentario(ComentarioBean comentario) {
        this.comentarioBean = comentario;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String deleteComentario(Comentario comRef) throws NonexistentEntityException {
        cJpaController = new ComentarioJpaController(emf);
        cJpaController.destroy(comRef.getComentarioPK());

        return "index?faces-redirect=true";
    }

}
