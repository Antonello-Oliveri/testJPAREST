/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package it.anto.jparest.routes;


import it.anto.jparest.services.IEntityRepository;
import it.anto.jparest.model.User;
import it.anto.jparest.rest.filters.RequiresPermission;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anto
 */
@Path("/user")
@Component
@RequiresPermission("MovMag")
public class UserRoute {
    @Autowired
    IEntityRepository repository;
    
    @POST
    public User saveUser(User user)
    {
        repository.createEntity(user);
        return user;
    }
    
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") Long id )
    {
        return repository.find(id, User.class);
    }
   
}
