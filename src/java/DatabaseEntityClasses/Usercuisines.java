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
@Table(name = "USERCUISINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usercuisines.findAll", query = "SELECT u FROM Usercuisines u")
    , @NamedQuery(name = "Usercuisines.findByEmail", query = "SELECT u FROM Usercuisines u WHERE u.usercuisinesPK.email = :email")
    , @NamedQuery(name = "Usercuisines.findByCuisine", query = "SELECT u FROM Usercuisines u WHERE u.usercuisinesPK.cuisine = :cuisine")})
public class Usercuisines implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsercuisinesPK usercuisinesPK;
    @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Userprofiles userprofiles;

    public Usercuisines() {
    }

    public Usercuisines(UsercuisinesPK usercuisinesPK) {
        this.usercuisinesPK = usercuisinesPK;
    }

    public Usercuisines(String email, String cuisine) {
        this.usercuisinesPK = new UsercuisinesPK(email, cuisine);
    }

    public UsercuisinesPK getUsercuisinesPK() {
        return usercuisinesPK;
    }

    public void setUsercuisinesPK(UsercuisinesPK usercuisinesPK) {
        this.usercuisinesPK = usercuisinesPK;
    }

    public Userprofiles getUserprofiles() {
        return userprofiles;
    }

    public void setUserprofiles(Userprofiles userprofiles) {
        this.userprofiles = userprofiles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usercuisinesPK != null ? usercuisinesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usercuisines)) {
            return false;
        }
        Usercuisines other = (Usercuisines) object;
        if ((this.usercuisinesPK == null && other.usercuisinesPK != null) || (this.usercuisinesPK != null && !this.usercuisinesPK.equals(other.usercuisinesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Usercuisines[ usercuisinesPK=" + usercuisinesPK + " ]";
    }
    
}
