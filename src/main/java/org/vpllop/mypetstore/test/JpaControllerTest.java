package org.vpllop.mypetstore.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.vpllop.mypetstore.controller.PetEntityMapper;
import org.vpllop.mypetstore.excepcions.ClauDuplicadaException;
import org.vpllop.mypetstore.model.PetEntity;
import org.vpllop.mypetstore.model.PetMain;

public class JpaControllerTest {

    EntityManagerFactory emf;

    public JpaControllerTest() {
    }

    public void iniciar() throws Exception {
        emf = Persistence.createEntityManagerFactory(
                "UnitatDePersistenciaPersistenciaAmbJpa");
    }

    public void acabar() throws Exception {
        emf.close();
    }

    public static void main(String[] args) {
        JpaControllerTest inst = new JpaControllerTest();
        System.setProperty("java.util.logging.config.file",
                "cfg/logger.properties");
        try {
            inst.iniciar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            inst.testInicial();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            inst.testPets();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            inst.acabar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void testInicial() throws Exception {
        System.out.println("Eliminació inical");
        // Eliminem registres de Pets
        PetEntityMapper petMapper = new PetEntityMapper(emf);
        List<PetEntity> listPets = petMapper.findPetEntities();
        for (PetEntity p : listPets) {
            petMapper.destroy(p.getId());
        }
    }

    public void testPets() throws Exception {
        System.out.println("Pets");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date birthDate = null;
         PetEntity pet;
        try {
            birthDate = sdf.parse("1992-07-26");
        } catch (ParseException ex) {
            Logger.getLogger(PetMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean tryFail = false;
        PetEntityMapper instance = new PetEntityMapper(emf);
        
        pet = new PetEntity("Mycat", birthDate, "Cat");
        instance.create(pet);
        
        pet = new PetEntity("Mydog", birthDate, "Dog");
        instance.create(pet);
        
        try {
            pet = new PetEntity("Mydog", birthDate, "Dog");
            instance.create(pet);
            tryFail = true;
        } catch (ClauDuplicadaException ex) {
            Logger.getLogger(PetMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        assert (tryFail) : "Creacio repetida";
        
        pet = new PetEntity("Mysnake", birthDate, "Snake");
        
        System.out.println("Fi test pais");
    }

}
