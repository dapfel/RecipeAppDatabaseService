/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseEntityClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dapfe
 */
@Entity
@Table(name = "RECIPEINGREDIENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipeingredients.findAll", query = "SELECT r FROM Recipeingredients r")
    , @NamedQuery(name = "Recipeingredients.findByRecipeid", query = "SELECT r FROM Recipeingredients r WHERE r.recipeingredientsPK.recipeid = :recipeid")
    , @NamedQuery(name = "Recipeingredients.findByIngredient", query = "SELECT r FROM Recipeingredients r WHERE r.recipeingredientsPK.ingredient = :ingredient")
    , @NamedQuery(name = "Recipeingredients.findByQuantity", query = "SELECT r FROM Recipeingredients r WHERE r.quantity = :quantity")})
public class Recipeingredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipeingredientsPK recipeingredientsPK;
    @Column(name = "QUANTITY", length = 30)
    private String quantity;
    @JoinColumn(name = "RECIPEID", referencedColumnName = "RECIPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;

    public Recipeingredients() {
    }

    public Recipeingredients(RecipeingredientsPK recipeingredientsPK) {
        this.recipeingredientsPK = recipeingredientsPK;
    }

    public Recipeingredients(int recipeid, String ingredient) {
        this.recipeingredientsPK = new RecipeingredientsPK(recipeid, ingredient);
    }

    public RecipeingredientsPK getRecipeingredientsPK() {
        return recipeingredientsPK;
    }

    public void setRecipeingredientsPK(RecipeingredientsPK recipeingredientsPK) {
        this.recipeingredientsPK = recipeingredientsPK;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeingredientsPK != null ? recipeingredientsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipeingredients)) {
            return false;
        }
        Recipeingredients other = (Recipeingredients) object;
        if ((this.recipeingredientsPK == null && other.recipeingredientsPK != null) || (this.recipeingredientsPK != null && !this.recipeingredientsPK.equals(other.recipeingredientsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Recipeingredients[ recipeingredientsPK=" + recipeingredientsPK + " ]";
    }
    
}
