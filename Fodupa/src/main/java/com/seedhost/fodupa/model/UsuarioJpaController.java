/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;

import com.seedhost.fodupa.model.exceptions.IllegalOrphanException;
import com.seedhost.fodupa.model.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alexis
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getCarreraList() == null) {
            usuario.setCarreraList(new ArrayList<Carrera>());
        }
        if (usuario.getComentarioList() == null) {
            usuario.setComentarioList(new ArrayList<Comentario>());
        }
        if (usuario.getPreguntaList() == null) {
            usuario.setPreguntaList(new ArrayList<Pregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Carrera> attachedCarreraList = new ArrayList<Carrera>();
            for (Carrera carreraListCarreraToAttach : usuario.getCarreraList()) {
                carreraListCarreraToAttach = em.getReference(carreraListCarreraToAttach.getClass(), carreraListCarreraToAttach.getId());
                attachedCarreraList.add(carreraListCarreraToAttach);
            }
            usuario.setCarreraList(attachedCarreraList);
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : usuario.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getComentarioPK());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            usuario.setComentarioList(attachedComentarioList);
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : usuario.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getId());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            usuario.setPreguntaList(attachedPreguntaList);
            em.persist(usuario);
            for (Carrera carreraListCarrera : usuario.getCarreraList()) {
                carreraListCarrera.getUsuarioList().add(usuario);
                carreraListCarrera = em.merge(carreraListCarrera);
            }
            for (Comentario comentarioListComentario : usuario.getComentarioList()) {
                Usuario oldUsuarioOfComentarioListComentario = comentarioListComentario.getUsuario();
                comentarioListComentario.setUsuario(usuario);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldUsuarioOfComentarioListComentario != null) {
                    oldUsuarioOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldUsuarioOfComentarioListComentario = em.merge(oldUsuarioOfComentarioListComentario);
                }
            }
            for (Pregunta preguntaListPregunta : usuario.getPreguntaList()) {
                Usuario oldIdusuarioOfPreguntaListPregunta = preguntaListPregunta.getIdusuario();
                preguntaListPregunta.setIdusuario(usuario);
                preguntaListPregunta = em.merge(preguntaListPregunta);
                if (oldIdusuarioOfPreguntaListPregunta != null) {
                    oldIdusuarioOfPreguntaListPregunta.getPreguntaList().remove(preguntaListPregunta);
                    oldIdusuarioOfPreguntaListPregunta = em.merge(oldIdusuarioOfPreguntaListPregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Carrera> carreraListOld = persistentUsuario.getCarreraList();
            List<Carrera> carreraListNew = usuario.getCarreraList();
            List<Comentario> comentarioListOld = persistentUsuario.getComentarioList();
            List<Comentario> comentarioListNew = usuario.getComentarioList();
            List<Pregunta> preguntaListOld = persistentUsuario.getPreguntaList();
            List<Pregunta> preguntaListNew = usuario.getPreguntaList();
            List<String> illegalOrphanMessages = null;
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentario " + comentarioListOldComentario + " since its usuario field is not nullable.");
                }
            }
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pregunta " + preguntaListOldPregunta + " since its idusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Carrera> attachedCarreraListNew = new ArrayList<Carrera>();
            for (Carrera carreraListNewCarreraToAttach : carreraListNew) {
                carreraListNewCarreraToAttach = em.getReference(carreraListNewCarreraToAttach.getClass(), carreraListNewCarreraToAttach.getId());
                attachedCarreraListNew.add(carreraListNewCarreraToAttach);
            }
            carreraListNew = attachedCarreraListNew;
            usuario.setCarreraList(carreraListNew);
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getComentarioPK());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            usuario.setComentarioList(comentarioListNew);
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getId());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            usuario.setPreguntaList(preguntaListNew);
            usuario = em.merge(usuario);
            for (Carrera carreraListOldCarrera : carreraListOld) {
                if (!carreraListNew.contains(carreraListOldCarrera)) {
                    carreraListOldCarrera.getUsuarioList().remove(usuario);
                    carreraListOldCarrera = em.merge(carreraListOldCarrera);
                }
            }
            for (Carrera carreraListNewCarrera : carreraListNew) {
                if (!carreraListOld.contains(carreraListNewCarrera)) {
                    carreraListNewCarrera.getUsuarioList().add(usuario);
                    carreraListNewCarrera = em.merge(carreraListNewCarrera);
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Usuario oldUsuarioOfComentarioListNewComentario = comentarioListNewComentario.getUsuario();
                    comentarioListNewComentario.setUsuario(usuario);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldUsuarioOfComentarioListNewComentario != null && !oldUsuarioOfComentarioListNewComentario.equals(usuario)) {
                        oldUsuarioOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldUsuarioOfComentarioListNewComentario = em.merge(oldUsuarioOfComentarioListNewComentario);
                    }
                }
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    Usuario oldIdusuarioOfPreguntaListNewPregunta = preguntaListNewPregunta.getIdusuario();
                    preguntaListNewPregunta.setIdusuario(usuario);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                    if (oldIdusuarioOfPreguntaListNewPregunta != null && !oldIdusuarioOfPreguntaListNewPregunta.equals(usuario)) {
                        oldIdusuarioOfPreguntaListNewPregunta.getPreguntaList().remove(preguntaListNewPregunta);
                        oldIdusuarioOfPreguntaListNewPregunta = em.merge(oldIdusuarioOfPreguntaListNewPregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comentario> comentarioListOrphanCheck = usuario.getComentarioList();
            for (Comentario comentarioListOrphanCheckComentario : comentarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Comentario " + comentarioListOrphanCheckComentario + " in its comentarioList field has a non-nullable usuario field.");
            }
            List<Pregunta> preguntaListOrphanCheck = usuario.getPreguntaList();
            for (Pregunta preguntaListOrphanCheckPregunta : preguntaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Pregunta " + preguntaListOrphanCheckPregunta + " in its preguntaList field has a non-nullable idusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Carrera> carreraList = usuario.getCarreraList();
            for (Carrera carreraListCarrera : carreraList) {
                carreraListCarrera.getUsuarioList().remove(usuario);
                carreraListCarrera = em.merge(carreraListCarrera);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Usuario findLogin(String correo, String contraseña) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Usuario.findByCorreoAndPassword")
                .setParameter("correo", correo)
                .setParameter("contrasena", contraseña);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Usuario) q.getSingleResult();
    }
    
        public Usuario findUsuarioByLoginId(Integer loginId) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Usuario.findById")
                .setParameter("id", loginId);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Usuario) q.getSingleResult();
    }
}
