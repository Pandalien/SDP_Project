/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * This is Entity Class for database
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author andyc
 */
@Embeddable
public class RequirementsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "adverts_id")
    private int advertsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skills_id")
    private int skillsId;

    public RequirementsPK() {
    }

    public RequirementsPK(int advertsId, int skillsId) {
        this.advertsId = advertsId;
        this.skillsId = skillsId;
    }

    public int getAdvertsId() {
        return advertsId;
    }

    public void setAdvertsId(int advertsId) {
        this.advertsId = advertsId;
    }

    public int getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(int skillsId) {
        this.skillsId = skillsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) advertsId;
        hash += (int) skillsId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequirementsPK)) {
            return false;
        }
        RequirementsPK other = (RequirementsPK) object;
        if (this.advertsId != other.advertsId) {
            return false;
        }
        if (this.skillsId != other.skillsId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RequirementsPK[ advertsId=" + advertsId + ", skillsId=" + skillsId + " ]";
    }
    
}
