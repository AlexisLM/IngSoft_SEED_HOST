/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;

import com.seedhost.fodupa.model.exceptions.NonexistentEntityException;
import com.seedhost.fodupa.model.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author alexis
 */
public class ComentarioJpaController implements Serializable {

    public ComentarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentario comentario) throws PreexistingEntityException, Exception {
        if (comentario.getComentarioPK() == null) {
            comentario.setComentarioPK(new ComentarioPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta pregunta = comentario.getPregunta();
            if (pregunta != null) {
                pregunta = em.getReference(pregunta.getClass(), pregunta.getId());
                comentario.setPregunta(pregunta);
            }
            Usuario usuario = comentario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                comentario.setUsuario(usuario);
            }
            em.persist(comentario);
            if (pregunta != null) {
                pregunta.getComentarioList().add(comentario);
                pregunta = em.merge(pregunta);
            }
            if (usuario != null) {
                usuario.getComentarioList().add(comentario);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComentario(comentario.getComentarioPK()) != null) {
                throw new PreexistingEntityException("Comentario " + comentario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comentario comentario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentario persistentComentario = em.find(Comentario.class, comentario.getComentarioPK());
            Pregunta preguntaOld = persistentComentario.getPregunta();
            Pregunta preguntaNew = comentario.getPregunta();
            Usuario usuarioOld = persistentComentario.getUsuario();
            Usuario usuarioNew = comentario.getUsuario();
            if (preguntaNew != null) {
                preguntaNew = em.getReference(preguntaNew.getClass(), preguntaNew.getId());
                comentario.setPregunta(preguntaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                comentario.setUsuario(usuarioNew);
            }
            comentario = em.merge(comentario);
            if (preguntaOld != null && !preguntaOld.equals(preguntaNew)) {
                preguntaOld.getComentarioList().remove(comentario);
                preguntaOld = em.merge(preguntaOld);
            }
            if (preguntaNew != null && !preguntaNew.equals(preguntaOld)) {
                preguntaNew.getComentarioList().add(comentario);
                preguntaNew = em.merge(preguntaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getComentarioList().remove(comentario);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getComentarioList().add(comentario);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ComentarioPK id = comentario.getComentarioPK();
                if (findComentario(id) == null) {
                    throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComentarioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
                comentario.getComentarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.", enfe);
            }
            Pregunta pregunta = comentario.getPregunta();
            if (pregunta != null) {
                pregunta.getComentarioList().remove(comentario);
                pregunta = em.merge(pregunta);
            }
            Usuario usuario = comentario.getUsuario();
            if (usuario != null) {
                usuario.getComentarioList().remove(comentario);
                usuario = em.merge(usuario);
            }
            em.remove(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }

    public List<Comentario> findComentarioEntities(int maxResults, int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }

    private List<Comentario> findComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentario.class));
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

    public Comentario findComentario(ComentarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentario> rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
