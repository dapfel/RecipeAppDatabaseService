package DatabaseAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DatabaseAccess.UserProfile.skillLevel;
import DatabaseAccess.Recipe.recipeType;
import DatabaseEntityClasses.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;

public class RecipeDbController {
    
    EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public RecipeDbController() {
       entityManagerFactory = Persistence.createEntityManagerFactory("RecipeAppDatabaseServicePU");
       entityManager = entityManagerFactory.createEntityManager();
    }
    
    public UserProfile validateSignIn(String email, String password) {
        Userprofiles profile = entityManager.find(Userprofiles.class, email);
        if (profile == null) // no such email exists
            return null;
        if (profile.getPassword().equals(password))
           return convertToUserProfile(profile);
        else // wrong password
            return null;
    }
    
    public UserProfile addUser(UserProfile user, String password) throws Exception {
        Userprofiles userP = convertToUserprofilesEntity(user, password);
        
        try {
            entityManager.getTransaction().begin();       
            entityManager.persist(userP);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw e;
        }
        
        return getUser(user.getEmail());
    }
    
    public UserProfile getUser(String email) {
        Userprofiles user = entityManager.find(Userprofiles.class, email);
        if (user == null) // no such email exists
            return null;
        else
           return convertToUserProfile(user);      
    }
    
    public String getPassword(String email) {
        Userprofiles user = entityManager.find(Userprofiles.class, email);
        if (user == null) // no such email exists
            return null;
        else
           return user.getPassword();      
    }
    
    public UserProfile changeProfilePic(String userEmail, byte[] pic) {
        entityManager.getTransaction().begin();
        Userprofiles user = entityManager.find(Userprofiles.class, userEmail);
        if (user == null) {
            entityManager.getTransaction().commit();
            return null;
        }      
        
        user.setProfilePic(pic);
        entityManager.getTransaction().commit();
        
        return getUser(userEmail);
    }
    
    public UserProfile addFollower(String userEmail, String followerEmail) {
        entityManager.getTransaction().begin();
        Userprofiles user = entityManager.find(Userprofiles.class, userEmail);
        Userprofiles follower = entityManager.find(Userprofiles.class, followerEmail);
        if (user == null || follower == null) {
            entityManager.getTransaction().commit();
            return null;
        }

        user.getFollowers().add(follower);
        entityManager.getTransaction().commit();
        
        return getUser(userEmail);
    }
    
    public UserProfile deleteFollower(String userEmail, String followerEmail) {
        entityManager.getTransaction().begin();
        Userprofiles user = entityManager.find(Userprofiles.class, userEmail);
        Userprofiles follower = entityManager.find(Userprofiles.class, followerEmail);
        if (user == null || follower == null) {
            entityManager.getTransaction().commit();
            return null;
        }
            
       user.getFollowers().remove(follower);
       follower.getFollowerOf().remove(user);
       entityManager.getTransaction().commit();
       
       return getUser(userEmail);
    }
    
    public int addRecipe(Recipe recipe) throws Exception {
        Recipes recipes = convertToRecipesEntity(recipe);
        int recipeID = 0;
        try {
            entityManager.getTransaction().begin();       
            entityManager.persist(recipes);
            entityManager.flush();
            recipeID = recipes.getRecipeid();
            addRecipeData(recipe, recipeID);
            entityManager.getTransaction().commit();
            
            return recipeID;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    private void addRecipeData(Recipe recipe, int recipeID) throws Exception {
        
        try {
            Recipes rec = entityManager.find(Recipes.class, recipeID);
        
            int i =1;
            for (byte[] picture : recipe.getImages()) {
                RecipepicturesPK picPK = new RecipepicturesPK(recipeID, i);
                Recipepictures pic = new Recipepictures(picPK);
                pic.setPicture(picture);
                rec.getRecipepicturesList().add(pic);
                i++;
            }
         
            for (String cuisine : recipe.getCuisines()) {
                RecipecuisinesPK cuisPK = new RecipecuisinesPK(recipeID, cuisine);
                Recipecuisines cuis = new Recipecuisines(cuisPK);
                rec.getRecipecuisinesList().add(cuis);
            }
        
            i = 1;
            for (String instruction : recipe.getInstructions()) {
                RecipeinstructionsPK instrucPK = new RecipeinstructionsPK(recipeID ,i);
                Recipeinstructions instruc = new Recipeinstructions(instrucPK);
                instruc.setInstruction(instruction);
                rec.getRecipeinstructionsList().add(instruc);
                i++;
            }
        
            for (String ingredient : recipe.getIngredients().keySet()) {
                RecipeingredientsPK ingredPK = new RecipeingredientsPK(recipeID, ingredient);
                Recipeingredients ingred = new Recipeingredients(ingredPK);
                ingred.setQuantity(recipe.getIngredients().get(ingredient));
                rec.getRecipeingredientsList().add(ingred);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    public Recipe getRecipe(int recipeID) {
        Recipes recipe = entityManager.find(Recipes.class, recipeID);
        if (recipe == null)
            return null;
        else
            return convertToRecipe(recipe);
    }
    
    public Recipe addComment(int recipeID, Comment comment) {  
        entityManager.getTransaction().begin();        
        Recipes rec = entityManager.find(Recipes.class, recipeID);
        if (rec == null) {
            entityManager.getTransaction().commit();
            return null;
        }

        Recipecomments com = new Recipecomments(recipeID, rec.getRecipecommentsList().size() + 1);
        Userprofiles author = entityManager.find(Userprofiles.class, comment.getAuthor());
        if (author == null) 
            return null;
        com.setCommentauthor(author);
        com.setCommenttext(comment.getComment());
        rec.getRecipecommentsList().add(com);
        entityManager.getTransaction().commit();
        
        return getRecipe(recipeID);
    }
  
    public Recipe addPicture(int recipeID, byte[] picture) throws Exception {
        entityManager.getTransaction().begin();        
        Recipes rec = entityManager.find(Recipes.class, recipeID);
        if (rec == null) {
            entityManager.getTransaction().commit();
            return null;
        }

        Recipepictures pic = new Recipepictures(recipeID, rec.getRecipepicturesList().size() + 1);
        pic.setPicture(picture);
        rec.getRecipepicturesList().add(pic);
        entityManager.getTransaction().commit();
        
        return getRecipe(recipeID);
    }
    
    public RecipeList searchRecipes(String skill, String cuisines, String type, String author, String freeText) {
        String[] cuisinesArray = null;
        if (cuisines != null)
            cuisinesArray = cuisines.split("\\+");
       
        String sqlString = "SELECT DISTINCT * FROM Recipes NATURAL JOIN Recipecuisines";
        
        if (!skill.equals("null") || !cuisines.equals("null") || !type.equals("null") || !author.equals("null"))
            sqlString += " WHERE";
       
        if (!skill.equals("null")) {
            sqlString += " skilllevel = '" + skill + "'";
        }
        if (!cuisines.equals("null")) {
            if (!skill.equals("null"))
                sqlString += " AND";
            for (int i = 0; i < cuisinesArray.length; i++)
                if (i == cuisinesArray.length - 1)
                    sqlString += " cuisine = '" + cuisinesArray[i] +"'";
                else
                    sqlString += " cuisine = '" + cuisinesArray[i] +"'" + " OR";
        }
        if (!type.equals("null")) {
            if (!cuisines.equals("null") || !skill.equals("null"))
                sqlString += " AND";
            sqlString += " type = '" + type + "'";
        }
        if (!author.equals("null")) {
            if (!type.equals("null") || !cuisines.equals("null") || !skill.equals("null"))
                sqlString += " AND";
            sqlString += " author = '" + author + "'";
        }
        
        Query searchRecipes;
        try {
            searchRecipes = entityManager.createNativeQuery(sqlString, Recipes.class);
        }
        catch (Exception e) {
            return null;
        }
                
        List<Recipes> resultList;
        resultList = searchRecipes.getResultList(); 
        
        ArrayList<Recipe> results = new ArrayList<>();
        // only adds to results those recipes that contain the user inputed keywords (freeText) in their name or description
        if (!freeText.equals("null")) { 
            for (int i = 0; i < resultList.size(); i++) {
                Recipes recipe = resultList.get(i);
                if (recipe.getName().contains(freeText) || recipe.getDescription().contains(freeText))
                    results.add(convertToRecipe(recipe));
            }
        }
        else { 
            for (int i = 0; i < resultList.size(); i++) {
            Recipes recipe = resultList.get(i);
            results.add(convertToRecipe(recipe));
            }
        }
        
        results = removeDuplicates(results);
        return (new RecipeList(results)).sortByDate();
    }
    
    private static ArrayList<Recipe> removeDuplicates(ArrayList<Recipe> recipes) {
        ArrayList<Recipe> result = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            for (int j = i + 1; j < recipes.size(); j++) {
              if (recipes.get(i).getRecipeId() == recipes.get(j).getRecipeId())
                  recipes.get(j).setRecipeId(-1);
            }
            if (recipes.get(i).getRecipeId() != -1)
                result.add(recipes.get(i));
        }
        return result;
    }
    
    public RecipeList getUsersRecipes(String email) {
        List<Recipes> resultList;
        ArrayList<Recipe> results = new ArrayList<>();
        Userprofiles user = entityManager.find(Userprofiles.class, email);
        if (user == null)
            return null;
        resultList = user.getRecipesList();
        for (int i = 0; i < resultList.size(); i++) {
            Recipes recipe = resultList.get(i);
            results.add(convertToRecipe(recipe));
        }
        
        return (new RecipeList(results)).sortByDate();
    }  
    
    public ArrayList<Recipe> getFollowedRecipes(String email) {
        List<Recipes> recipesList;
        ArrayList<Recipe> results = new ArrayList<>();
        Userprofiles user = entityManager.find(Userprofiles.class, email);
        if (user == null)
            return null;
        
        for (Userprofiles followedUser : user.getFollowerOf()) {
            recipesList = followedUser.getRecipesList();
            for (int i = 0; i < recipesList.size(); i++) {
                Recipes recipe = recipesList.get(i);
                results.add(convertToRecipe(recipe));
            }
        }
        
        return results;
    }  
    
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
    
    /**
     * convert a Userprofiles DB entity object into a UserProfile object
     */
    private UserProfile convertToUserProfile(Userprofiles user) {
        
        // convert skillLevel string to skillLevel enum type
        skillLevel skill = null;
        if (user.getSkilllevel() != null)
            skill = skillLevel.valueOf(user.getSkilllevel());
        
        // convert Followers lists of Userprofiles to lists of email strings
        ArrayList<String> followers = new ArrayList<>();
        ArrayList<String> followerOf = new ArrayList<>();
        for (Userprofiles userP : user.getFollowers()) {
            followers.add(userP.getEmail());
        }
        for (Userprofiles userP : user.getFollowerOf()) {
            followerOf.add(userP.getEmail());
        }
        
        ArrayList<String> cuisines = new ArrayList<>();
        for (Usercuisines userCuisine : user.getUsercuisinesList()) {
            cuisines.add(userCuisine.getUsercuisinesPK().getCuisine());
        }
        
        return new UserProfile(user.getEmail(), user.getFirstname(), user.getLastname(), cuisines, skill, user.getCountry(), user.getProfilePic(), followers, followerOf);
    }
    
     /**
     * convert a UserProfile object into a Userprofiles DB entity object
     */
    private Userprofiles convertToUserprofilesEntity(UserProfile user, String password) {
        Userprofiles userP = new Userprofiles();
        userP.setEmail(user.getEmail());
        userP.setPassword(password);
        userP.setFirstname(user.getFirstName());
        userP.setLastname(user.getLastName());
        userP.setCountry(user.getCountry());
        userP.setProfilePic(user.getProfilePic());
        
        // convert skillLevel enum type to skillLevel string
        String skill = null;
        if (user.getCookingSkills() != null) 
            skill = user.getCookingSkills().name();
        userP.setSkilllevel(skill);
        
        // initialize empty lists of Userprofiles
        ArrayList<Userprofiles> followers = new ArrayList<>();
        ArrayList<Userprofiles> followerOf = new ArrayList<>();
        userP.setFollowers(followers);
        userP.setFollowerOf(followerOf);
        
        ArrayList<Usercuisines> cuisines = new ArrayList<>();
        for (String cuisine : user.getCuisines()) {
            cuisines.add(new Usercuisines(user.getEmail(), cuisine));
        }
        userP.setUsercuisinesList(cuisines);
        
        ArrayList<Recipes> recipesList = new ArrayList<>();
        userP.setRecipesList(recipesList);
        
        return userP;
    }
    
     /**
     * convert a Recipes DB entity object into a Recipe object
     */
    private Recipe convertToRecipe(Recipes recipes) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipes.getRecipeid());
        recipe.setName(recipes.getName());
        recipe.setReleaseDate(recipes.getReleasedate());
        recipe.setAuthor(recipes.getAuthor().getEmail());
        recipe.setDescription(recipes.getDescription());
        
        // convert skill level string to skillLevel enum type
        skillLevel skill = null;
        if (recipe.getSkillLevel() != null)
            skill = skillLevel.valueOf(recipes.getSkilllevel());
        recipe.setSkillLevel(skill);
        
        // convert recipe type string to recipeType enum type
        recipeType type = null;
        if (recipe.getType() != null)
            type = recipeType.valueOf(recipes.getType());
        recipe.setType(type);
        
        for (Recipecuisines cuisine : recipes.getRecipecuisinesList()) {
            recipe.getCuisines().add(cuisine.getRecipecuisinesPK().getCuisine());
        }
        
        for (Recipeingredients ingredient : recipes.getRecipeingredientsList()) {
            recipe.getIngredients().put(ingredient.getRecipeingredientsPK().getIngredient(), ingredient.getQuantity());
        }
            
        for (Recipeinstructions instruction: recipes.getRecipeinstructionsList())
            recipe.getInstructions().add(instruction.getRecipeinstructionsPK().getInstructionnum() - 1, instruction.getInstruction());
        
        for (Recipepictures picture: recipes.getRecipepicturesList())
            recipe.getImages().add(picture.getPicture());
        
        for (Recipecomments comment: recipes.getRecipecommentsList())
            recipe.getComments().add(new Comment(comment.getCommentauthor().getEmail(),comment.getCommenttext(),
                                                 comment.getCommentauthor().getFirstname() + " " + comment.getCommentauthor().getLastname()));
        
        return recipe;
    }
    
    
     /**
     * convert a Recipe object into a Recipes DB entity object
     */
    private Recipes convertToRecipesEntity(Recipe recipe) {
        Recipes recipes = new Recipes();
        recipes.setName(recipe.getName());
        recipes.setReleasedate(recipe.getReleaseDate());
        Userprofiles author = entityManager.find(Userprofiles.class, recipe.getAuthor());
        recipes.setAuthor(author);
        recipes.setDescription(recipe.getDescription());
        
        // convert skillLevel enum type to skill level string
        String skill = null;
        if (recipe.getSkillLevel() != null)
            skill = recipe.getSkillLevel().name();
        recipes.setSkilllevel(skill);
        
        // convert recipeType enum type to recipe type string
        String type = null;
        if (recipe.getType() != null)
            type = recipe.getType().name();
        recipes.setType(type);
        
        ArrayList<Recipecuisines> cuisines = new ArrayList<>();
        recipes.setRecipecuisinesList(cuisines);
        
        ArrayList<Recipeinstructions> instructions = new ArrayList<>();
        recipes.setRecipeinstructionsList(instructions);
        
        ArrayList<Recipeingredients> ingredients = new ArrayList<>();
        recipes.setRecipeingredientsList(ingredients);
        
        ArrayList<Recipecomments> comments = new ArrayList<>();
        recipes.setRecipecommentsList(comments);
        
        ArrayList<Recipepictures> pictures = new ArrayList<>();
        recipes.setRecipepicturesList(pictures);
        
        return recipes;
    }

}
