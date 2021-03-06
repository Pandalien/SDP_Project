/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * This is Entity Class for database
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andyc
 */
@Entity
@Table(name = "responders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responders.findAll", query = "SELECT r FROM Responders r"),
    @NamedQuery(name = "Responders.findByUserId", query = "SELECT r FROM Responders r WHERE r.respondersPK.userId = :userId"),
    @NamedQuery(name = "Responders.findByAdvertsId", query = "SELECT r FROM Responders r WHERE r.respondersPK.advertsId = :advertsId"),
    @NamedQuery(name = "Responders.findByMessage", query = "SELECT r FROM Responders r WHERE r.message = :message"),
    @NamedQuery(name = "Responders.findByTime", query = "SELECT r FROM Responders r WHERE r.time = :time"),
    @NamedQuery(name = "Responders.findByFeedback", query = "SELECT r FROM Responders r WHERE r.feedback = :feedback"),
    @NamedQuery(name = "Responders.findByRating", query = "SELECT r FROM Responders r WHERE r.rating = :rating"),
    @NamedQuery(name = "Responders.findByStatus", query = "SELECT r FROM Responders r WHERE r.status = :status"),
    @NamedQuery(name = "Responders.findByUserAndAdvertId", query = "SELECT r FROM Responders r WHERE r.respondersPK.userId = :userId AND r.respondersPK.advertsId = :advertsId"),
})

public class Responders implements Serializable {

    @Size(max = 200)
    @Column(name = "feedback_worker")
    private String feedbackWorker;
    @Column(name = "rating_worker")
    private Integer ratingWorker;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespondersPK respondersPK;
    @Size(max = 200)
    @Column(name = "message")
    private String message;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Size(max = 200)
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "adverts_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Adverts adverts;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Responders() {
    }

    public Responders(RespondersPK respondersPK) {
        this.respondersPK = respondersPK;
    }

    public Responders(int userId, int advertsId) {
        this.respondersPK = new RespondersPK(userId, advertsId);
    }

    public RespondersPK getRespondersPK() {
        return respondersPK;
    }

    public void setRespondersPK(RespondersPK respondersPK) {
        this.respondersPK = respondersPK;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Adverts getAdverts() {
        return adverts;
    }

    public void setAdverts(Adverts adverts) {
        this.adverts = adverts;
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
        hash += (respondersPK != null ? respondersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responders)) {
            return false;
        }
        Responders other = (Responders) object;
        if ((this.respondersPK == null && other.respondersPK != null) || (this.respondersPK != null && !this.respondersPK.equals(other.respondersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Responders[ respondersPK=" + respondersPK + " ]";
    }

    public String getFeedbackWorker() {
        return feedbackWorker;
    }

    public void setFeedbackWorker(String feedbackWorker) {
        this.feedbackWorker = feedbackWorker;
    }

    public Integer getRatingWorker() {
        return ratingWorker;
    }

    public void setRatingWorker(Integer ratingWorker) {
        this.ratingWorker = ratingWorker;
    }
    
}
