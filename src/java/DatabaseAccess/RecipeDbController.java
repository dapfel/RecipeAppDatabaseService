package DatabaseAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DatabaseEntityClasses.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import DatabaseAccess.UserProfile.skillLevel;
import DatabaseAccess.Recipe.recipeType;

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
        
        UserProfile userP = convertToUserProfile(user);        
        entityManager.getTransaction().commit();

        return userP;
    }
    
        public UserProfile updateUserProfile(String userEmail, String newPassword, String newFirstName, 
                                             String newLastName, String newCountry, String newCuisines, String newSkillLevel) {
        entityManager.getTransaction().begin();
        Userprofiles user = entityManager.find(Userprofiles.class, userEmail);
        if (user == null) {
            entityManager.getTransaction().commit();
            return null;
        }

        if (!newPassword.equals("null"))
            user.setPassword(newPassword);
        if (!newFirstName.equals("null"))
            user.setFirstname(newFirstName);
        if (!newLastName.equals("null"))
            user.setLastname(newLastName);
        if (!newCountry.equals("null")) 
            user.setCountry(newCountry);
        if (!newSkillLevel.equals("null"))
            user.setSkilllevel(skillLevel.valueOf(newSkillLevel));
        if (!newCuisines.equals("null")) {
            String[] newCuisinesArray = newCuisines.split("\\+");
            for (String cuisineName : newCuisinesArray) {
                Usercuisines newCuisine = new Usercuisines(userEmail, cuisineName);
                if (!user.getUsercuisinesList().contains(newCuisine))
                    user.getUsercuisinesList().add(newCuisine);
            }
        }
        UserProfile userP = convertToUserProfile(user);
        entityManager.getTransaction().commit();
        
        return userP;
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
        
        UserProfile userP = convertToUserProfile(user);
        entityManager.getTransaction().commit();
        
        return userP;
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
       UserProfile userP = convertToUserProfile(user);
       entityManager.getTransaction().commit();
       
       return userP;
    }
    
    public int addRecipe(Recipe recipe) throws Exception {
        Recipes recipes = convertToRecipesEntity(recipe);
        try {
            entityManager.getTransaction().begin();       
            entityManager.persist(recipes);
            entityManager.flush();
            addRecipeData(recipe, recipes);
            entityManager.getTransaction().commit();
            
            return recipes.getRecipeid();
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    private void addRecipeData(Recipe recipe, Recipes recipes) throws Exception {
        
        try {
            int recipeID = recipes.getRecipeid();
        
            int i =1;
            for (byte[] picture : recipe.getImages()) {
                RecipepicturesPK picPK = new RecipepicturesPK(recipeID, i);
                Recipepictures pic = new Recipepictures(picPK);
                pic.setPicture(picture);
                recipes.getRecipepicturesList().add(pic);
                i++;
            }
         
            for (String cuisine : recipe.getCuisines()) {
                RecipecuisinesPK cuisPK = new RecipecuisinesPK(recipeID, cuisine);
                Recipecuisines cuis = new Recipecuisines(cuisPK);
                recipes.getRecipecuisinesList().add(cuis);
            }
        
            i = 1;
            for (String instruction : recipe.getInstructions()) {
                RecipeinstructionsPK instrucPK = new RecipeinstructionsPK(recipeID ,i);
                Recipeinstructions instruc = new Recipeinstructions(instrucPK);
                instruc.setInstruction(instruction);
                recipes.getRecipeinstructionsList().add(instruc);
                i++;
            }
        
            for (String ingredient : recipe.getIngredients().keySet()) {
                RecipeingredientsPK ingredPK = new RecipeingredientsPK(recipeID, ingredient);
                Recipeingredients ingred = new Recipeingredients(ingredPK);
                ingred.setQuantity(recipe.getIngredients().get(ingredient));
                recipes.getRecipeingredientsList().add(ingred);
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
        
        Recipe recipe = convertToRecipe(rec);
        entityManager.getTransaction().commit();
        
        return recipe;
    }
    
    public RecipeList searchRecipes(String skill, String cuisines, String type, String author, String freeText) {
        String[] cuisinesArray = cuisines.split("\\+");
        
        String recipeColumnNames = "recipeID, name, description, type, author, skillLevel, releaseDate";
        String sqlString = "SELECT DISTINCT " + recipeColumnNames + " FROM Recipes NATURAL JOIN Recipecuisines";
        
        if (!skill.equals("null") || !cuisines.equals("null") || !type.equals("null") || !author.equals("null"))
            sqlString += " WHERE";
       
        if (!skill.equals("null")) {
            int skillNum = skillLevel.valueOf(skill).ordinal();
            sqlString += " skillLevel = '" + skillNum + "'";
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
            int typeNum = recipeType.valueOf(type).ordinal();
            if (!cuisines.equals("null") || !skill.equals("null"))
                sqlString += " AND";
            sqlString += " type = '" + typeNum + "'";
        }
        if (!author.equals("null")) {
            if (!type.equals("null") || !cuisines.equals("null") || !skill.equals("null"))
                sqlString += " AND";
            sqlString += " author = '" + author + "'";
        }
        sqlString += " ORDER BY releaseDate DESC";
        
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
        
        return new RecipeList(results);
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
        
        return new UserProfile(user.getEmail(), user.getFirstname(), user.getLastname(), 
                               cuisines, user.getSkilllevel(), user.getCountry(), user.getProfilePic(), followers, followerOf);
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
        userP.setSkilllevel(user.getCookingSkills());
        
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
        recipe.setSkillLevel(recipes.getSkilllevel());
        recipe.setType(recipes.getType());
        
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
        recipes.setSkilllevel(recipe.getSkillLevel());
        recipes.setType(recipe.getType());
        
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
