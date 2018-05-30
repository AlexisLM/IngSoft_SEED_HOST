/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import com.seedhost.fodupa.model.Carrera;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;
//import org.apache.myfaces.custom.fileupload.UploadedFile;


/**
 *
 * @author fergch97
 */
@ManagedBean
@RequestScoped
public class PerfilBean{
    
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String contrasena;
    private String confirm;
    private UploadedFile uploadedFile;
    private byte[] foto;
    private Carrera carrera;
    private ArrayList<Carrera> carreras;
    
    public String getNombre(){
        return nombre;
    }

    public String getApPaterno(){
        return apPaterno;
    }
    public String getApMaterno(){
        return apMaterno;
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
    
    public UploadedFile getUploadedFile(){
        return uploadedFile;
    }
    
    public Carrera getCarrera(){
        return carrera;
    }
    
    public ArrayList<Carrera> getCarreras(){
        return carreras;
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
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    public void setConfirm(String confirm){
        this.confirm = confirm;
    }
    
    public void setFoto(byte[] foto){
        this.foto = foto;
    }
    
    public void setUploadedFile(UploadedFile foto){
        System.out.println("\n\n\n\n\n\nENTRA \n\n\n\n\n\n");
        this.uploadedFile = foto;
    }
    
    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
        setCarreras();
    }
    
    private void setCarreras(){
        System.out.println("\n\n\n\n\n\nENTRA Cn\n\n\n\n\n");
        if(carreras == null)
            carreras = new ArrayList<>();
        carreras.add(carrera);

    }
    
    
    public void save(){
        if(uploadedFile != null){
            foto = uploadedFile.getContents();
        }else
            System.out.println("\n\n\n\n\n\nERROR AL OBTENER IMAGEN UPLOADD\n\n\n\n\n\n");

        foto = null;
    }
    
    /**
     * Método que obtiene la ruta de los archivos
     * @return la ruta del directorio.
     */
    private static String getRuta(){
        try{
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            return ctx.getRealPath("/");
        }catch(Exception e){
            System.out.println("Error en obtener ruta");
        }
        return null;
    }
}
