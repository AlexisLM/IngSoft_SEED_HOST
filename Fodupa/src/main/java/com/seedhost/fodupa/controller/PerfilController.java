package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.Carrera;
import com.seedhost.fodupa.model.CarreraJpaController;
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.web.PerfilBean;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    private List<Carrera> carrerasAc;
    private Usuario usuario;
    private Carrera carrera;
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
        FacesContext context = getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        
        //SIMULAR SESIÓN
        if(usuario == null) {
            u_jpaController = new UsuarioJpaController(emf);
            usuario = u_jpaController.findUsuario(1);
//            context.getExternalContext().getSessionMap().put("adm", usuario);
            context.getExternalContext().getSessionMap().put("usuario", usuario);
        }
        
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
        
        Usuario usuario = new Usuario();
        Carrera carrera = new Carrera();
        List<Carrera> carreras = new ArrayList<>();
        
        String confirm = perfil_bean.getConfirm();
        byte[] foto = perfil_bean.getFoto();

//        //Take the default user image.
        if (foto == null) {
            System.out.println(getRuta());
            String src = getRuta()+"/resources/imgs/default_user.png";
            File file = new File(src);
            
            try{
                BufferedImage image = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                byte[] res=baos.toByteArray();
                //String encodedImage = Base64.encode(baos.toByteArray());
                /*System.out.println("FOTO");
                for(int i= 0; i<res.length;i++)
                    System.out.print(res[i]);
                */
                foto = res;
            }catch(IOException e){System.out.println("NO filee");}
        }
           
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
        carreras.add(perfil_bean.getCarrera());

        usuario.setNombre(perfil_bean.getNombre());
        usuario.setApPaterno(perfil_bean.getApPaterno());
        usuario.setApMaterno(perfil_bean.getApMaterno());
        usuario.setCorreo(perfil_bean.getCorreo());
        
        //Introduce la contraseña cifrada.
        usuario.setContrasena(getSha256(perfil_bean.getContrasena()));
        
        usuario.setCarreraList(carreras);
        usuario.setFoto(foto);
        //Ya que no puede ser null
        usuario.setToken("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        usuario.setValido(false);
        
        u_jpaController = new UsuarioJpaController(emf);
        u_jpaController.create(usuario);
        
        List<Usuario> l = u_jpaController.findUsuarioEntities();
        Usuario usr = l.get(l.size()-1);
        String usr_id = Integer.toString(usr.getId());
        String token = getSha256(usr_id);
        System.out.print("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!11TOKEN: "+token);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
        usr.setToken(token);
        
        try{
            u_jpaController.edit(usr);
        //Jejejeje
        }catch(Exception e){ System.out.println("Error al editar usuario.\n"); }
        
        //CORREO
        String destinatario = perfil_bean.getCorreo();
        String asunto = "Confirmación de registro";
        
        String hostName = getUrl();
        String link = hostName.substring(0,hostName.length()-14)+"registro_exitoso.xhtml?token="+token;
        //UTILIZAR MD5
        String cuerpo = "Haz click en el siguiente enlace para confirmar tu registro:\n"+link;
        
        return "/views/mensaje_correo?faces-redirect=true";
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
    
    public List<Carrera> getCarrerasAc() {
        return carrerasAc;
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
    
    public void setCarrerasAc(Carrera car) {
        this.carrerasAc.add(car);
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

}