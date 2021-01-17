/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Dog;

/**
 *
 * @author Patrick
 */
public class dogDTO {
    
    
    Long id;
    String name;
    String dateOfBirth;
    String Info;
    String breed;

    public dogDTO(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.Info = dog.getInfo();
        this.dateOfBirth = dog.getDateOfBitrh();
        this.breed = dog.getBreed();
    }
    
    
    
    
}
