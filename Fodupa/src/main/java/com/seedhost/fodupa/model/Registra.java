/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fergch97
 */
@Entity
@Table(name = "Usuario", catalog = "fodupa", schema = "fodupa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApPaterno", query = "SELECT u FROM Usuario u WHERE u.apPaterno = :apPaterno")
    , @NamedQuery(name = "Usuario.findByApMaterno", query = "SELECT u FROM Usuario u WHERE u.apMaterno = :apMaterno")})

public class Registra implements Cloneable, Serializable {
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id")
        private Integer id;
        
        @Basic(optional = false)
        @NotNull
	@Size(min = 3, max = 22, message = "El nombre debe tener entre 3 y 22 caracteres.")
        @Column(name = "name")
	private String nombre;
        
        
        @Basic(optional = false)
        @NotNull
        @Size(min = 3, max = 22, message = "El apellido paterno debe tener entre 3 y 22 caracteres.")
        @Column(name = "appat")
        private String apPaterno;
        
        @Basic(optional = false)
        @NotNull
        @Size(min = 3, max = 22, message = "El apellido materno debe tener entre 3 y 22 caracteres.")
        @Column(name = "apmat")
        private String apMaterno;
        
        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 100)
        @Column(name = "email")
        private String correo;
        
        @Basic(optional = false)
        @NotNull
	@Size(min = 6, max = 12, message = "La contraseña debe tener entre 6 y 12 caracteres.")
	@Column(name = "password")
        private String contrasena;
        
	private String confirm;
        
        @Lob
        @Column(name = "picture")
        private byte[] foto;
        
        @ManyToMany(mappedBy = "list_c")
        private List<Carrera> carreraList;

	@AssertTrue(message = "Las contraseñas deben coincidir.")
	public boolean equalsContrasenia() {
		return contrasena.equals(confirm);
	}

	public void agregaUsuario() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado con éxito", ""));
	}

        
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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


	public String getContrasenia() {
		return contrasena;
	}

	public void setContrasenia(String password) {
		this.contrasena = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
}
