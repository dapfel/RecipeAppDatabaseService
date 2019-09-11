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
       int recipeID = 0;
       try {
           recipeID = recipeDB.addRecipe(recipe);
       }
       catch (Exception e) {
           return null;
       }
       finally {
           recipeDB.close();
       }
       recipe.setRecipeId(recipeID);
       return new Gson().toJson(recipe);
    }
    
    @GET
    @Path("getRecipe/{recipeID}")
    public String getRecipe(@PathParam("recipeID") int recipeID) {
        String recipeJson = new Gson().toJson(recipeDB.getRecipe(recipeID));
        recipeDB.close();
        return recipeJson;
    }
    
    @GET
    @Path("{skill}/{cuisines}/{type}/{author}/{freeText}")
    public String searchRecipes(@PathParam("skill") String skill, @PathParam("cuisines") String cuisines,
                                @PathParam("type") String type, @PathParam("author") String author, 
                                @PathParam("freeText") String freeText) {
       RecipeList results = recipeDB.searchRecipes(skill, cuisines, type, author, freeText);
       recipeDB.close();
       if (results == null)
           return new Gson().toJson(null);
       else
           return new Gson().toJson(new RecipeList(results));
    }
    
    @GET
    @Path("getUsersRecipes/{email}")
    public String getUsersRecipes(@PathParam("email") String email) {
        
        RecipeList results = recipeDB.getUsersRecipes(email);
        recipeDB.close();
        if (results == null) 
            return new Gson().toJson(null);
        else
            return new Gson().toJson(new RecipeList(results));
    }
    
    @GET
    @Path("getFollowedRecipes/{email}")
    public String getFollowedRecipes(@PathParam("email") String email) {
        
        ArrayList<Recipe> results = recipeDB.getFollowedRecipes(email);
        recipeDB.close();
        if (results == null) 
            return new Gson().toJson(null);
        else
            return new Gson().toJson(results);
    }
    
    @POST
    @Path("addComment/{recipeID}")
    public String addComment(@PathParam("recipeID") int recipeID, String commentJson) {
       Comment comment = new Gson().fromJson(commentJson, Comment.class);
       Recipe recipe = null;
       try {
           recipe = recipeDB.addComment(recipeID, comment);
       }
       catch (Exception e) {
           recipe = null;
       }
       finally {
           recipeDB.close();
       }
       return new Gson().toJson(recipe);
    }
    
    @POST
    @Path("addPicture/{recipeID}")
    public String addPicture(@PathParam("recipeID") int recipeID, String pictureJson) {
       byte[] picture = new Gson().fromJson(pictureJson, byte[].class);
       Recipe recipe = null;
       try {
           recipe = recipeDB.addPicture(recipeID, picture);
       }
       catch (Exception e) {
           recipe = null;
       }
       finally {
           recipeDB.close();
       }
       return new Gson().toJson(recipe);
    }
}