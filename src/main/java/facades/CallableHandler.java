/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.Callable;
import utils.HttpUtils;

/**
 *
 * @author Patrick
 */
class CallableHandler implements Callable<String>{
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String categoryUrl;
        public CallableHandler(String url){
            this.categoryUrl = url;
        }
        
        @Override
        public String call() throws Exception {
     
            String data = HttpUtils.fetchData(categoryUrl);
            
                
            return data;
        }
        
    } 

