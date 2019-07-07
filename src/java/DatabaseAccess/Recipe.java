package DatabaseAccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import DatabaseAccess.UserProfile.skillLevel;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipe implements Serializable {
    
   public enum recipeType {APPETIZER, SOUP, SALAD, MAIN, DESSERT}
    
    private String name;
    private int recipeId;
    private recipeType type;
    private skillLevel skillLevel;
    @XmlElement
    private ArrayList<String> instructions;
    @XmlElement
    private ArrayList<String> cuisines;
    private ArrayList<byte[]> images;
    @XmlElement
    private Map<String, String> ingredients;
    private ArrayList<Comment> comments;
    private String Author;
    private Date releaseDate;

    public Recipe() {
        this.comments = new ArrayList<>();
        this.cuisines = new ArrayList<>();
        this.images = new ArrayList<>();
        this.ingredients = new HashMap<>();
        this.instructions = new ArrayList<>();
    }

    public Recipe(String name, int recipeId, recipeType type, skillLevel skillLevel, 
                  ArrayList<String> instructions, ArrayList<String> cuisines, ArrayList<byte[]> images, 
                  Map<String, String> ingredients, ArrayList<Comment> comments, String Author, Date releaseDate) {
        this.name = name;
        this.recipeId = recipeId;
        this.type = type;
        this.skillLevel = skillLevel;
        this.instructions = instructions;
        this.cuisines = cuisines;
        this.images = images;
        this.ingredients = ingredients;
        this.comments = comments;
        this.Author = Author;
        this.releaseDate = releaseDate;
    }
    
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    public skillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(skillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }
    
    public void addInstruction(String instruction) {
        this.instructions.add(instruction);
    }

    public ArrayList<String> getCusines() {
        return cuisines;
    }

    public ArrayList<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<String> cuisines) {
        this.cuisines = cuisines;
    }
    
    public void addCuisine(String cuisine) {
        this.cuisines.add(cuisine);
    }

    public ArrayList<byte[]> getImages() {
        return images;
    }

    public void setImages(ArrayList<byte[]> images) {
        this.images = images;
    }

    public void addImage(byte[] image) {
        this.images.add(image);
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String name, String quantity) {
        this.ingredients.put(name, quantity);
    }

    public recipeType getType() {
        return type;
    }

    public void setType(recipeType type) {
        this.type = type;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
    
}
