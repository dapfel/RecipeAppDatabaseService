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
public class RecipecommentsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "RECIPEID")
    private int recipeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMMENTNUM")
    private int commentnum;

    public RecipecommentsPK() {
    }

    public RecipecommentsPK(int recipeid, int commentnum) {
        this.recipeid = recipeid;
        this.commentnum = commentnum;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeid;
        hash += (int) commentnum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipecommentsPK)) {
            return false;
        }
        RecipecommentsPK other = (RecipecommentsPK) object;
        if (this.recipeid != other.recipeid) {
            return false;
        }
        if (this.commentnum != other.commentnum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.RecipecommentsPK[ recipeid=" + recipeid + ", commentnum=" + commentnum + " ]";
    }
    
}
