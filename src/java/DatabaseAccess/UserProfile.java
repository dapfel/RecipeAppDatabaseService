package DatabaseAccess;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserProfile implements Serializable {
    
    public enum skillLevel{BEGINNER, INTERMEDIATE, PRO}
    
    private String email;
    private String firstName;
    private String lastName;
    private skillLevel cookingSkills;
    @XmlElement
    private ArrayList<String> cuisines;
    private String country;
    @XmlElement
    private ArrayList<String> followers; // emails of followers
    @XmlElement
    private ArrayList<String> followerOf; // emails of people the user follows

    public UserProfile() {
    }
    
    public UserProfile(String email, String firstName, String lastName, ArrayList<String> cuisines, skillLevel cookingSkills, String country, ArrayList<String> followers, ArrayList<String> followerOf) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cookingSkills = cookingSkills;
        this.cuisines = cuisines;
        this.country = country;
        this.followers = followers;
        this.followerOf = followerOf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public skillLevel getCookingSkills() {
        return cookingSkills;
    }

    public ArrayList<String> getCuisine() {
        return cuisines;
    }

    public String getCountry() {
        return country;
    }
    
    public ArrayList<String> getFollowers() {
        return followers;
    }
    
    public ArrayList<String> getFollowerOf() {
        return followerOf;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCookingSkills(skillLevel cookingSkills) {
        this.cookingSkills = cookingSkills;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<String> cuisines) {
        this.cuisines = cuisines;
    }

    public void setCuisine(String[] cuisine) {
        for(int i=0; i<cuisine.length; i++)
            this.cuisines.add(cuisine[i]);
    }

    public void addCuisine(String cuisine) {
        this.cuisines.add(cuisine);
    }

    public void removeCuisine(String cuisine) {
        for (String element : this.cuisines) {
            if (element.equals(cuisine)) {
                this.cuisines.remove(cuisine);
                return;
            }
        }
    }
    
    public void setfollowers(String[] followers) {
        for(int i=0; i<followers.length; i++)
            this.followers.add(followers[i]);
    }

    public void addFollower(String follower) {
        this.followers.add(follower);
    }

    public void removeFollower(String follower) {
        for (String element : this.followers) {
            if (element.equals(follower)) {
                this.followers.remove(follower);
                return;
            }
        }
    }
    
    public void setfollowerOf(String[] followerOf) {
        for(int i=0; i<followerOf.length; i++)
            this.followerOf.add(followerOf[i]);
    }

    public void addFollowerOf(String followerOf) {
        this.followerOf.add(followerOf);
    }

    public void removeFollowerOf(String followerOf) {
        for (String element : this.followerOf) {
            if (element.equals(followerOf)) {
                this.followerOf.remove(followerOf);
                return;
            }
        }
    }
}
    