/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * This is Entity Class for database
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
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
 * @author andyc
 */
@Entity
@Table(name = "requirements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requirements.findAll", query = "SELECT r FROM Requirements r"),
    @NamedQuery(name = "Requirements.findByAdvertsId", query = "SELECT r FROM Requirements r WHERE r.requirementsPK.advertsId = :advertsId"),
    @NamedQuery(name = "Requirements.findBySkillsId", query = "SELECT r FROM Requirements r WHERE r.requirementsPK.skillsId = :skillsId"),
    @NamedQuery(name = "Requirements.findByLevel", query = "SELECT r FROM Requirements r WHERE r.level = :level")})
public class Requirements implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RequirementsPK requirementsPK;
    @Column(name = "level")
    private Integer level;
    @JoinColumn(name = "adverts_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Adverts adverts;
    @JoinColumn(name = "skills_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Skills skills;

    public Requirements() {
    }

    public Requirements(RequirementsPK requirementsPK) {
        this.requirementsPK = requirementsPK;
    }

    public Requirements(int advertsId, int skillsId) {
        this.requirementsPK = new RequirementsPK(advertsId, skillsId);
    }

    public RequirementsPK getRequirementsPK() {
        return requirementsPK;
    }

    public void setRequirementsPK(RequirementsPK requirementsPK) {
        this.requirementsPK = requirementsPK;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Adverts getAdverts() {
        return adverts;
    }

    public void setAdverts(Adverts adverts) {
        this.adverts = adverts;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requirementsPK != null ? requirementsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requirements)) {
            return false;
        }
        Requirements other = (Requirements) object;
        if ((this.requirementsPK == null && other.requirementsPK != null) || (this.requirementsPK != null && !this.requirementsPK.equals(other.requirementsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Requirements[ requirementsPK=" + requirementsPK + " ]";
    }
    
}
