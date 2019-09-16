
package DatabaseEntityClasses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import DatabaseAccess.UserProfile.skillLevel;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;

/**
 *
 * @author dapfe
 */
@Entity
@Table(name = "USERPROFILES")
public class Userprofiles implements Serializable {

    @OneToMany(mappedBy = "commentauthor")
    private List<Recipecomments> recipecommentsList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "COUNTRY")
    private String country;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "SKILLLEVEL")
    private skillLevel skilllevel;
    @Column(name = "PROFILEPIC")
    private byte[] profilePic;
    @JoinTable(name = "FOLLOWS", joinColumns = {
        @JoinColumn(name = "FOLLOWEDEMAIL", referencedColumnName = "EMAIL")}, inverseJoinColumns = {
        @JoinColumn(name = "FOLLOWEREMAIL", referencedColumnName = "EMAIL")})
    @ManyToMany
    private List<Userprofiles> followers;
    @ManyToMany(mappedBy = "followers")
    private List<Userprofiles> followerOf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userprofiles")
    private List<Usercuisines> usercuisinesList;
    @OneToMany(mappedBy = "author")
    private List<Recipes> recipesList;  

    public Userprofiles() {
    }

    public Userprofiles(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public skillLevel getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(skillLevel skilllevel) {
        this.skilllevel = skilllevel;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public List<Userprofiles> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Userprofiles> followers) {
        this.followers = followers;
    }

    public List<Userprofiles> getFollowerOf() {
        return followerOf;
    }

    public void setFollowerOf(List<Userprofiles> followerOf) {
        this.followerOf = followerOf;
    }

    public List<Usercuisines> getUsercuisinesList() {
        return usercuisinesList;
    }

    public void setUsercuisinesList(List<Usercuisines> usercuisinesList) {
        this.usercuisinesList = usercuisinesList;
    }

    public List<Recipes> getRecipesList() {
        return recipesList;
    }

    public void setRecipesList(List<Recipes> recipesList) {
        this.recipesList = recipesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userprofiles)) {
            return false;
        }
        Userprofiles other = (Userprofiles) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Userprofiles[ email=" + email + " ]";
    }

    public List<Recipecomments> getRecipecommentsList() {
        return recipecommentsList;
    }

    public void setRecipecommentsList(List<Recipecomments> recipecommentsList) {
        this.recipecommentsList = recipecommentsList;
    }
    
}
