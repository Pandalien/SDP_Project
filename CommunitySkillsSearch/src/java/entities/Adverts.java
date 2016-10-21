/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * This is Entity Class for database
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Adverts.findByClosed", query = "SELECT a FROM Adverts a WHERE a.closed = :closed"),
    @NamedQuery(name = "Adverts.findByUserId", query = "SELECT a FROM Adverts a WHERE a.userId = :user"),
    @NamedQuery(name = "Adverts.findByVarious", query = "SELECT a FROM Adverts a WHERE a.suburbId = :suburbId AND a.classificationId = :classificationId")
/*    
    @NamedQuery(name = "Adverts.findByVarious", 
            query = "SELECT a FROM Adverts a WHERE " +
                    "a.suburbId = :suburbId AND " +
                    "a.classificationId = :classificationId " +
                    "a.title LIKE %:"
    )    
*/
})
public class Adverts implements Serializable {

    @Size(max = 45)
    @Column(name = "img")
    private String img;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @JoinColumn(name = "classification_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classification classificationId;
    @JoinColumn(name = "suburb_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Suburb suburbId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adverts")
    private Collection<Requirements> requirementsCollection;

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

    @XmlTransient
    public Collection<Requirements> getRequirementsCollection() {
        return requirementsCollection;
    }

    public void setRequirementsCollection(Collection<Requirements> requirementsCollection) {
        this.requirementsCollection = requirementsCollection;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Suburb getSuburbId() {
        return suburbId;
    }

    public void setSuburbId(Suburb suburbId) {
        this.suburbId = suburbId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
