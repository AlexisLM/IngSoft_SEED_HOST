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
public class PreguntaJpaController implements Serializable {

    public PreguntaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pregunta pregunta) {
        if (pregunta.getComentarioList() == null) {
            pregunta.setComentarioList(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria idcategoria = pregunta.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getId());
                pregunta.setIdcategoria(idcategoria);
            }
            Usuario idusuario = pregunta.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getId());
                pregunta.setIdusuario(idusuario);
            }
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : pregunta.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getComentarioPK());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            pregunta.setComentarioList(attachedComentarioList);
            em.persist(pregunta);
            if (idcategoria != null) {
                idcategoria.getPreguntaList().add(pregunta);
                idcategoria = em.merge(idcategoria);
            }
            if (idusuario != null) {
                idusuario.getPreguntaList().add(pregunta);
                idusuario = em.merge(idusuario);
            }
            for (Comentario comentarioListComentario : pregunta.getComentarioList()) {
                Pregunta oldPreguntaOfComentarioListComentario = comentarioListComentario.getPregunta();
                comentarioListComentario.setPregunta(pregunta);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldPreguntaOfComentarioListComentario != null) {
                    oldPreguntaOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldPreguntaOfComentarioListComentario = em.merge(oldPreguntaOfComentarioListComentario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pregunta pregunta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta persistentPregunta = em.find(Pregunta.class, pregunta.getId());
            Categoria idcategoriaOld = persistentPregunta.getIdcategoria();
            Categoria idcategoriaNew = pregunta.getIdcategoria();
            Usuario idusuarioOld = persistentPregunta.getIdusuario();
            Usuario idusuarioNew = pregunta.getIdusuario();
            List<Comentario> comentarioListOld = persistentPregunta.getComentarioList();
            List<Comentario> comentarioListNew = pregunta.getComentarioList();
            List<String> illegalOrphanMessages = null;
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentario " + comentarioListOldComentario + " since its pregunta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getId());
                pregunta.setIdcategoria(idcategoriaNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getId());
                pregunta.setIdusuario(idusuarioNew);
            }
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getComentarioPK());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            pregunta.setComentarioList(comentarioListNew);
            pregunta = em.merge(pregunta);
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getPreguntaList().remove(pregunta);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getPreguntaList().add(pregunta);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getPreguntaList().remove(pregunta);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getPreguntaList().add(pregunta);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Pregunta oldPreguntaOfComentarioListNewComentario = comentarioListNewComentario.getPregunta();
                    comentarioListNewComentario.setPregunta(pregunta);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldPreguntaOfComentarioListNewComentario != null && !oldPreguntaOfComentarioListNewComentario.equals(pregunta)) {
                        oldPreguntaOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldPreguntaOfComentarioListNewComentario = em.merge(oldPreguntaOfComentarioListNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pregunta.getId();
                if (findPregunta(id) == null) {
                    throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.");
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
            Pregunta pregunta;
            try {
                pregunta = em.getReference(Pregunta.class, id);
                pregunta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comentario> comentarioListOrphanCheck = pregunta.getComentarioList();
            for (Comentario comentarioListOrphanCheckComentario : comentarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pregunta (" + pregunta + ") cannot be destroyed since the Comentario " + comentarioListOrphanCheckComentario + " in its comentarioList field has a non-nullable pregunta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria idcategoria = pregunta.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getPreguntaList().remove(pregunta);
                idcategoria = em.merge(idcategoria);
            }
            Usuario idusuario = pregunta.getIdusuario();
            if (idusuario != null) {
                idusuario.getPreguntaList().remove(pregunta);
                idusuario = em.merge(idusuario);
            }
            em.remove(pregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pregunta> findPreguntaEntities() {
        return findPreguntaEntities(true, -1, -1);
    }

    public List<Pregunta> findPreguntaEntities(int maxResults, int firstResult) {
        return findPreguntaEntities(false, maxResults, firstResult);
    }

    private List<Pregunta> findPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pregunta.class));
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

    public List<Pregunta> findPreguntaByTitleContent(String content){
        EntityManager em = getEntityManager();
        try{
            
            Query q = em.createNamedQuery("Pregunta.findByTitleContent")
                    .setParameter("content", "%"+content+"%");
            if( q.getResultList().isEmpty() ) return null;
            
            return q.getResultList();
        } finally{
            em.close();
        }
    }

    public Pregunta findPregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pregunta> rt = cq.from(Pregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
