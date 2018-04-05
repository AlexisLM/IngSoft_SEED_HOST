/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Categoria;
import com.seedhost.fodupa.model.CategoriaJpaController;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alexis
 */

@ManagedBean
@SessionScoped
public class CategoriaController implements Serializable {

    private EntityManagerFactory emf;
    private CategoriaJpaController jpaController;
    private List<Categoria> categorias;
    public int t;
    
    /**
     * Creates a new instance of CategoriaController
     */
    @PostConstruct
    private void init() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        jpaController = new CategoriaJpaController(emf);
        categorias = jpaController.findCategoriaEntities();
        if(categorias == null)
            t = 100;
        else
            t = categorias.size();
    }
    
    
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public int getT(){
        return t;
    }
    
}
