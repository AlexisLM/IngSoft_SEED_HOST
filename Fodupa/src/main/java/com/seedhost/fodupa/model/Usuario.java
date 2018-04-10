/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexis
 */
@Entity
@Table(catalog = "fodupa", schema = "fodupa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApPaterno", query = "SELECT u FROM Usuario u WHERE u.apPaterno = :apPaterno")
    , @NamedQuery(name = "Usuario.findByApMaterno", query = "SELECT u FROM Usuario u WHERE u.apMaterno = :apMaterno")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    @Column(nullable = false)
    private String nombre;


    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50, message = "El apellido paterno debe tener entre 3 y 50 caracteres.")
    @Column(nullable = false)
    private String apPaterno;

    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50, message = "El apellido materno debe tener entre 3 y 50 caracteres.")
    @Column(nullable = false)
    private String apMaterno;

    @Basic(optional = false)
    @NotNull
    @Size(min = 15, max = 100)
    @Column(nullable = false)
    private String correo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres.")
    @Column(nullable = false)
    private String contrasena;

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres.")
    @Column(nullable = false)
    private String confirm;
    
    @Basic(optional = false)
    @NotNull
    private Carrera carrera;
    
    
    @Lob
    private byte[] foto;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Carrera> carreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Comentario> comentarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Pregunta> preguntaList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String correo, String contrasena, String nombre, String apPaterno, String apMaterno) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @XmlTransient
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }
    
    
    public Carrera getCarrera(){
        return carrera;
    }
    
    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
    }
    
    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }
    
    public String getConfirm() {
            return confirm;
    }

    public void setConfirm(String confirm) {
            this.confirm = confirm;
    }
    
    @AssertTrue(message = "Las contraseñas deben coincidir.")
    public boolean equalsContrasenia() {
            return contrasena.equals(confirm);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seedhost.fodupa.model.Usuario[ id=" + id + " ]";
    }
    
}
