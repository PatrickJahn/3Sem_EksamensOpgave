/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.breedDTO;
import errorhandling.API_Exception;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.scene.input.KeyCode.G;
import javax.persistence.EntityManagerFactory;
import utils.HttpUtils;

/**
 *
 * @author Patrick
 */
public class RemoteServerFacade {
    
      private static EntityManagerFactory emf;
      private static RemoteServerFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
   
    public RemoteServerFacade(){}
      /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static RemoteServerFacade getRemoteServerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RemoteServerFacade();
        }
        return instance;
    }
    
    
    
    public breedDTO getAllBreeds() throws IOException, API_Exception{
     breedDTO breed;
        try {
            
             String breedJson = HttpUtils.fetchData("https://dog-info.cooljavascript.dk/api/breed");
             breed = GSON.fromJson(breedJson, breedDTO.class);
               
        } catch(Exception e){
           throw new API_Exception(e.getMessage());
        }
        
        return breed;
    }
   
}
