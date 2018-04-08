/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alexis
 */
@ManagedBean
@SessionScoped
public class PreguntaBean {

    private String titulo;
    private String categoria;
    private String detalles;
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    public String getDetalles(){
        return detalles;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    public void setDetalles(String detalles){
        this.detalles = detalles;
    }
    
}
