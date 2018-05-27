/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.web;

import com.seedhost.fodupa.model.Carrera;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;


/**
 *
 * @author fergch97
 */

public class PerfilBean{
    
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String contrasena;
    private String confirm;
    private Part fotoP;
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
    
    public Part getFotoP(){
        return fotoP;
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
    
    public void setFotoP(Part foto){
        this.fotoP = foto;
    }
    
    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
        setCarreras();
    }
    
    private void setCarreras(){
        if(carreras == null)
            carreras = new ArrayList<>();
        carreras.add(carrera);

    }
        
    public void save() {
        if(fotoP != null)
            try (InputStream input = fotoP.getInputStream()) {
                this.foto = IOUtils.toByteArray(input);
            }
            catch (IOException e) {
                System.out.println("\n\n\n\n CARITA TRISTE: Error foto \n\n\n");
            }
    }

}
