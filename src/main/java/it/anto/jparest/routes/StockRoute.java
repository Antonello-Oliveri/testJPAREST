/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package it.anto.jparest.routes;


import it.anto.jparest.model.StockData;
import it.anto.jparest.services.IEntityRepository;
import it.anto.jparest.model.User;
import it.anto.jparest.rest.filters.RequiresPermission;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Request;
import org.jboss.resteasy.annotations.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anto
 */
@Path("/stock")
@Component
@RequiresPermission({"Admin"})

public class StockRoute {
    @Autowired
    IEntityRepository repository;
    @Context
    Request request;
    
   
    @GET
    @Path("/{id}")
    @RequiresPermission({"Admin","MovMag"})
    public StockData getUser(@PathParam("id") Long id)
    {
       
        
       return repository.find(id, StockData.class);
    }
   
}
