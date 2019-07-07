
package DatabaseAccess;

import java.net.URI;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author dapfe
 */
@Path("/userProfile")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class UserProfileResource {

    @Context
    private UriInfo context;
    private RecipeDbController recipeDB;

    public UserProfileResource() {
        recipeDB = new RecipeDbController();
    }

    @GET
    @Path("signIn/{email}/{password}")
    public Response validateSignIn(@PathParam("email") String email, @PathParam("password") String password) {
       return Response.ok(recipeDB.validateSignIn(email, password)).build();
    }
    
    @POST
    @Path("add/{password}")
    public Response addUser(UserProfile user, @PathParam("password") String password) {
       recipeDB.addUser(user, password);
       URI uri = context.getAbsolutePathBuilder().path(user.getEmail()).build();
       return Response.created(uri).build();
    }

    @DELETE
    @Path("{email}")
    public Response deleteUser(@PathParam("email") String email) {
        recipeDB.deleteUser(email);
        return Response.noContent().build();
    }
    
    @GET
    @Path("{email}")
    public Response getUser(@PathParam("email") String email) {
        return Response.ok(recipeDB.getUser(email)).build();
    }
    
    @GET
    @Path("{userEmail}/{followerEmail}")
    public Response addFollower(@PathParam("userEmail") String userEmail,@PathParam("followerEmail") String followerEmail) {
        recipeDB.addFollower(userEmail, followerEmail);
               URI uri = context.getAbsolutePathBuilder().path(userEmail).build();
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{userEmail}/{followerEmail}")
    public Response deleteFollower(@PathParam("userEmail") String userEmail, @PathParam("followerEmail") String followerEmail) {
        recipeDB.deleteFollower(userEmail, followerEmail);
        return Response.noContent().build();
    }
}
