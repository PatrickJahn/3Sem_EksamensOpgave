/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private Date dateTime;
    
    
    
    @ManyToMany
    @JoinTable(name = "searches_breed", joinColumns = {
    @JoinColumn(name = "dateTime", referencedColumnName = "dateTime")}, inverseJoinColumns = {
    @JoinColumn(name = "breed", referencedColumnName = "breed")})
    private List<Breed> breed;

    
    public Searches(){}
    
    public Searches(Breed breed) {
        this.breed.add(breed);
        this.dateTime = new Date();
    }

    
    
  
}
