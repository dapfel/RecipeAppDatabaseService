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

/**
 *
 * @author dapfe
 */
@Embeddable
public class RecipepicturesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "RECIPEID")
    private int recipeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PICTURENUM")
    private int picturenum;

    public RecipepicturesPK() {
    }

    public RecipepicturesPK(int recipeid, int picturenum) {
        this.recipeid = recipeid;
        this.picturenum = picturenum;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public int getPicturenum() {
        return picturenum;
    }

    public void setPicturenum(int picturenum) {
        this.picturenum = picturenum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeid;
        hash += (int) picturenum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipepicturesPK)) {
            return false;
        }
        RecipepicturesPK other = (RecipepicturesPK) object;
        if (this.recipeid != other.recipeid) {
            return false;
        }
        if (this.picturenum != other.picturenum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.RecipepicturesPK[ recipeid=" + recipeid + ", picturenum=" + picturenum + " ]";
    }
    
}
