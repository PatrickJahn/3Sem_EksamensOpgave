/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Patrick
 */
@Entity
public class Searches implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String dateTime;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "searches_breed", joinColumns = {
    @JoinColumn(name = "date", referencedColumnName = "dateTime")}, inverseJoinColumns = {
    @JoinColumn(name = "breed", referencedColumnName = "breed")})
    private List<Breed> breeds = new ArrayList();
    
    
    
    public Searches() {
        this.dateTime = "" + new Date();
    }
    
   public void addBreed(Breed b){
       this.breeds.add(b);
   }
    
  
}
