/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.dogDTO;
import entities.Breed;
import entities.Dog;
import entities.User;
import errorhandling.API_Exception;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.errorhandling.DogException;

/**
 *
 * @author Patrick
 */
public class DogFacade {
    
    
      private static EntityManagerFactory emf;
      private static DogFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
   
    public DogFacade(){}
      /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static DogFacade getDogFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DogFacade();
        }
        return instance;
    }
    
    
    
    
    public List<dogDTO> getUsersDogs(String username) throws DogException{
        
        List<dogDTO> dogs = new ArrayList<>();
        
         EntityManager em = emf.createEntityManager();
          
          try {
              
         User user = em.find(User.class, username);
         
         for (Dog d : user.getDogs()){
             dogs.add(new dogDTO(d));
         }
         
          } catch (Exception e){
              throw new DogException(e.getMessage());
          }
        return dogs;
    }
    
    
   public Dog AddNewDog(Dog newDog, String userName) throws API_Exception, DogException{
       EntityManager em = emf.createEntityManager();
       
       
       if (newDog.getName().isEmpty() ){
       throw new DogException("Name was not given");
   }
       if (newDog.getDateOfBitrh().isEmpty()){
           throw new DogException("Date of birth was not given");
       }
       
       try {
            em.getTransaction().begin();
            
         
            
           User user = em.find(User.class, userName);
           newDog.setUser(user);
           em.persist(newDog);
           em.getTransaction().commit();
           
       } catch (Exception e){
           
           throw new API_Exception(e.getMessage());
           
       } finally {
           em.close();
       }
       
       return newDog;
   }
    
}