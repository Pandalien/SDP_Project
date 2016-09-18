/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andyc
 */
@Entity
@Table(name = "suburb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suburb.findAll", query = "SELECT s FROM Suburb s"),
    @NamedQuery(name = "Suburb.findById", query = "SELECT s FROM Suburb s WHERE s.id = :id"),
    @NamedQuery(name = "Suburb.findBySuburb", query = "SELECT s FROM Suburb s WHERE s.suburb = :suburb")})
public class Suburb implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "suburbId")
    private Collection<Adverts> advertsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "suburb")
    private String suburb;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "suburbId")
    private Collection<User> userCollection;

    public Suburb() {
    }

    public Suburb(Integer id) {
        this.id = id;
    }

    public Suburb(Integer id, String suburb) {
        this.id = id;
        this.suburb = suburb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suburb)) {
            return false;
        }
        Suburb other = (Suburb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Suburb[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Adverts> getAdvertsCollection() {
        return advertsCollection;
    }

    public void setAdvertsCollection(Collection<Adverts> advertsCollection) {
        this.advertsCollection = advertsCollection;
    }
    
}
