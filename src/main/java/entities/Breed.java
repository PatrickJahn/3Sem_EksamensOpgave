/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author Patrick
 */
@Entity
public class Breed implements Serializable {


    private static final long serialVersionUID = 1L;
    
    @Id
    private String breed;
    
    @Column(length = 1025)
    private String info;
    
  
    @ManyToMany(mappedBy = "breeds", cascade = CascadeType.PERSIST)
   List<Searches> searches = new ArrayList<>();

    public Breed() {}
    
    public Breed(String breed, String info) {
        this.breed = breed;
        this.info = info;
    }

    
    public List<Searches> getSearches(){
        return this.searches;
    }
    public void addSearch(Searches s){
        s.addBreed(this);
        this.searches.add(s);
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


  
    

   
    
}
