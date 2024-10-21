/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.anto.jparest.rest.filters;

import it.anto.jparest.model.User;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aoliv
 */

@Provider
@RequiresPermission("") // Associa questo filtro all'annotazione RequiresPermissions
@Priority(Priorities.AUTHORIZATION)
public class GenioAuthorizationFilter implements ContainerRequestFilter {
 
    @Context
    private ResourceInfo resourceInfo;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Ottieni l'utente autenticato (simulazione)
        User currentUser = getCurrentUser(requestContext);

        // Ottieni i permessi richiesti dall'annotazione
        String[] requiredPermissions = getAnnotation(requestContext);
        if (requiredPermissions == null) {
            return; // Nessuna annotazione trovata, quindi non è necessario eseguire alcuna verifica.
        }

       

        // Verifica se l'utente ha almeno uno dei permessi richiesti
        if (!hasRequiredPermissions(currentUser, requiredPermissions)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Accesso negato: permessi insufficienti")
                    .build());
        }
    }

    private boolean hasRequiredPermissions(User user, String[] requiredPermissions) {
        if (user == null || user.getPermissions() == null) {
            return false; // Se non c'è un utente autenticato o non ha permessi, accesso negato.
        }
        // Controlla se l'utente ha almeno uno dei permessi richiesti.
        for (String permission : requiredPermissions) {
            if (user.getPermissions().contains(permission)) {
                return true;
            }
        }
        return false;
    }

    private String[] getAnnotation(ContainerRequestContext requestContext) {
        if(resourceInfo.getResourceClass().isAnnotationPresent(RequiresPermission.class))
        {
            RequiresPermission requirePerm = resourceInfo.getResourceClass().getAnnotation(RequiresPermission.class);
            requirePerm.value();
        }
        if(resourceInfo.getResourceMethod().isAnnotationPresent(RequiresPermission.class))
        {
            RequiresPermission requirePerm = resourceInfo.getResourceMethod().getAnnotation(RequiresPermission.class);
            requirePerm.value();
        }
    }

    private User getCurrentUser(ContainerRequestContext requestContext) {
        // Simulazione: recupera l'utente autenticato dalla richiesta (ad esempio dal token o sessione)
        // In una vera implementazione, recupereresti l'utente dal token JWT, sessione o database.
        return null; //new User("john_doe", Arrays.asList("Admin", "User")); // Simula un utente con permessi "Admin" e "User"
    }
}
