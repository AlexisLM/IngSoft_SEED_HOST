/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Categoria;
import com.seedhost.fodupa.model.CategoriaJpaController;
import com.seedhost.fodupa.model.Comentario;
import com.seedhost.fodupa.model.ComentarioJpaController;
import com.seedhost.fodupa.model.Pregunta;
import com.seedhost.fodupa.model.PreguntaJpaController;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.model.exceptions.IllegalOrphanException;
import com.seedhost.fodupa.model.exceptions.NonexistentEntityException;

/* Vista */
import com.seedhost.fodupa.web.PreguntaBean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.Collections;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author alexis
 */

@ManagedBean
@ViewScoped
public class PreguntaController implements Serializable {

    private EntityManagerFactory emf;
    private CategoriaJpaController cJpaController;
    private UsuarioJpaController ujpaController;
    private PreguntaJpaController pJpaController;
    private List<Categoria> categorias;
    private List<Pregunta> preguntas;
    private PreguntaBean preguntaBean;

    /**
     * Creates a new instance of PreguntaController.
     */
    public PreguntaController() {

        FacesContext.getCurrentInstance().getViewRoot()
                    .setLocale(newLocale("es-Mx"));
        emf = EntityProvider.provider();

        //Obtenemos las categorias
        cJpaController = new CategoriaJpaController(emf);
        categorias = cJpaController.findCategoriaEntities();

        // Mandamos la categoria Otros al final.
        Categoria otros = null;
        for (Iterator<Categoria> it = categorias.iterator(); it.hasNext();) {
            Categoria c = it.next();
            if (c.getNombre().equals("Otros")) {
                it.remove();
                otros = c;
            }
        }
        categorias.add(otros);

        //Obtenemos las preguntas
        pJpaController = new PreguntaJpaController(emf);
        preguntas = pJpaController.findPreguntaEntities();
        Collections.reverse(preguntas);

        //Inicializamos la preguntaBean
        preguntaBean = new PreguntaBean();

//Obtenemos el usuario actual (Esto es del caso de uso de Fer)
//        FacesContext context = getCurrentInstance();
//        Usuario usuario = (Usuario) context.getExternalContext()
//                                           .getSessionMap().get("usuario");
//        if (usuario == null) {
//            ujpaController = new UsuarioJpaController(emf);
//            usuario = ujpaController.findUsuario(2);
//            context.getExternalContext().getSessionMap().put("adm", usuario);
//            context.getExternalContext().getSessionMap()
//                                            .put("usuario", usuario);
//        }
    }

    /**
     * Creates a new pregunta.
     */
    public String createPregunta() {
        if (!validateTitulo()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "
                    + "Lo sentimos, el títlo sólo no admite el símbolo "
                    + getTitleInvalidChars(), ""));
            return null;
        }

        if (!validateDetalles()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "
                    + "Lo sentimos, el campo de detalles no admite el "
                    + "símbolo" + getDetallesInvalidChars(), ""));
            return null;
        }

        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();

        Pregunta pregunta = new Pregunta();
        Categoria categoria = new Categoria();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap()
                                    .get("usuario");

        usuario = usuario == null ? (Usuario) context.getExternalContext().
            getSessionMap().get("adm") : usuario;

        String[] idNomCategoria = preguntaBean.getCategoria().split(":");
        int id = Integer.parseInt(idNomCategoria[0]);

        categoria.setId(id);
        categoria.setNombre(idNomCategoria[1]);

        pregunta.setTitulo(preguntaBean.getTitulo());
        pregunta.setIdcategoria(categoria);
        pregunta.setDescripcion(preguntaBean.getDetalles());
        pregunta.setFecha(new Date());
        pregunta.setIdusuario(usuario);

        pJpaController = new PreguntaJpaController(emf);
        pJpaController.create(pregunta);

        clear();

        return "index?faces-redirect=true";
    }

    public void clear() {
        preguntaBean.setTitulo(null);
        preguntaBean.setCategoria(null);
        preguntaBean.setDetalles(null);
        return;
    }

    private boolean validateTitulo() {
        return preguntaBean.getTitulo().matches(
            "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¿?!¡\"] {2,}"
            + "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\\s\"] {0,48}$");
    }

    private boolean validateDetalles() {
        return preguntaBean.getDetalles()
        .matches("^[A-Za-z0-9\\s¿?+ -_.*/\\ {}()%&#"
        + "\"$@|!¡;,:áé\\níóúÁÉÍÓÚñÑ\"] {0,}$");
    }

    private String getTitleInvalidChars() {
        return preguntaBean.getTitulo().replaceAll("[A-Za-z0-9áéíóúÁÉÍÓÚñÑ"
            + "¿?!¡\"]{2,}[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\\s\"] {0,48}", "");
    }

    private String getDetallesInvalidChars() {
        return preguntaBean.getDetalles().replaceAll(
        "[A-Za-z0-9\\s¿?+ -_.*/\\{}()%&#\"$@|!¡;,:áé\\níóúÁÉÍÓÚñÑ\"] {0,}","");
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public PreguntaBean getPregunta() {
        return preguntaBean;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void setPregunta(PreguntaBean pregunta) {
        this.preguntaBean = pregunta;
    }

    public String deletePregunta(Pregunta pregRef)
    throws IllegalOrphanException, NonexistentEntityException {
        pJpaController = new PreguntaJpaController(emf);
        ComentarioJpaController comJpaController =
                                            new ComentarioJpaController(emf);
        List<Comentario> comentarios = pregRef.getComentarioList();
        for (Comentario coment : comentarios) {
            comJpaController.destroy(coment.getComentarioPK());
        }
        pJpaController.destroy(pregRef.getId());
        return "index?faces-redirect=true";
    }

}
