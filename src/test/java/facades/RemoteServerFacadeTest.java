package facades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dto.breedDTO;
import dto.breedDetailDTO;
import errorhandling.API_Exception;
import facades.RemoteServerFacade;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author Patrick
 */
public class RemoteServerFacadeTest {
    
     private static EntityManagerFactory emf;
    public static RemoteServerFacade facade; 
    
    public RemoteServerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
           emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = RemoteServerFacade.getRemoteServerFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    

    @Test
    public void testGetAllBreeds() throws Exception {
        System.out.println("getAllBreeds");
      
           breedDTO result = facade.getAllBreeds();
            assertTrue(result.getDogs().size() > 0);
     
    }
    
  
    
}
