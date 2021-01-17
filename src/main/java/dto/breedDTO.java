/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Patrick
 */
public class breedDTO {
    
    
    String breed;
    
    List<breedDTO> dogs;

    
    
    public breedDTO(String breed) {
        this.breed = breed;
    }
    
    

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<breedDTO> getDogs() {
        return dogs;
    }

    public void setDogs(List<breedDTO> dogs) {
        this.dogs = dogs;
    }
    
    
    
}
