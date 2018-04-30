package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.Carrera;
import com.seedhost.fodupa.model.CarreraJpaController;
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.model.web.RegistraBean;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;

import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


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
    private Carrera carrera;
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


    public String createRegistro(){

        FacesContext.getCurrentInstance().getViewRoot().setLocale(new
                                                            Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();

        Usuario usuario = new Usuario();
        Carrera carrera = new Carrera();
        List<Carrera> carreras = new ArrayList<>();
        /*Usuario usuario = (Usuario) context.getExternalContext().getSessionMap()
                                    .get("usuario");
        */
        /*String[] id_nom_categoria = pregunta_bean.getCategoria().split(":");
        int id = Integer.parseInt(id_nom_categoria[0]);
        */

        String confirm = registra_bean.getConfirm();
        byte[] foto = registra_bean.getFoto();

//        //Take the default user image.
        if (foto == null) {
            String src = getRuta()+"/resources/imgs/default_user.png";
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
                System.out.println("FOTO: "+foto);
            }catch(FileNotFoundException e){}
        }
           

        carreras.add(registra_bean.getCarrera());

        usuario.setNombre(registra_bean.getNombre());
        usuario.setApPaterno(registra_bean.getApPaterno());
        usuario.setApMaterno(registra_bean.getApMaterno());
        usuario.setCorreo(registra_bean.getCorreo());
        usuario.setContrasena(registra_bean.getContrasena());
        
        usuario.setCarreraList(carreras);
        usuario.setFoto(foto);
        usuario.setToken("");
        usuario.setValido(false);
        
        u_jpaController = new UsuarioJpaController(emf);
        u_jpaController.create(usuario);
        
        List<Usuario> l = u_jpaController.findUsuarioEntities();
        Usuario usr = l.get(l.size()-1);
        String usr_id = Integer.toString(usr.getId());
        String token = getMD5(usr_id);
        usr.setToken(token);
        
        try{
            u_jpaController.edit(usr);
        //Jejejeje
        }catch(Exception e){ System.out.println("Error al editar usuario.\n"); }
        
        //CORREO
        String destinatario = registra_bean.getCorreo();
        String asunto = "Confirmación de registro";
        
        try{
            //Obtenemos el nombre del host y su servidor.
            InetAddress address = InetAddress.getLocalHost();
            //Nombre de la dirección InetAddress seleccionada.
            String hostName = address.getHostName();
            String link = hostName+"token="+token;
        //UTILIZAR MD5
            String cuerpo = "Haz click en el siguiente enlace para confirmar tu registro:\n"+link;
            enviar(destinatario,asunto,cuerpo);
        }catch(UnknownHostException e){System.out.println("Problemas al obtener dirección del host.");}
        
        return "/views/mensaje_correo?faces-redirect=true";
    }
    
  /*  public void registroExitoso(){
        Login l = jpaController.findLogin(usuario.getUsuario(), usuario.getContraseña());
        boolean logged = l != null;
        Usuario u = usuarioJpaController.findUsuarioByLoginId(l.getId());
    }
*/
    private boolean enviar(String destinatario, String asunto, String cuerpo) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "fodupa@gmail.com";  //Para la dirección nomcuenta@gmail.com
        String password = "fodupa123";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", "fodupa");
        props.put("mail.smtp.clave", password);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {return false;}

        return true;
    }
    
    /**
     *  Aplica el hash md5 a la cadena input.
     * @param input cadena a cifrar.
     * @return el cifrado de una cadena.
     */
    private static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
            }
            
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Carrera> getCarreras() {
        return carreras;
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

    public RegistraBean getRegistra(){
        return registra_bean;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void setRegistra(RegistraBean registra){
        this.registra_bean = registra;
    }

    public String formularioRegistro(){
        return "/views/registra?faces-redirect=true";
    }

    public String paginaPrincipal(){
        return "/index?faces-redirect=true";
    }

    public static String getRuta(){
        try{
            HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            StringBuffer requestUrl = origRequest.getRequestURL();
            return requestUrl.toString();
        }catch(Exception e){
            System.out.println("Error en obtener ruta");
        }
        return null;
    }

}