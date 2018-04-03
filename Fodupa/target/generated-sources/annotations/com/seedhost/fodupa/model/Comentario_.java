package com.seedhost.fodupa.model;

import com.seedhost.fodupa.model.ComentarioPK;
import com.seedhost.fodupa.model.Pregunta;
import com.seedhost.fodupa.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T12:19:43")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, String> contenido;
    public static volatile SingularAttribute<Comentario, Usuario> usuario;
    public static volatile SingularAttribute<Comentario, ComentarioPK> comentarioPK;
    public static volatile SingularAttribute<Comentario, Pregunta> pregunta;

}