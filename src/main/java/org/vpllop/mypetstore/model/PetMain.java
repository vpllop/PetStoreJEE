package org.vpllop.mypetstore.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PetMain {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date bitrhDate = null;
        try {
            bitrhDate = sdf.parse("1992-07-26");
        } catch (ParseException ex) {
            Logger.getLogger(PetMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 1-Creates an instance of book
        PetEntity pet = new PetEntity("MyCat", bitrhDate, "Cat");

        // 2-Obtains an entity manager and a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetStoreDB");
        EntityManager em = emf.createEntityManager();

        // 3-Persists the book to the database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(pet);
        tx.commit();

        // 4-Executes the named query
        pet = em.createNamedQuery("findPetCat", PetEntity.class).getSingleResult();

        System.out.println("######### " + pet.toString());

        // 5-Closes the entity manager and the factory
        em.close();
        emf.close();
    }
}
