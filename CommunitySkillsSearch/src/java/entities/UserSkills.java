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
@Table(name = "user_skills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSkills.findAll", query = "SELECT u FROM UserSkills u"),
    @NamedQuery(name = "UserSkills.findByUserId", query = "SELECT u FROM UserSkills u WHERE u.userSkillsPK.userId = :userId"),
    @NamedQuery(name = "UserSkills.findBySkillsId", query = "SELECT u FROM UserSkills u WHERE u.userSkillsPK.skillsId = :skillsId"),
    @NamedQuery(name = "UserSkills.findByLevel", query = "SELECT u FROM UserSkills u WHERE u.level = :level")})
public class UserSkills implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserSkillsPK userSkillsPK;
    @Column(name = "level")
    private Integer level;
    @JoinColumn(name = "skills_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Skills skills;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserSkills() {
    }

    public UserSkills(UserSkillsPK userSkillsPK) {
        this.userSkillsPK = userSkillsPK;
    }

    public UserSkills(int userId, int skillsId) {
        this.userSkillsPK = new UserSkillsPK(userId, skillsId);
    }

    public UserSkillsPK getUserSkillsPK() {
        return userSkillsPK;
    }

    public void setUserSkillsPK(UserSkillsPK userSkillsPK) {
        this.userSkillsPK = userSkillsPK;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userSkillsPK != null ? userSkillsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSkills)) {
            return false;
        }
        UserSkills other = (UserSkills) object;
        if ((this.userSkillsPK == null && other.userSkillsPK != null) || (this.userSkillsPK != null && !this.userSkillsPK.equals(other.userSkillsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserSkills[ userSkillsPK=" + userSkillsPK + " ]";
    }
    
}
