/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAccess;

import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author dapfe
 */
@Path("recipe")
public class RecipeResource {

    @Context
    private UriInfo context;
    private RecipeDbController recipeDB;


    public RecipeResource() {
        recipeDB = new RecipeDbController();
    }

    @POST
    public Response addRecipe(Recipe recipe) {
       recipeDB.addRecipe(recipe);
       URI uri = context.getAbsolutePathBuilder().path(recipe.getName()).build();
       return Response.created(uri).build();
    }
    
    @GET
    @Path("{recipeID}")
    public Response getRecipe(@PathParam("recipeID") int recipeID) {
        return Response.ok(recipeDB.getRecipe(recipeID)).build();
    }
    
    @GET
    @Path("{skill}/{cuisine}/{type}/{author}")
    public Response searchRecipes(@PathParam("skill") String skill, @PathParam("cuisine") String cuisine,
                                  @PathParam("type") String type, @PathParam("author") String author) {
       RecipeList results = new RecipeList(recipeDB.searchRecipes(skill, cuisine, type, author));
       return Response.ok(results).build();
    }
    
    @GET
    @Path("getUsersRecipes/{email}")
    public Response getUsersRecipes(@PathParam("email") String email) {
        RecipeList results = new RecipeList(recipeDB.getUsersRecipes(email));
        return Response.ok(results).build();
    }
    
    @POST
    @Path("addComment/{recipeID}")
    public Response addComment(@PathParam("recipeID") int recipeID, Comment comment) {
       recipeDB.addComment(recipeID, comment);
       URI uri = context.getAbsolutePathBuilder().path("recipeID").build();
       return Response.created(uri).build();
    }
    
    @POST
    @Path("addPicture/{recipeID}")
    public Response addComment(@PathParam("recipeID") int recipeID, byte[] picture) {
       recipeDB.addPicture(recipeID, picture);
       URI uri = context.getAbsolutePathBuilder().path("recipeID").build();
       return Response.created(uri).build();
    }
    
    @GET
    @Path("getComments/{recipeID}")
    public Response getComments(@PathParam("recipeID") int recipeID) {
        return Response.ok(recipeDB.getComments(recipeID)).build();
    }
    
    @GET
    @Path("getPictures/{recipeID}")
    public Response getPictures(@PathParam("recipeID") int recipeID) {
        return Response.ok(recipeDB.getPictures(recipeID)).build();
    }
}
