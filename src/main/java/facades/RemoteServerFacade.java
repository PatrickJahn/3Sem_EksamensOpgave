/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.breedDTO;
import dto.breedDetailDTO;
import dto.factDTO;
import dto.imageDTO;
import dto.infoDTO;
import entities.Breed;
import entities.Searches;
import errorhandling.API_Exception;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
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
             
             saveAllBreeds(breed);
               
        } catch(Exception e){
           throw new API_Exception(e.getMessage());
        }
        
        return breed;
    }
    
    
    public breedDetailDTO getDetailsOfBreed(String breed) throws InterruptedException, ExecutionException, API_Exception{

      
        
        String informationUrl = "https://dog-info.cooljavascript.dk/api/breed/" + breed;
        String factURL = "https://dog-api.kinduff.com/api/facts";
        String imageUrl = "https://dog-image.cooljavascript.dk/api/breed/random-image/" + breed;      
        
        breedDetailDTO details = giveThreadsGetDetails(informationUrl, factURL, imageUrl);
                 saveSearchOfEnpoint(details);
       
        
        return details;
        
    }
    
      /*
     * Denne metode anvender en executor og futures, til at tildele threads opgaver.
     * Tager argumenterne: infoUrl og factUrl som er de urls man vil hente data fra..
     */
     private breedDetailDTO giveThreadsGetDetails(String infoUrl, String factUrl, String imageUrl) throws InterruptedException, ExecutionException, API_Exception {
         
         breedDetailDTO breedDetails;
           ExecutorService executor = Executors.newCachedThreadPool();
         
         try { 
        
            Future infoFuture = executor.submit(new CallableHandler(infoUrl));
            Future factFuture = executor.submit(new CallableHandler(factUrl));
            Future imageFuture = executor.submit(new CallableHandler(imageUrl));

        
            String factJson = (String) factFuture.get();
            String infoJson = (String) infoFuture.get();
            String imageJson = (String) imageFuture.get();
            
            factDTO facts = GSON.fromJson(factJson, factDTO.class);
             infoDTO info = GSON.fromJson(infoJson, infoDTO.class);
             imageDTO image = GSON.fromJson(imageJson, imageDTO.class);
             
             breedDetails = new breedDetailDTO(facts, info, image);
      
             
         } catch(Exception e) {
             throw new API_Exception("Wrong breed or server is down");
         }
             
             
         return breedDetails;
     }
     
     
     
     
     
     private void saveSearchOfEnpoint(breedDetailDTO details) throws API_Exception{
         
         EntityManager em = emf.createEntityManager();
          Breed breed = new Breed(details.getBreed(), details.getInfo());
          Searches s = new Searches();
        
         
         try {
               
             em.getTransaction().begin();
            Breed b = em.find(Breed.class, details.getBreed());
            b.setInfo(details.getInfo());
            b.addSearch(s);
             em.getTransaction().commit();
         } catch (Exception e){
            throw new API_Exception(e.getMessage());
         }
                
         
     }
     
     
      private void saveAllBreeds(breedDTO breeds) throws API_Exception{
         
         EntityManager em = emf.createEntityManager();
         
         
         try {
             
            if (em.find(Breed.class, "boxer") != null){
                return ;
            } 
             em.getTransaction().begin();
             for (breedDTO b : breeds.getDogs()){
                          em.persist(new Breed(b.getBreed(), ""));

                      }
             em.getTransaction().commit();
         } catch (Exception e){
            throw new API_Exception(e.getMessage());
         }
                
         
     }
}
