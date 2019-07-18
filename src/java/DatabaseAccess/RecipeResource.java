package DatabaseAccess;

import com.google.gson.Gson;
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
       recipeDB.addRecipe(recipe);
       return new Gson().toJson(new TextMessage("added recipe " + recipe.getName()));
    }
    
    @GET
    @Path("getRecipe/{recipeID}")
    public String getRecipe(@PathParam("recipeID") int recipeID) {
        return new Gson().toJson(recipeDB.getRecipe(recipeID));
    }
    
    @GET
    @Path("{skill}/{cuisine}/{type}/{author}")
    public String searchRecipes(@PathParam("skill") String skill, @PathParam("cuisine") String cuisine,
                                  @PathParam("type") String type, @PathParam("author") String author) {
       RecipeList results = new RecipeList(recipeDB.searchRecipes(skill, cuisine, type, author));
       return new Gson().toJson(results);
    }
    
    @GET
    @Path("getUsersRecipes/{email}")
    public String getUsersRecipes(@PathParam("email") String email) {
        RecipeList results = new RecipeList(recipeDB.getUsersRecipes(email));
        return new Gson().toJson(results);
    }
    
    @POST
    @Path("addComment/{recipeID}")
    public String addComment(@PathParam("recipeID") int recipeID, String commentJson) {
       Comment comment = new Gson().fromJson(commentJson, Comment.class);
       recipeDB.addComment(recipeID, comment);
       return new Gson().toJson(new TextMessage("added comment to recipe " + recipeID));
    }
    
    @POST
    @Path("addPicture/{recipeID}")
    public String addPicture(@PathParam("recipeID") int recipeID, String pictureJson) {
       byte[] picture = new Gson().fromJson(pictureJson, byte[].class);
       recipeDB.addPicture(recipeID, picture);
       return new Gson().toJson(new TextMessage("picture added to recipe " + recipeID));
    }
    
    @GET
    @Path("getComments/{recipeID}")
    public String getComments(@PathParam("recipeID") int recipeID) {
        return new Gson().toJson(recipeDB.getComments(recipeID));
    }
    
    @GET
    @Path("getPictures/{recipeID}")
    public String getPictures(@PathParam("recipeID") int recipeID) {
        return new Gson().toJson(recipeDB.getPictures(recipeID));
    }
    
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