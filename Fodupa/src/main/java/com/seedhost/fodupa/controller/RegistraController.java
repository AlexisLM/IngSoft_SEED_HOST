package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Carrera;
import com.seedhost.fodupa.model.CarreraJpaController;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;

/* Vista */
import com.seedhost.fodupa.web.RegistraBean;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;


/**
 *
 * @author fergch97
 */

@ManagedBean
@SessionScoped
public class RegistraController implements Serializable {

    private EntityManagerFactory emf;
    private CarreraJpaController c_jpaController;
    private UsuarioJpaController u_jpaController;
    private List<Carrera> carreras;
    private RegistraBean registra_bean;

    /**
     * Creates a new instance of RegistraController
     */
    @PostConstruct
    private void init() {
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        emf = EntityProvider.provider();
        
        //Obtenemos las carreras
        c_jpaController = new CarreraJpaController(emf);
        carreras = c_jpaController.findCarreraEntities();
        
        //Inicializamos el registra_bean
        registra_bean = new RegistraBean();
        
        //Obtenemos el usuario actual (Esto es del caso de uso de Fer)
        /*FacesContext context = getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap()
                                    .get("usuario");
        if(usuario == null){
            u_jpaController = new UsuarioJpaController(emf);
            usuario = u_jpaController.findUsuario(1);
            context.getExternalContext().getSessionMap().put("usuario", usuario);
        }*/
    }
    
    
    public void createRegistro(){
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();
        
        Usuario usuario = new Usuario();
        Carrera carrera = new Carrera();
        
        /*Usuario usuario = (Usuario) context.getExternalContext().getSessionMap()
                                    .get("usuario");
        */
        /*String[] id_nom_categoria = pregunta_bean.getCategoria().split(":");
        int id = Integer.parseInt(id_nom_categoria[0]);
        */
        
        String confirm = registra_bean.getConfirm();
        byte[] foto = registra_bean.getFoto();

//        //Take the default user image.
        if(foto == null){
            String src = "../../../../../webapp/resources/imgs/default_user.png";
            File file = new File(src);
            
            try{
                FileInputStream fis = new FileInputStream(file);
                //create FileInputStream which obtains input bytes from a file in a file system

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                try {
                    for (int readNum; (readNum = fis.read(buf)) != -1;) {
                        //Writes to this byte array output stream
                        bos.write(buf, 0, readNum); 
                        System.out.println("read " + readNum + " bytes,");
                    }
                } catch (IOException ex){}
                
                foto = bos.toByteArray();
            }catch(FileNotFoundException e){}   
        }
                
        usuario.setNombre(registra_bean.getNombre());
        usuario.setApPaterno(registra_bean.getApPaterno());
        usuario.setApMaterno(registra_bean.getApMaterno());
        usuario.setCorreo(registra_bean.getCorreo());
        usuario.setContrasena(registra_bean.getContrasena());
        usuario.setCarrera(registra_bean.getCarrera());
        usuario.setFoto(foto);
        
        usuario.setConfirm(confirm);
        
        if(usuario.equalsContrasenia()){
            u_jpaController = new UsuarioJpaController(emf);
            u_jpaController.create(usuario);
        }
        
    }
    
    public List<Carrera> getCarreras() {
        return carreras;
    }

    public RegistraBean getRegistra(){
        return registra_bean;
    }
    
    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
    
    public void setRegistra(RegistraBean registra){
        this.registra_bean = registra;
    }
}