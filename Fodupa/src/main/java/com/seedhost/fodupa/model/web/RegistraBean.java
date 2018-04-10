/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import com.seedhost.fodupa.model.Carrera;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author fergch97
 */
@ManagedBean
@SessionScoped
public class RegistraBean{
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private String contrasena;
    private String confirm;
    private byte[] foto;
    private Carrera carrera;
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApPaterno(){
        return apPaterno;
    }
    public String getApMaterno(){
        return apMaterno;
    }
    public String getCorreo(){
        return correo;
    }
    public String getContrasena(){
        return contrasena;
    }
    public String getConfirm(){
        return confirm;
    }
    public byte[] getFoto(){
        return foto;
    }
    public Carrera getCarrera(){
        return carrera;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setApPaterno(String apPaterno){
        this.apPaterno = apPaterno;
    }
    public void setApMaterno(String apMaterno){
        this.apMaterno = apMaterno;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    public void setContrasena(String contraseña){
        this.contrasena = contrasena;
    }
    public void setConfirm(String confirm){
        this.confirm = confirm;
    }
    public void setFoto(byte[] foto){
        this.foto = foto;
    }
    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
    }
    
}
