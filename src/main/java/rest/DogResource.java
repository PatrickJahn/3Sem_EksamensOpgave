package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.dogDTO;
import entities.Dog;
import errorhandling.API_Exception;
import facades.DogFacade;
import facades.RemoteServerFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import security.errorhandling.DogException;
import utils.EMF_Creator;

/**
 * @author Patrick
 */
@Path("dog")
public class DogResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   private static final RemoteServerFacade remoteFACADE =  RemoteServerFacade.getRemoteServerFacade(EMF);
       private static final DogFacade FACADE =  DogFacade.getDogFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String  getInfoForAll() {
        return  "{\"msg\":\"Hello anonymous\"}";
   
    }

   

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    @RolesAllowed("user")
    public String addNewDog(String dogJson) throws API_Exception, DogException {
        String thisuser = securityContext.getUserPrincipal().getName();
        
        Dog newDog = GSON.fromJson(dogJson, Dog.class);
        
        FACADE.AddNewDog(newDog, thisuser);
        
        return "{\"msg\": \"Dog added for User: " + thisuser + "\"}";
   
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mydogs")
    @RolesAllowed("user")
    public String getUsersDogs() throws DogException {
        String thisuser = securityContext.getUserPrincipal().getName();
        
            List<dogDTO> dogs = FACADE.getUsersDogs(thisuser);
       
          return GSON.toJson(dogs);
    }
    
    
    
}   