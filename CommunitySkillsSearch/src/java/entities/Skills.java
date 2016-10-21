/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * This is Entity Class for database
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andyc
 */
@Entity
@Table(name = "skills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skills.findAll", query = "SELECT s FROM Skills s"),
    @NamedQuery(name = "Skills.findById", query = "SELECT s FROM Skills s WHERE s.id = :id"),
    @NamedQuery(name = "Skills.findByName", query = "SELECT s FROM Skills s WHERE s.name = :name"),
    @NamedQuery(name = "Skills.findByDescription", query = "SELECT s FROM Skills s WHERE s.description = :description")})
public class Skills implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skills")
    private Collection<Requirements> requirementsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "skillsCollection")
    private Collection<Adverts> advertsCollection;
    @JoinColumn(name = "classification_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classification classificationId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skills")
    private Collection<UserSkills> userSkillsCollection;

    public Skills() {
    }

    public Skills(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Adverts> getAdvertsCollection() {
        return advertsCollection;
    }

    public void setAdvertsCollection(Collection<Adverts> advertsCollection) {
        this.advertsCollection = advertsCollection;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    @XmlTransient
    public Collection<UserSkills> getUserSkillsCollection() {
        return userSkillsCollection;
    }

    public void setUserSkillsCollection(Collection<UserSkills> userSkillsCollection) {
        this.userSkillsCollection = userSkillsCollection;
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
        if (!(object instanceof Skills)) {
            return false;
        }
        Skills other = (Skills) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Skills[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Requirements> getRequirementsCollection() {
        return requirementsCollection;
    }

    public void setRequirementsCollection(Collection<Requirements> requirementsCollection) {
        this.requirementsCollection = requirementsCollection;
    }
    
}
