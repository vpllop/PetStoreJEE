
package org.vpllop.mypetstore.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.vpllop.mypetstore.excepcions.ClauInexistent;
import org.vpllop.mypetstore.model.PetEntity;

/**
 *
 * @author vpllop
 */
public class PetEntityMapper {
    public PetEntityMapper(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PetEntity pet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PetEntity pet) throws ClauInexistent, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pet = em.merge(pet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pet.getId();
                if (findPet(id) == null) {
                    throw new ClauInexistent("The article with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws ClauInexistent {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PetEntity pet;
            try {
                pet = em.getReference(PetEntity.class, id);
                pet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new ClauInexistent("The article with id " + id + " no longer exists.", enfe);
            }
            em.remove(pet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PetEntity> findPetEntities() {
        return findPetEntities(true, -1, -1);
    }

    public List<PetEntity> findPetEntities(int maxResults, int firstResult) {
        return findPetEntities(false, maxResults, firstResult);
    }

    private List<PetEntity> findPetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PetEntity.class));
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

    public PetEntity findPet(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PetEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    public PetEntity perNom(String nom){
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select a from Article a where a.descripcio=:desc");
        q.setParameter("desc", nom);
        return (PetEntity) q.getSingleResult();
    }

    public int getArticleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PetEntity> rt = cq.from(PetEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
