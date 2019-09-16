
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
public class UsercuisinesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CUISINE")
    private String cuisine;

    public UsercuisinesPK() {
    }

    public UsercuisinesPK(String email, String cuisine) {
        this.email = email;
        this.cuisine = cuisine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (email != null ? email.hashCode() : 0);
        hash += (cuisine != null ? cuisine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsercuisinesPK)) {
            return false;
        }
        UsercuisinesPK other = (UsercuisinesPK) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        if ((this.cuisine == null && other.cuisine != null) || (this.cuisine != null && !this.cuisine.equals(other.cuisine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.UsercuisinesPK[ email=" + email + ", cuisine=" + cuisine + " ]";
    }
    
}
