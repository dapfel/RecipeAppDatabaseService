package DatabaseEntityClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dapfe
 */
@Entity
@Table(name = "RECIPECOMMENTS")
public class Recipecomments implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipecommentsPK recipecommentsPK;
    @Column(name = "COMMENTTEXT", length = 1000)
    private String commenttext;
    @JoinColumn(name = "RECIPEID", referencedColumnName = "RECIPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;
    @JoinColumn(name = "COMMENTAUTHOR", referencedColumnName = "EMAIL")
    @ManyToOne
    private Userprofiles commentauthor;

    public Recipecomments() {
    }

    public Recipecomments(RecipecommentsPK recipecommentsPK) {
        this.recipecommentsPK = recipecommentsPK;
    }

    public Recipecomments(int recipeid, int commentnum) {
        this.recipecommentsPK = new RecipecommentsPK(recipeid, commentnum);
    }

    public RecipecommentsPK getRecipecommentsPK() {
        return recipecommentsPK;
    }

    public void setRecipecommentsPK(RecipecommentsPK recipecommentsPK) {
        this.recipecommentsPK = recipecommentsPK;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    public Userprofiles getCommentauthor() {
        return commentauthor;
    }

    public void setCommentauthor(Userprofiles commentauthor) {
        this.commentauthor = commentauthor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipecommentsPK != null ? recipecommentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipecomments)) {
            return false;
        }
        Recipecomments other = (Recipecomments) object;
        if ((this.recipecommentsPK == null && other.recipecommentsPK != null) || (this.recipecommentsPK != null && !this.recipecommentsPK.equals(other.recipecommentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Recipecomments[ recipecommentsPK=" + recipecommentsPK + " ]";
    }
    
}
