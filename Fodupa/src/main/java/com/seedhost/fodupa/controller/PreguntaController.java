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
import com.seedhost.fodupa.model.Pregunta;
import com.seedhost.fodupa.model.PreguntaJpaController;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;

/* Vista */
import com.seedhost.fodupa.web.PreguntaBean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author alexis
 */

@ManagedBean
@SessionScoped
public class PreguntaController implements Serializable {

    private EntityManagerFactory emf;
    private CategoriaJpaController c_jpaController;
    private UsuarioJpaController u_jpaController;
    private PreguntaJpaController p_jpaController;
    private List<Categoria> categorias;
    private PreguntaBean pregunta_bean;
    
    /**
     * Creates a new instance of PreguntaController
     */
    @PostConstruct
    private void init() {
        
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
        
        //Inicializamos la pregunta_bean
        pregunta_bean = new PreguntaBean();
        
        //Obtenemos el usuario actual (Esto es del caso de uso de Fer)
        FacesContext context = getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap()
                                    .get("usuario");
        if(usuario == null){
            u_jpaController = new UsuarioJpaController(emf);
            usuario = u_jpaController.findUsuario(1);
            context.getExternalContext().getSessionMap().put("usuario", usuario);
        }
    }
    
    
    public void createPregunta(){
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();
        
        Pregunta pregunta = new Pregunta();
        Categoria categoria = new Categoria();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap()
                                    .get("usuario");
        
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
        
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public PreguntaBean getPregunta(){
        return pregunta_bean;
    }
    
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public void setPregunta(PreguntaBean pregunta){
        this.pregunta_bean = pregunta;
    }
}
