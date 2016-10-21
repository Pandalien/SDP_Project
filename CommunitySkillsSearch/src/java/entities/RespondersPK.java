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
public class RespondersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adverts_id")
    private int advertsId;

    public RespondersPK() {
    }

    public RespondersPK(int userId, int advertsId) {
        this.userId = userId;
        this.advertsId = advertsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdvertsId() {
        return advertsId;
    }

    public void setAdvertsId(int advertsId) {
        this.advertsId = advertsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) advertsId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespondersPK)) {
            return false;
        }
        RespondersPK other = (RespondersPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.advertsId != other.advertsId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RespondersPK[ userId=" + userId + ", advertsId=" + advertsId + " ]";
    }
    
}
