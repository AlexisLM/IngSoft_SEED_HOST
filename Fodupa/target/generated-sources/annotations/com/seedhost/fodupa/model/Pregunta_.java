package com.seedhost.fodupa.model;

import com.seedhost.fodupa.model.Categoria;
import com.seedhost.fodupa.model.Comentario;
import com.seedhost.fodupa.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T14:58:50")
@StaticMetamodel(Pregunta.class)
public class Pregunta_ { 

    public static volatile SingularAttribute<Pregunta, String> descripcion;
    public static volatile SingularAttribute<Pregunta, Date> fecha;
    public static volatile ListAttribute<Pregunta, Comentario> comentarioList;
    public static volatile SingularAttribute<Pregunta, String> titulo;
    public static volatile SingularAttribute<Pregunta, Categoria> idcategoria;
    public static volatile SingularAttribute<Pregunta, Integer> id;
    public static volatile SingularAttribute<Pregunta, Usuario> idusuario;

}