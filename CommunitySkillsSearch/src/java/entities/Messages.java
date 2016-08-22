/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andyc
 */
@Entity
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.messagesPK.id = :id"),
    @NamedQuery(name = "Messages.findByContent", query = "SELECT m FROM Messages m WHERE m.content = :content"),
    @NamedQuery(name = "Messages.findByRead", query = "SELECT m FROM Messages m WHERE m.read = :read"),
    @NamedQuery(name = "Messages.findByTime", query = "SELECT m FROM Messages m WHERE m.time = :time"),
    @NamedQuery(name = "Messages.findBySenderId", query = "SELECT m FROM Messages m WHERE m.messagesPK.senderId = :senderId"),
    @NamedQuery(name = "Messages.findByReceiverId", query = "SELECT m FROM Messages m WHERE m.messagesPK.receiverId = :receiverId")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MessagesPK messagesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "content")
    private String content;
    @Column(name = "read")
    private Boolean read;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "sender_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Messages() {
    }

    public Messages(MessagesPK messagesPK) {
        this.messagesPK = messagesPK;
    }

    public Messages(MessagesPK messagesPK, String content, Date time) {
        this.messagesPK = messagesPK;
        this.content = content;
        this.time = time;
    }

    public Messages(int id, int senderId, int receiverId) {
        this.messagesPK = new MessagesPK(id, senderId, receiverId);
    }

    public MessagesPK getMessagesPK() {
        return messagesPK;
    }

    public void setMessagesPK(MessagesPK messagesPK) {
        this.messagesPK = messagesPK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messagesPK != null ? messagesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.messagesPK == null && other.messagesPK != null) || (this.messagesPK != null && !this.messagesPK.equals(other.messagesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Messages[ messagesPK=" + messagesPK + " ]";
    }
    
}
