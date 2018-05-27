package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.Carrera;
import com.seedhost.fodupa.model.CarreraJpaController;
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.model.exceptions.NonexistentEntityException;
import com.seedhost.fodupa.web.PerfilBean;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;


/**
 *
 * @author fergch97
 */

@ManagedBean
@SessionScoped
public class PerfilController implements Serializable {

    private EntityManagerFactory emf;
    private CarreraJpaController c_jpaController;
    private UsuarioJpaController u_jpaController;
    private List<Carrera> carreras;
    private Usuario usuario;
    private Carrera carreraNula;
    private PerfilBean perfil_bean;
    private DefaultStreamedContent imagen;

    /**
     * Creates a new instance of PerfilController
     */
    @PostConstruct
    private void init() {
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new 
                                                            Locale("es-Mx"));
        emf = EntityProvider.provider();
        
        //Inicializa los controladores
        c_jpaController = new CarreraJpaController(emf);
        u_jpaController = new UsuarioJpaController(emf);
        
        //Lista de todas las carreras.
        carreras = c_jpaController.findCarreraEntities();
        
        //Inicializa carrera nula : equivalente a quitar una carrera.
        carreraNula = new Carrera(0);
        //Inicializa perfil_bean
        perfil_bean = new PerfilBean();
        
        
        //Obtenemos el usuario actual
        setUsuario();
        
//        System.out.println("%%%%%%%%%%%"+usuario.getFoto().toString()+"%%%%%%%%%\n\n\n\n");
        //Cargamos la imagen del usuario de la bd.
        imagen =  new DefaultStreamedContent(new ByteArrayInputStream(usuario.getFoto()),"image/png");
    }

    
    /**
     * Hace una actualización del perfil de acuerdo a los nuevos cambios hechos por el usuario.
     * @return re dirige a la página de perfil donde se despliega la información actualizada.
     */
    public String createPerfil(){

        FacesContext.getCurrentInstance().getViewRoot().setLocale(new
                                                            Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();
        
        String nombre = (perfil_bean.getNombre() == null || perfil_bean.getNombre().length() == 0) ? usuario.getNombre() : perfil_bean.getNombre();
        String apPat = (perfil_bean.getApPaterno() == null || perfil_bean.getApPaterno().length() == 0 ) ? usuario.getApPaterno() : perfil_bean.getApPaterno();
        String apMat = (perfil_bean.getApMaterno() == null || perfil_bean.getApMaterno().length() == 0) ? usuario.getApMaterno() : perfil_bean.getApMaterno();
        
        String contrasena = (perfil_bean.getContrasena() == null ) ? 
                                usuario.getContrasena() : 
                                getSha256(perfil_bean.getContrasena());
        perfil_bean.save();
        byte[] foto = (perfil_bean.getFoto() == null ) ? usuario.getFoto() : perfil_bean.getFoto();
        
        //Filtramos las carreras nulas.
        carreras.clear();
        for(Carrera carrera : perfil_bean.getCarreras())
            if(!carreraNula.equals(carrera) && carrera != null && !carreras.contains(carrera)){
                carreras.add(carrera);
                System.out.println(carrera.toString()+"\n\n");
            }
        
        Usuario usr = u_jpaController.findUsuario(usuario.getId());
        
        usr.setNombre(nombre);
        usr.setApPaterno(apPat);
        usr.setApMaterno(apMat);
        //Introduce la contraseña cifrada.
        usr.setContrasena(contrasena);
        usr.setCarreraList(carreras);
        usr.setFoto(foto);
        //Ya que no puede ser null
//        usuarioActualizado.setToken(usuario.getToken());
  //      usuarioActualizado.setValido(usuario.getValido());
        u_jpaController = new UsuarioJpaController(emf);
        try {
            System.out.println("\n\n\n\n\n SHIDO BANDA \n\n\n\n");
            u_jpaController.edit(usr);
        } catch (NonexistentEntityException ex) {
            System.out.println("\n\n\n\n\n 333333333 \n\n\n\n");
            System.out.println("Error no existe usuario.\n");
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("\n\n\n\n\n 3333333--33 \n\n\n\n");
            System.out.println("Error al editar usuario.\n");
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Lista de todas las carreras.
        carreras = c_jpaController.findCarreraEntities();
        setUsuario();
        return "/views/perfil?faces-redirect=true";
    }
    
    
    /**
     *  Aplica el hash md5 a la cadena input.
     * @param input cadena a cifrar.
     * @return el cifrado de una cadena de tamaño 65.
     */
    private static String getSha256(String input) {
        try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
           System.out.println("!!!!!!!!!!!!!!!ERROOOOR SHA256!!!!!!!!!!!!!!!");
           return null;
        }
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }
    
    public Carrera getCarreraNula(){
        return carreraNula;
    }
    
    public Carrera getCarrera(Integer id){
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Carrera carr : carreras){
            if (id.equals(carr.getId())){
                return carr;
            }
        }
        return null;
    }

    public PerfilBean getPerfil(){
        return perfil_bean;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public DefaultStreamedContent getImagen(){
        setImagen(); //Se hace ya que estamos tratando con una imagen dinámica.
        return imagen;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void setPerfil(PerfilBean perfil){
        this.perfil_bean = perfil;
    }
    
    public void setImagen(){
        this.imagen = new DefaultStreamedContent(new ByteArrayInputStream(usuario.getFoto()),"image/png");
    }


    public String modificaPerfilVista(){
        return "/views/modifica_perfil?faces-redirect=true";
    }
    
    public String perfilVista(){
        return "/views/perfil?faces-redirect=true";
    }
    
    /**
     * Recibe una foto en bytes y regresa el nombre de la foto en servidor.
     * @param foto
     * @return 
     */
    private DefaultStreamedContent obtieneImg(){
        String mimeType = "image/png";
        return new DefaultStreamedContent(new ByteArrayInputStream(usuario.getFoto()),mimeType);
    }

    
    /**
     * Método que obtiene la ruta de los archivos
     * @return la ruta del directorio.
     */
    public static String getRuta(){
        try{
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            return ctx.getRealPath("/");
        }catch(Exception e){
            System.out.println("Error en obtener ruta");
        }
        return null;
    }
    
    /**
     * Obtiene la url actual.
     * @return la url actual.
     */
    public static String getUrl(){
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StringBuffer requestUrl = origRequest.getRequestURL();
        return requestUrl.toString();
    }
    
    private void setUsuario(){
        FacesContext context = getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        
        //SIMULAR SESIÓN
        if(usuario == null) {
            u_jpaController = new UsuarioJpaController(emf);
            usuario = u_jpaController.findUsuario(1);
//            context.getExternalContext().getSessionMap().put("adm", usuario);
            context.getExternalContext().getSessionMap().put("usuario", usuario);
        }else{
            u_jpaController = new UsuarioJpaController(emf);
            usuario = u_jpaController.findUsuario(usuario.getId());
//            context.getExternalContext().getSessionMap().put("adm", usuario);
            context.getExternalContext().getSessionMap().put("usuario", usuario);
        }
    }
}