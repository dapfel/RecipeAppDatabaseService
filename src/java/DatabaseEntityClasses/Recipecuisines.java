/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseEntityClasses;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dapfe
 */
@Entity
@Table(name = "RECIPECUISINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipecuisines.findAll", query = "SELECT r FROM Recipecuisines r")
    , @NamedQuery(name = "Recipecuisines.findByRecipeid", query = "SELECT r FROM Recipecuisines r WHERE r.recipecuisinesPK.recipeid = :recipeid")
    , @NamedQuery(name = "Recipecuisines.findByCuisine", query = "SELECT r FROM Recipecuisines r WHERE r.recipecuisinesPK.cuisine = :cuisine")})
public class Recipecuisines implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipecuisinesPK recipecuisinesPK;
    @JoinColumn(name = "RECIPEID", referencedColumnName = "RECIPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;

    public Recipecuisines() {
    }

    public Recipecuisines(RecipecuisinesPK recipecuisinesPK) {
        this.recipecuisinesPK = recipecuisinesPK;
    }

    public Recipecuisines(int recipeid, String cuisine) {
        this.recipecuisinesPK = new RecipecuisinesPK(recipeid, cuisine);
    }

    public RecipecuisinesPK getRecipecuisinesPK() {
        return recipecuisinesPK;
    }

    public void setRecipecuisinesPK(RecipecuisinesPK recipecuisinesPK) {
        this.recipecuisinesPK = recipecuisinesPK;
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
        hash += (recipecuisinesPK != null ? recipecuisinesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipecuisines)) {
            return false;
        }
        Recipecuisines other = (Recipecuisines) object;
        if ((this.recipecuisinesPK == null && other.recipecuisinesPK != null) || (this.recipecuisinesPK != null && !this.recipecuisinesPK.equals(other.recipecuisinesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Recipecuisines[ recipecuisinesPK=" + recipecuisinesPK + " ]";
    }
    
}
