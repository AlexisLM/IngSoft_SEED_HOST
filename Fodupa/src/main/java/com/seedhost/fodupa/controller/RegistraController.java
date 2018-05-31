package com.seedhost.fodupa.controller;

/* Modelo */
import com.seedhost.fodupa.model.Carrera;
import com.seedhost.fodupa.model.CarreraJpaController;
import com.seedhost.fodupa.model.EntityProvider;
import com.seedhost.fodupa.model.Usuario;
import com.seedhost.fodupa.model.UsuarioJpaController;
import com.seedhost.fodupa.model.web.RegistraBean;
import java.awt.image.BufferedImage;
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
import javax.imageio.ImageIO;
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
    private CarreraJpaController cJpaController;
    private UsuarioJpaController uJpaController;
    private List<Carrera> carreras;
    private Carrera carrera;
    private RegistraBean registraBean;

    /**
     * Creates a new instance of RegistraController.
     */
    @PostConstruct
    private void init() {

        FacesContext.getCurrentInstance().getViewRoot().setLocale(new
                                                            Locale("es-Mx"));
        emf = EntityProvider.provider();

        //Obtenemos las carreras
        cJpaController = new CarreraJpaController(emf);
        carreras = cJpaController.findCarreraEntities();

        //Inicializamos el registraBean
        registraBean = new RegistraBean();

        //Obtenemos el usuario actual (Esto es del caso de uso de Fer)
        /*FacesContext context = getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext()
                          .getSessionMap().get("usuario");
        if(usuario == null) {
            uJpaController = new UsuarioJpaController(emf);
            usuario = uJpaController.findUsuario(1);
            context.getExternalContext().getSessionMap().put("usuario", 
                                                              usuario);
        }*/
    }

    public String createRegistro() {

        FacesContext.getCurrentInstance().getViewRoot().setLocale(new
                                                            Locale("es-Mx"));
        FacesContext context = getCurrentInstance();
        emf = EntityProvider.provider();

        Usuario usuario = new Usuario();
        Carrera carrera = new Carrera();
        List<Carrera> carreras = new ArrayList<>();
        /*Usuario usuario = (Usuario) context.getExternalContext()
                            .getSessionMap().get("usuario");
        */
        /*String[] id_nom_categoria = pregunta_bean.getCategoria().split(":");
        int id = Integer.parseInt(id_nom_categoria[0]);
        */

        String confirm = registraBean.getConfirm();
        byte[] foto = registraBean.getFoto();

        //Take the default user image.
        if (foto == null) {
            System.out.println(getRuta());
            String src = getRuta() + "/resources/imgs/default_user.png";
            File file = new File(src);

            try {
                BufferedImage image = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                byte[] res = baos.toByteArray();
                //String encodedImage = Base64.encode(baos.toByteArray());
                /*System.out.println("FOTO");
                for(int i= 0; i<res.length;i++)
                    System.out.print(res[i]);
                */
                foto = res;
            } catch(IOException e) {
                System.out.println("NO filee");
            }
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
        carreras.add(registraBean.getCarrera());

        usuario.setNombre(registraBean.getNombre());
        usuario.setApPaterno(registraBean.getApPaterno());
        usuario.setApMaterno(registraBean.getApMaterno());
        usuario.setCorreo(registraBean.getCorreo());

        //Introduce la contraseña cifrada.
        usuario.setContrasena(getSha256(registraBean.getContrasena()));

        usuario.setCarreraList(carreras);
        usuario.setFoto(foto);
        //Ya que no puede ser null
        usuario.setToken("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        usuario.setValido(false);

        uJpaController = new UsuarioJpaController(emf);
        uJpaController.create(usuario);

        List<Usuario> l = uJpaController.findUsuarioEntities();
        Usuario usr = l.get(l.size()-1);
        String usrID = Integer.toString(usr.getId());
        String token = getSha256(usrID);
        System.out.print("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!11TOKEN: " + token);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
        usr.setToken(token);

        try {
            uJpaController.edit(usr);
        //Jejejeje
        } catch(Exception e) {
            System.out.println("Error al editar usuario.\n");
        }

        //CORREO
        String destinatario = registraBean.getCorreo();
        String asunto = "Confirmación de registro";
        String hostName = getUrl();
        String link = hostName.substring(0, hostName.length() - 14)
                      + "registro_exitoso.xhtml?token=" + token;
        //UTILIZAR MD5
        String cuerpo = "Haz click en el siguiente enlace para confirmar tu "
                        + "registro:\n" + link;
        boolean e = enviar(destinatario, asunto, cuerpo);
        System.out.println(e + "");

        return "/views/mensaje_correo?faces-redirect=true";
    }

  /*  public void registroExitoso() {
        Login l = jpaController.findLogin(usuario.getUsuario(), usuario
                                          .getContraseña());
        boolean logged = l != null;
        Usuario u = usuarioJpaController.findUsuarioByLoginId(l.getId());
    }*/
    private boolean enviar(String destinatario, String asunto, String cuerpo) {
        //Esto es lo que va delante de @gmail.com en tu cuenta de correo.
        //Es el remitente también.
        String remitente = "fodupa@gmail.com";
        //Para la dirección nomcuenta@gmail.com
        String password = "fodupa123";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        //El servidor SMTP de Google.
        props.put("mail.smtp.user", "fodupa");
        //La clave de la cuenta.
        props.put("mail.smtp.clave", password);
        //Usar autenticación mediante usuario y clave.
        props.put("mail.smtp.auth", "true");
        //Para conectar de manera segura al servidor SMTP.
        props.put("mail.smtp.starttls.enable", "true");
        //El puerto SMTP seguro de Google.
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            //Se podrían añadir varios de la misma manera.
            message.addRecipient(Message.RecipientType.TO, new 
                                 InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            return false;
        }
        return true;
    }

    /**
     *  Aplica el hash md5 a la cadena input.
     * @param input cadena a cifrar.
     * @return el cifrado de una cadena de tamaño 65.
     */
    public static String getSha256(String input) {
        try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex) {
           System.out.println("ERROOOOR SHA256!!!!!!!!!!!!!!!!!!!!!!!!!!!");
           return null;
        }
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public Carrera getCarrera(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("no id provided");
        }
        for (Carrera carr : carreras) {
            if (id.equals(carr.getId())) {
                return carr;
            }
        }
        return null;
    }

    public RegistraBean getRegistra() {
        return registraBean;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void setRegistra(RegistraBean registra) {
        this.registraBean = registra;
    }

    public String formularioRegistro() {
        return "/views/registra?faces-redirect=true";
    }

    public String paginaPrincipal() {
        return "/index?faces-redirect=true";
    }
    
    /**
     * Método que obtiene la ruta de los archivos
     * @return la ruta del directorio.
     */
    public static String getRuta() {
        try {
            ServletContext ctx = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();
            return ctx.getRealPath("/");
        } catch(Exception e) {
            System.out.println("Error en obtener ruta");
        }
        return null;
    }

    /**
     * Obtiene la url actual.
     * @return la url actual.
     */
    public static String getUrl() {
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        StringBuffer requestUrl = origRequest.getRequestURL();
        return requestUrl.toString();
    }

}
