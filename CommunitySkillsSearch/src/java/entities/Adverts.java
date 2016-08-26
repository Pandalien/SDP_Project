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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "adverts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adverts.findAll", query = "SELECT a FROM Adverts a"),
    @NamedQuery(name = "Adverts.findById", query = "SELECT a FROM Adverts a WHERE a.id = :id"),
    @NamedQuery(name = "Adverts.findByTitle", query = "SELECT a FROM Adverts a WHERE a.title = :title"),
    @NamedQuery(name = "Adverts.findByContent", query = "SELECT a FROM Adverts a WHERE a.content = :content"),
    @NamedQuery(name = "Adverts.findByAdvertscol", query = "SELECT a FROM Adverts a WHERE a.advertscol = :advertscol"),
    @NamedQuery(name = "Adverts.findByClosed", query = "SELECT a FROM Adverts a WHERE a.closed = :closed")})
public class Adverts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "content")
    private String content;
    @Size(max = 45)
    @Column(name = "advertscol")
    private String advertscol;
    @Column(name = "closed")
    private Boolean closed;
    @JoinTable(name = "requirements", joinColumns = {
        @JoinColumn(name = "adverts_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "skills_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Skills> skillsCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adverts")
    private Collection<Responders> respondersCollection;

    public Adverts() {
    }

    public Adverts(Integer id) {
        this.id = id;
    }

    public Adverts(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdvertscol() {
        return advertscol;
    }

    public void setAdvertscol(String advertscol) {
        this.advertscol = advertscol;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    @XmlTransient
    public Collection<Skills> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(Collection<Skills> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Responders> getRespondersCollection() {
        return respondersCollection;
    }

    public void setRespondersCollection(Collection<Responders> respondersCollection) {
        this.respondersCollection = respondersCollection;
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
        if (!(object instanceof Adverts)) {
            return false;
        }
        Adverts other = (Adverts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Adverts[ id=" + id + " ]";
    }
    
}