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
import javax.persistence.Lob;
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
@Table(name = "RECIPEPICTURES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipepictures.findAll", query = "SELECT r FROM Recipepictures r")
    , @NamedQuery(name = "Recipepictures.findByRecipeid", query = "SELECT r FROM Recipepictures r WHERE r.recipepicturesPK.recipeid = :recipeid")
    , @NamedQuery(name = "Recipepictures.findByPicturenum", query = "SELECT r FROM Recipepictures r WHERE r.recipepicturesPK.picturenum = :picturenum")})
public class Recipepictures implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipepicturesPK recipepicturesPK;
    @Lob
    @Column(name = "PICTURE")
    private byte[] picture;
    @JoinColumn(name = "RECIPEID", referencedColumnName = "RECIPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;

    public Recipepictures() {
    }

    public Recipepictures(RecipepicturesPK recipepicturesPK) {
        this.recipepicturesPK = recipepicturesPK;
    }

    public Recipepictures(int recipeid, int picturenum) {
        this.recipepicturesPK = new RecipepicturesPK(recipeid, picturenum);
    }

    public RecipepicturesPK getRecipepicturesPK() {
        return recipepicturesPK;
    }

    public void setRecipepicturesPK(RecipepicturesPK recipepicturesPK) {
        this.recipepicturesPK = recipepicturesPK;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
        hash += (recipepicturesPK != null ? recipepicturesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipepictures)) {
            return false;
        }
        Recipepictures other = (Recipepictures) object;
        if ((this.recipepicturesPK == null && other.recipepicturesPK != null) || (this.recipepicturesPK != null && !this.recipepicturesPK.equals(other.recipepicturesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Recipepictures[ recipepicturesPK=" + recipepicturesPK + " ]";
    }
    
}
