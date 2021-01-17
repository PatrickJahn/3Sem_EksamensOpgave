/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.characterDTO;
import dto.filmDTO;
import dto.combinedDTO;
import dto.planetDTO;
import errorhandling.API_Exception;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    
    
   
   
}
