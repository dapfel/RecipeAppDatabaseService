
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
@Table(name = "RECIPEINSTRUCTIONS")
public class Recipeinstructions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipeinstructionsPK recipeinstructionsPK;
    @Column(name = "INSTRUCTION", length = 400)
    private String instruction;
    @JoinColumn(name = "RECIPEID", referencedColumnName = "RECIPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;

    public Recipeinstructions() {
    }

    public Recipeinstructions(RecipeinstructionsPK recipeinstructionsPK) {
        this.recipeinstructionsPK = recipeinstructionsPK;
    }

    public Recipeinstructions(int recipeid, int instructionnum) {
        this.recipeinstructionsPK = new RecipeinstructionsPK(recipeid, instructionnum);
    }

    public RecipeinstructionsPK getRecipeinstructionsPK() {
        return recipeinstructionsPK;
    }

    public void setRecipeinstructionsPK(RecipeinstructionsPK recipeinstructionsPK) {
        this.recipeinstructionsPK = recipeinstructionsPK;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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
        hash += (recipeinstructionsPK != null ? recipeinstructionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipeinstructions)) {
            return false;
        }
        Recipeinstructions other = (Recipeinstructions) object;
        if ((this.recipeinstructionsPK == null && other.recipeinstructionsPK != null) || (this.recipeinstructionsPK != null && !this.recipeinstructionsPK.equals(other.recipeinstructionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Recipeinstructions[ recipeinstructionsPK=" + recipeinstructionsPK + " ]";
    }
    
}
