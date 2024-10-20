/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package it.anto.jparest.routes;

import it.anto.jpaproj.datamodel.User;
import it.anto.jpaproj.services.IEntityRepository;
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
