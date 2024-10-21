/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.anto.jparest.rest.filters;

import com.sun.net.httpserver.HttpContext;
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

        // Recupera i claims dal token; 
        String[] genioTokenPermissions = requestContext.getHeaderString("x-genio-token").split(";");
        
        requestContext.setProperty("sessionid","alsdkjòladkjòad" );
        
        // Ottieni i permessi richiesti dall'annotazione
        String[] requiredPermissions = getAnnotation(requestContext);
        if (requiredPermissions == null) {
            return; // Nessuna annotazione trovata, quindi non è necessario eseguire alcuna verifica.
        }

       

        // 
        if (!hasRequiredPermissions(genioTokenPermissions, requiredPermissions)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Accesso negato: permessi insufficienti")
                    .build());
        }
    }

    private boolean hasRequiredPermissions(String[] userPermissions, String[] requiredPermissions) {
        if (userPermissions == null || userPermissions.length == 0) {
            return false; // Se non c'è un utente autenticato o non ha permessi, accesso negato.
        }
        // Controlla se l'utente ha almeno uno dei permessi richiesti.
        for (String permission : requiredPermissions) {
            if (Arrays.asList(userPermissions).contains(permission)) {
                return true;
            }
        }
        return false;
    }

    private String[] getAnnotation(ContainerRequestContext requestContext) {
        String[] classLevelPermissionsArray = new String[0];
        String[] methodLevelPermissionsArray = new String[0];
        if(resourceInfo.getResourceClass().isAnnotationPresent(RequiresPermission.class))
        {
            RequiresPermission requirePerm = resourceInfo.getResourceClass().getAnnotation(RequiresPermission.class);
            classLevelPermissionsArray = requirePerm.value();
        }
        if(resourceInfo.getResourceMethod().isAnnotationPresent(RequiresPermission.class))
        {
            RequiresPermission requirePerm = resourceInfo.getResourceMethod().getAnnotation(RequiresPermission.class);
            methodLevelPermissionsArray = requirePerm.value();
        }
        String[] permissionsArray = new String[classLevelPermissionsArray.length + methodLevelPermissionsArray.length ];
        System.arraycopy(classLevelPermissionsArray, 0, permissionsArray, 0, classLevelPermissionsArray.length);
        System.arraycopy(methodLevelPermissionsArray, 0, permissionsArray, classLevelPermissionsArray.length, methodLevelPermissionsArray.length);
        return permissionsArray;
    }

//    private User getCurrentUser(ContainerRequestContext requestContext) {
//        // Simulazione: recupera l'utente autenticato dalla richiesta (ad esempio dal token o sessione)
//        // In una vera implementazione, recupereresti l'utente dal token JWT, sessione o database.
//        return null; //new User("john_doe", Arrays.asList("Admin", "User")); // Simula un utente con permessi "Admin" e "User"
//    }
}
