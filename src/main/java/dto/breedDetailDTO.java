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
public class breedDetailDTO {
    
    
    String breed;
    String info;
    String wikipedia;
    String imageUrl;
    String facts;

    public breedDetailDTO(factDTO facts, infoDTO info, imageDTO image ) {
        
        this.breed = info.getBreed();
        this.info = info.getInfo();
        this.wikipedia = info.getWikipedia();
        this.facts = facts.getFacts();
        this.imageUrl = image.getImage();
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

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFacts() {
        return facts;
    }

    public void setFacts(String facts) {
        this.facts = facts;
    }
    
    
    
}
