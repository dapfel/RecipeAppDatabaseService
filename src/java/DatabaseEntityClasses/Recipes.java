
package DatabaseEntityClasses;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import DatabaseAccess.UserProfile.skillLevel;
import DatabaseAccess.Recipe.recipeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author dapfe
 */
@Entity
@Table(name = "RECIPES")
public class Recipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RECIPEID")
    private Integer recipeid;
    @Column(name = "NAME")
    private String name;
    @Column (name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TYPE")
    private recipeType type;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "SKILLLEVEL")
    private skillLevel skilllevel;
    @Column(name = "RELEASEDATE")
    @Temporal(TemporalType.DATE)
    private Date releasedate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private List<Recipecomments> recipecommentsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private List<Recipecuisines> recipecuisinesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private List<Recipepictures> recipepicturesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private List<Recipeinstructions> recipeinstructionsList;
    @JoinColumn(name = "AUTHOR", referencedColumnName = "EMAIL")
    @ManyToOne
    private Userprofiles author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private List<Recipeingredients> recipeingredientsList;  

    public Recipes() {
    }

    public Recipes(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public Integer getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public recipeType getType() {
        return type;
    }
    public void setType(recipeType type) {
        this.type = type;
    }

    public void setSkilllevel(skillLevel skilllevel) {
        this.skilllevel = skilllevel;
    }

    public skillLevel getSkilllevel() {
        return skilllevel;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public List<Recipecomments> getRecipecommentsList() {
        return recipecommentsList;
    }

    public void setRecipecommentsList(List<Recipecomments> recipecommentsList) {
        this.recipecommentsList = recipecommentsList;
    }

    public List<Recipecuisines> getRecipecuisinesList() {
        return recipecuisinesList;
    }

    public void setRecipecuisinesList(List<Recipecuisines> recipecuisinesList) {
        this.recipecuisinesList = recipecuisinesList;
    }

    public List<Recipepictures> getRecipepicturesList() {
        return recipepicturesList;
    }

    public void setRecipepicturesList(List<Recipepictures> recipepicturesList) {
        this.recipepicturesList = recipepicturesList;
    }

    public List<Recipeinstructions> getRecipeinstructionsList() {
        return recipeinstructionsList;
    }

    public void setRecipeinstructionsList(List<Recipeinstructions> recipeinstructionsList) {
        this.recipeinstructionsList = recipeinstructionsList;
    }

    public Userprofiles getAuthor() {
        return author;
    }

    public void setAuthor(Userprofiles author) {
        this.author = author;
    }
    
    public List<Recipeingredients> getRecipeingredientsList() {
        return recipeingredientsList;
    }

    public void setRecipeingredientsList(List<Recipeingredients> recipeingredientsList) {
        this.recipeingredientsList = recipeingredientsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeid != null ? recipeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.recipeid == null && other.recipeid != null) || (this.recipeid != null && !this.recipeid.equals(other.recipeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Recipes[ recipeid=" + recipeid + " ]";
    }
    
}
