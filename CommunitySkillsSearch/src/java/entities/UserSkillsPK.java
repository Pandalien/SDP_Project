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
public class UserSkillsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skills_id")
    private int skillsId;

    public UserSkillsPK() {
    }

    public UserSkillsPK(int userId, int skillsId) {
        this.userId = userId;
        this.skillsId = skillsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        hash += (int) userId;
        hash += (int) skillsId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSkillsPK)) {
            return false;
        }
        UserSkillsPK other = (UserSkillsPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.skillsId != other.skillsId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserSkillsPK[ userId=" + userId + ", skillsId=" + skillsId + " ]";
    }
    
}
