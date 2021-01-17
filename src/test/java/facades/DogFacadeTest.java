package facades;

import entities.Breed;
import entities.Dog;
import utils.EMF_Creator;
import entities.User;
import errorhandling.API_Exception;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import security.errorhandling.DogException;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class DogFacadeTest {

    private static EntityManagerFactory emf;
    private static DogFacade dogFacade;

    public DogFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
  
       dogFacade = DogFacade.getDogFacade(emf);
               EntityManager em = emf.createEntityManager();

               try {

         User u = new User("bobby", "123456");
       User u2 = new User("dogman", "123456");
       Dog dog = new Dog("dogmansdog", "20-20-2002", "info", "bulldog");
        dog.setUser(u2);
     
         em.getTransaction().begin();
         
           em.createQuery("delete from Dog").executeUpdate();         
           em.createQuery("delete from User").executeUpdate();
      
         em.persist(u);
         em.persist(u2);
         em.persist(dog);
         em.getTransaction().commit();

               }
                finally {
            em.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        
        
        try {
            em.getTransaction().begin();
        
  
    
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    @Test
    public void testAddDog() throws API_Exception, DogException{
        EntityManager em = emf.createEntityManager();
        
        Dog newDog = new Dog("testDog1", "20-10-2002", "info text", "mops");
        dogFacade.AddNewDog(newDog, "bobby");
        
        User u = em.find(User.class, "bobby");
   

        assertTrue(u.getDogs().size() > 0);
    }
    
    @Test
    public void negativeTestAddDogNoName() throws API_Exception, DogException{
            EntityManager em = emf.createEntityManager();
        
        Dog newDog = new Dog("", "20-10-2002", "info text", "mops");
        
         Exception exception = assertThrows(DogException.class, () -> {
         dogFacade.AddNewDog(newDog, "bobby");
    });

    String expectedMessage = "Name was not given";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
        
        
    }
    
     @Test
    public void negativeTestAddDogNoDate() throws API_Exception, DogException{
            EntityManager em = emf.createEntityManager();
        
        Dog newDog = new Dog("testDog", "", "info text", "mop");
        
         Exception exception = assertThrows(DogException.class, () -> {
         dogFacade.AddNewDog(newDog, "bobby");
    });

    String expectedMessage = "Date of birth was not given";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
        
        
    }
    
   
   
   
  

}
