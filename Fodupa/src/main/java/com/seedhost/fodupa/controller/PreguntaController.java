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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.Collections;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
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
    private CategoriaJpaController c_jpaController;
    private UsuarioJpaController u_jpaController;
    private PreguntaJpaController p_jpaController;
    private List<Categoria> categorias;
    private List<Pregunta> preguntas;
    private List<Pregunta> busqueda;
    private PreguntaBean pregunta_bean;
    private String search;
    
    /**
     * Creates a new instance of PreguntaController
     */
    @PostConstruct
    public void init() {
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        emf = EntityProvider.provider();
        
        //Obtenemos las categorias
        c_jpaController = new CategoriaJpaController(emf);
        categorias = c_jpaController.findCategoriaEntities();
        
        // Mandamos la categoria Otros al final.
        Categoria otros = null;
        for (Iterator<Categoria> it = categorias.iterator(); it.hasNext();) {
            Categoria c = it.next();
            if(c.getNombre().equals("Otros")){
                it.remove();
                otros = c;
            }
        }
        categorias.add(otros);

        //Obtenemos las preguntas
        p_jpaController = new PreguntaJpaController(emf);
        preguntas = p_jpaController.findPreguntaEntities();
        Collections.reverse(preguntas);
        
        //Inicializamos la pregunta_bean
        pregunta_bean = new PreguntaBean();

        searchPregunta();
        
    }
    
    
    public String createPregunta(){
        
        if(!validateTitulo()){
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error: Lo sentimos, el títlo sólo no admite el símbolo "+
                    getTitleInvalidChars(), ""));
            return null;
        }

        if(!validateDetalles()){
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error: Lo sentimos, el campo de detalles no admite el "+
                    "símbolo"+getDetallesInvalidChars(), ""));
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
        
        String[] id_nom_categoria = pregunta_bean.getCategoria().split(":");
        int id = Integer.parseInt(id_nom_categoria[0]);
        
        categoria.setId(id);
        categoria.setNombre(id_nom_categoria[1]);
        
        pregunta.setTitulo(pregunta_bean.getTitulo());
        pregunta.setIdcategoria(categoria);
        pregunta.setDescripcion(pregunta_bean.getDetalles());
        pregunta.setFecha(new Date());
        pregunta.setIdusuario(usuario);
        
        p_jpaController = new PreguntaJpaController(emf);
        p_jpaController.create(pregunta);
        
        clear();

        searchPregunta();
        
        return "index?faces-redirect=true";
    }

    public void clear(){
        pregunta_bean.setTitulo(null);
        pregunta_bean.setCategoria(null);
        pregunta_bean.setDetalles(null);
        return;
    }
    
    private boolean validateTitulo(){
        return pregunta_bean.getTitulo().matches("^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¿?!¡\"]{2,}"+
                                       "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\\s\"]{0,48}$");
    }

    private boolean validateDetalles(){
        return pregunta_bean.getDetalles().matches("^[A-Za-z0-9\\s¿?+-_.*/\\{}()%&#"+
                                              "\"$@|!¡;,:áé\\níóúÁÉÍÓÚñÑ\"]{0,}$");
    }

    private String getTitleInvalidChars(){
        return pregunta_bean.getTitulo().replaceAll("[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¿?!¡\"]{2,}"+
                                       "[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\\s\"]{0,48}","");
    }

    private String getDetallesInvalidChars(){
        return pregunta_bean.getDetalles().replaceAll("[A-Za-z0-9\\s¿?+-_.*/\\{}()%&#"+
                                              "\"$@|!¡;,:áé\\níóúÁÉÍÓÚñÑ\"]{0,}","");
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    public List<Pregunta> getBusqueda() {
        return busqueda;
    }

    public PreguntaBean getPregunta(){
        return pregunta_bean;
    }

    public String getSearch(){
        return search;
    }

    public void setSearch(String str){
        System.out.println(str);
        search = str;
    }
    
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void setPregunta(PreguntaBean pregunta){
        this.pregunta_bean = pregunta;
    }
    
    public String deletePregunta(Pregunta preg_ref) throws IllegalOrphanException, NonexistentEntityException{
        p_jpaController = new PreguntaJpaController(emf);
        ComentarioJpaController com_jpaController = new ComentarioJpaController(emf);        
        List<Comentario> comentarios = preg_ref.getComentarioList();
        for (Comentario coment : comentarios) {
            com_jpaController.destroy(coment.getComentarioPK());
        }
        p_jpaController.destroy(preg_ref.getId());
        
        searchPregunta();
        
        return "index?faces-redirect=true";
    }

    public String findPregunta(){
        //System.out.println("find: null");
        if(search.equals(""))
            return "";
        System.out.println("find: "+search);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put("search", search);
        return "/views/busqueda?faces-redirect=true";
    }
    
    private void searchPregunta(){
        search = (String) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("search");
        
        if( search == null || (busqueda = p_jpaController
                .findPreguntaByTitleContent(search)) == null )
            busqueda = new ArrayList<Pregunta>();
    }
    
}
