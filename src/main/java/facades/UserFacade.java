package facades;

import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    
    
      public User addNewUser(User newUser) throws AuthenticationException{
        
        
         if (newUser.getUserName().isEmpty() || newUser.getUserPass().isEmpty() ) {
                throw new AuthenticationException("User name or password must not be empty");   
            }
        
          EntityManager em = emf.createEntityManager();
        
          User user = new User("Bruger1", "bruger");
      
       User user2 = new User("Admin1", "admin");
            user.addRole(new Role("user"));
         user2.addRole(new Role("admin"));
        try {
          em.getTransaction().begin();
            em.persist(new Role("user"));
          em.persist(new Role("admin"));
          em.persist(user);
          em.persist(user2);
          em.getTransaction().commit();
           
        } catch (RollbackException e) {
            throw new AuthenticationException("User already exist. Try another username"+ e.getMessage());
            
        } catch (Exception e){
              throw new AuthenticationException("Something went wrong. Server may be unavailable at the moment");
        } finally {
            em.close();
        }
        return user;

        
    }
}
