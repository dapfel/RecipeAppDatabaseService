/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RecipecuisinesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "RECIPEID")
    private int recipeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CUISINE")
    private String cuisine;

    public RecipecuisinesPK() {
    }

    public RecipecuisinesPK(int recipeid, String cuisine) {
        this.recipeid = recipeid;
        this.cuisine = cuisine;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeid;
        hash += (cuisine != null ? cuisine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipecuisinesPK)) {
            return false;
        }
        RecipecuisinesPK other = (RecipecuisinesPK) object;
        if (this.recipeid != other.recipeid) {
            return false;
        }
        if ((this.cuisine == null && other.cuisine != null) || (this.cuisine != null && !this.cuisine.equals(other.cuisine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.RecipecuisinesPK[ recipeid=" + recipeid + ", cuisine=" + cuisine + " ]";
    }
    
}
