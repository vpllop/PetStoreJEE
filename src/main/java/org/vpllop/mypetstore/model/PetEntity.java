package org.vpllop.mypetstore.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllPets", query = "SELECT p FROM Pet p"),
        @NamedQuery(name = "findPetCat", query = "SELECT p FROM Pet p WHERE b.breed ='Cat'")
})
@Table(name="PETS")
public class PetEntity implements Serializable {

    @Id
    private Long id;
    @Column(nullable=false)
    String name;
    @Column(name = "BORN_DATE")
    Date bornDate;
    @Basic(optional = false)
    String breed;
    @Transient
    String formattedName;

    public PetEntity(String name, Date bornDate, String breed) {
        this.name = name;
        this.bornDate = bornDate;
        this.breed = breed;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    @Override
    public String toString() {
        return "PetEntity{" + "id=" + id + ", name=" + name + ", bornDate=" + bornDate + ", breed=" + breed + ", formattedName=" + formattedName + '}';
    }
    
    
}
