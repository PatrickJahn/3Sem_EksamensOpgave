/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Breed;

/**
 *
 * @author Patrick
 */
public class searchesDTO {
    
    
    String breed;
    int count;
    
    public searchesDTO(Breed b){
        
        this.breed = b.getBreed();
        this.count = b.getSearches().size();
        
    }
    
}
