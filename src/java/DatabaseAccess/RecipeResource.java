package DatabaseAccess;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author dapfe
 */
@Path("recipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeResource {

    private RecipeDbController recipeDB;


    public RecipeResource() {
        recipeDB = new RecipeDbController();
    }

    @POST
    @Path("add")
    public String addRecipe(String recipeJson) {
       Recipe recipe = new Gson().fromJson(recipeJson, Recipe.class);
       try {
           recipeDB.addRecipe(recipe);
       }
       catch (Exception e) {
           return null;
       }
       finally {
           recipeDB.close();
       }
       return new Gson().toJson(new TextMessage("added recipe " + recipe.getName()));
    }
    
    @GET
    @Path("getRecipe/{recipeID}")
    public String getRecipe(@PathParam("recipeID") int recipeID) {
        String recipeJson = new Gson().toJson(recipeDB.getRecipe(recipeID));
        recipeDB.close();
        return recipeJson;
    }
    
    @GET
    @Path("{skill}/{cuisine}/{type}/{author}")
    public String searchRecipes(@PathParam("skill") String skill, @PathParam("cuisine") String cuisine,
                                  @PathParam("type") String type, @PathParam("author") String author) {
       ArrayList<Recipe> results = recipeDB.searchRecipes(skill, cuisine, type, author);
       recipeDB.close();
       if (results == null)
           return new Gson().toJson(null);
       else
           return new Gson().toJson(new RecipeList(results));
    }
    
    @GET
    @Path("getUsersRecipes/{email}")
    public String getUsersRecipes(@PathParam("email") String email) {
        
        ArrayList<Recipe> results = recipeDB.getUsersRecipes(email);
        recipeDB.close();
        if (results == null) 
            return new Gson().toJson(null);
        else
            return new Gson().toJson(new RecipeList(results));
    }
    
    @POST
    @Path("addComment/{recipeID}")
    public String addComment(@PathParam("recipeID") int recipeID, String commentJson) {
       Comment comment = new Gson().fromJson(commentJson, Comment.class);
       try {
           recipeDB.addComment(recipeID, comment);
       }
       catch (Exception e) {
           return new Gson().toJson(null);
       }
       finally {
           recipeDB.close();
       }
       return new Gson().toJson(new TextMessage("added comment to recipe " + recipeID));
    }
    
    @POST
    @Path("addPicture/{recipeID}")
    public String addPicture(@PathParam("recipeID") int recipeID, String pictureJson) {
       byte[] picture = new Gson().fromJson(pictureJson, byte[].class);
       try {
           recipeDB.addPicture(recipeID, picture);
       }
       catch (Exception e) {
           return new Gson().toJson(null);
       }
       finally {
           recipeDB.close();
       }
       return new Gson().toJson(new TextMessage("picture added to recipe " + recipeID));
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