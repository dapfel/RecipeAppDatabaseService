
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
public class RecipeinstructionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "RECIPEID")
    private int recipeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTRUCTIONNUM")
    private int instructionnum;

    public RecipeinstructionsPK() {
    }

    public RecipeinstructionsPK(int recipeid, int instructionnum) {
        this.recipeid = recipeid;
        this.instructionnum = instructionnum;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public int getInstructionnum() {
        return instructionnum;
    }

    public void setInstructionnum(int instructionnum) {
        this.instructionnum = instructionnum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeid;
        hash += (int) instructionnum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeinstructionsPK)) {
            return false;
        }
        RecipeinstructionsPK other = (RecipeinstructionsPK) object;
        if (this.recipeid != other.recipeid) {
            return false;
        }
        if (this.instructionnum != other.instructionnum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.RecipeinstructionsPK[ recipeid=" + recipeid + ", instructionnum=" + instructionnum + " ]";
    }
    
}
