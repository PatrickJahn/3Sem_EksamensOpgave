package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.searchesDTO;
import entities.Breed;
import entities.User;
import facades.DogFacade;
import facades.RemoteServerFacade;
import facades.UserFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import security.errorhandling.AuthenticationException;
import security.errorhandling.DogException;
import utils.EMF_Creator;

/**
 * @author Patrick
 */
@Path("info")
public class DemoResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   private static final RemoteServerFacade remoteFACADE =  RemoteServerFacade.getRemoteServerFacade(EMF);
       private static final UserFacade FACADE =  UserFacade.getUserFacade(EMF);
       private static final DogFacade dogFACADE =  DogFacade.getDogFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String  getInfoForAll() {
        return  "{\"msg\":\"Hello anonymous\"}";
   
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u", User.class);
            List<User> users = query.getResultList();
            return "\"count\" : \"" + users.size() + "\"";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
   
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
          return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("adduser")
    public String addNewUser(String user) throws AuthenticationException {
            User newUser = GSON.fromJson(user, User.class);
            
            FACADE.addNewUser(newUser);
  
          return "{\"msg\": \"Ny bruger oprettet: " + newUser.getUserName() + "\"}";
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin/searches")
    @RolesAllowed("admin")
    public String getAllSearches() throws DogException {
            long count = dogFACADE.getAllSearches();
          return "{\"searches\": \""+ count+ "\"}";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin/searches/{breed}")
    @RolesAllowed("admin")
    public String getAllSearchesForBreed(@PathParam("breed") String breed) throws DogException {
            long count = dogFACADE.getAllSearchesForBreed(breed);
          return "{\"searches\": \""+ count+ "\"}";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin/allsearches")
    @RolesAllowed("admin")
    public String getAllSearchesForAllBreeds() throws DogException {
            List<Breed> breeds = dogFACADE.getAllSearchesForAllBreeds();
            
           List<searchesDTO> searches = new ArrayList();
            for (Breed b : breeds){
             searches.add(new searchesDTO(b));
            }
            
          return GSON.toJson(searches);
    }
}   