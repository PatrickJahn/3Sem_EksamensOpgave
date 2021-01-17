/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class factDTO {
    
    List<String> facts = new ArrayList<>();

    public factDTO(String f) {
        facts.add(f);
    }
    
    
    
    

    public String getFacts() {
        return this.facts.get(0);
    }

    public void addFacts(String facts) {
        this.facts.add(facts);
    }
    
    
    
    
    
}
