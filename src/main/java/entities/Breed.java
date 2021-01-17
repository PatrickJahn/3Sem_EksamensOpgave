/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Patrick
 */
@Entity
public class Breed implements Serializable {


    private static final long serialVersionUID = 1L;
    
    @Id
    private String breed;
    
    private String info;
    
    @OneToMany(mappedBy = "breed")
    private List<Dog> dogs;

    public Breed() {}
    
    public Breed(String breed, String info) {
        this.breed = breed;
        this.info = info;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }
    

   
    
}
