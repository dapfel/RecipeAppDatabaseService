
package DatabaseAccess;

import com.google.gson.Gson;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author dapfe
 */
@Path("userProfile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserProfileResource {

    private RecipeDbController recipeDB;

    public UserProfileResource() {
        recipeDB = new RecipeDbController();
    }

    @GET
    @Path("signIn/{email}/{password}")
    public String validateSignIn(@PathParam("email") String email, @PathParam("password") String password) {
       String userJson = new Gson().toJson(recipeDB.validateSignIn(email, password));
       recipeDB.close();
       return userJson;
    }
    
    @POST
    @Path("add/{password}")
    public String addUser(String userJson, @PathParam("password") String password) {
       UserProfile user = new Gson().fromJson(userJson, UserProfile.class);
       recipeDB.addUser(user, password);
       recipeDB.close();
       return new Gson().toJson(new TextMessage("added " + user.getEmail()));
    }

    @GET
    @Path("delete/{email}")
    public String deleteUser(@PathParam("email") String email) {
        recipeDB.deleteUser(email);
        recipeDB.close();
        return new Gson().toJson(new TextMessage("deleted " + email));
    }
    
    @GET
    @Path("getUser/{email}")
    public String getUser(@PathParam("email") String email) {
        String userJson = new Gson().toJson(recipeDB.getUser(email));
        recipeDB.close();
        return userJson;
    }
    
    @GET
    @Path("addFollower/{userEmail}/{followerEmail}")
    public String addFollower(@PathParam("userEmail") String userEmail,@PathParam("followerEmail") String followerEmail) {
        recipeDB.addFollower(userEmail, followerEmail);
        recipeDB.close();
        return new Gson().toJson(new TextMessage("added follower " + followerEmail + " for " + userEmail));
    }
    
    @GET
    @Path("deleteFollower/{userEmail}/{followerEmail}")
    public String deleteFollower(@PathParam("userEmail") String userEmail, @PathParam("followerEmail") String followerEmail) {
        recipeDB.deleteFollower(userEmail, followerEmail);
        recipeDB.close();
        return new Gson().toJson(new TextMessage("deleted follower " + followerEmail + " for " + userEmail));
    }
    
     /**
     * hold text response from server to app
     */
    class TextMessage {
        private String message;

        public TextMessage(String message) {
            this.message = message;
        }
        
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        
        
    }
}
