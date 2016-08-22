/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class MessagesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sender_id")
    private int senderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receiver_id")
    private int receiverId;

    public MessagesPK() {
    }

    public MessagesPK(int id, int senderId, int receiverId) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) senderId;
        hash += (int) receiverId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessagesPK)) {
            return false;
        }
        MessagesPK other = (MessagesPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.senderId != other.senderId) {
            return false;
        }
        if (this.receiverId != other.receiverId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MessagesPK[ id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + " ]";
    }
    
}
