/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexis
 */
@Embeddable
public class ComentarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idusuario;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idpregunta;

    public ComentarioPK() {
    }

    public ComentarioPK(Date fecha, int idusuario, int idpregunta) {
        this.fecha = fecha;
        this.idusuario = idusuario;
        this.idpregunta = idpregunta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(int idpregunta) {
        this.idpregunta = idpregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) idusuario;
        hash += (int) idpregunta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioPK)) {
            return false;
        }
        ComentarioPK other = (ComentarioPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        if (this.idpregunta != other.idpregunta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seedhost.fodupa.model.ComentarioPK[ fecha=" + fecha + ", idusuario=" + idusuario + ", idpregunta=" + idpregunta + " ]";
    }
    
}
