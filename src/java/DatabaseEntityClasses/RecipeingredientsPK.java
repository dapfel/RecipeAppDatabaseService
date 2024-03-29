
package DatabaseEntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dapfe
 */
@Embeddable
public class RecipeingredientsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "RECIPEID")
    private int recipeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "INGREDIENT")
    private String ingredient;

    public RecipeingredientsPK() {
    }

    public RecipeingredientsPK(int recipeid, String ingredient) {
        this.recipeid = recipeid;
        this.ingredient = ingredient;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeid;
        hash += (ingredient != null ? ingredient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeingredientsPK)) {
            return false;
        }
        RecipeingredientsPK other = (RecipeingredientsPK) object;
        if (this.recipeid != other.recipeid) {
            return false;
        }
        if ((this.ingredient == null && other.ingredient != null) || (this.ingredient != null && !this.ingredient.equals(other.ingredient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.RecipeingredientsPK[ recipeid=" + recipeid + ", ingredient=" + ingredient + " ]";
    }
    
}
