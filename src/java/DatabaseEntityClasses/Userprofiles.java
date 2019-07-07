/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseEntityClasses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dapfe
 */
@Entity
@Table(name = "USERPROFILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userprofiles.findAll", query = "SELECT u FROM Userprofiles u")
    , @NamedQuery(name = "Userprofiles.findByEmail", query = "SELECT u FROM Userprofiles u WHERE u.email = :email")
    , @NamedQuery(name = "Userprofiles.findByPassword", query = "SELECT u FROM Userprofiles u WHERE u.password = :password")
    , @NamedQuery(name = "Userprofiles.findByFirstname", query = "SELECT u FROM Userprofiles u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Userprofiles.findByLastname", query = "SELECT u FROM Userprofiles u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "Userprofiles.findByCountry", query = "SELECT u FROM Userprofiles u WHERE u.country = :country")
    , @NamedQuery(name = "Userprofiles.findBySkilllevel", query = "SELECT u FROM Userprofiles u WHERE u.skilllevel = :skilllevel")})
public class Userprofiles implements Serializable {

    @OneToMany(mappedBy = "commentauthor")
    private List<Recipecomments> recipecommentsList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "SKILLLEVEL")
    private String skilllevel;
    @JoinTable(name = "FOLLOWS", joinColumns = {
        @JoinColumn(name = "FOLLOWEDEMAIL", referencedColumnName = "EMAIL")}, inverseJoinColumns = {
        @JoinColumn(name = "FOLLOWEREMAIL", referencedColumnName = "EMAIL")})
    @ManyToMany
    private List<Userprofiles> followers;
    @ManyToMany(mappedBy = "followers")
    private List<Userprofiles> followerOf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userprofiles")
    private List<Usercuisines> usercuisinesList;
    @OneToMany(mappedBy = "author")
    private List<Recipes> recipesList;

    public Userprofiles() {
    }

    public Userprofiles(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(String skilllevel) {
        this.skilllevel = skilllevel;
    }

    @XmlTransient
    public List<Userprofiles> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Userprofiles> followers) {
        this.followers = followers;
    }

    @XmlTransient
    public List<Userprofiles> getFollowerOf() {
        return followerOf;
    }

    public void setFollowerOf(List<Userprofiles> followerOf) {
        this.followerOf = followerOf;
    }

    @XmlTransient
    public List<Usercuisines> getUsercuisinesList() {
        return usercuisinesList;
    }

    public void setUsercuisinesList(List<Usercuisines> usercuisinesList) {
        this.usercuisinesList = usercuisinesList;
    }

    @XmlTransient
    public List<Recipes> getRecipesList() {
        return recipesList;
    }

    public void setRecipesList(List<Recipes> recipesList) {
        this.recipesList = recipesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userprofiles)) {
            return false;
        }
        Userprofiles other = (Userprofiles) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseEntityClasses.Userprofiles[ email=" + email + " ]";
    }

    @XmlTransient
    public List<Recipecomments> getRecipecommentsList() {
        return recipecommentsList;
    }

    public void setRecipecommentsList(List<Recipecomments> recipecommentsList) {
        this.recipecommentsList = recipecommentsList;
    }
    
}
