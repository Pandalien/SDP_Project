/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import utils.StringUtils;

/**
 *
 * @author andyc
 */

public class ServerMessage implements java.io.Serializable {
    public String message;
    public MessageType level;
    public String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ServerMessage(String msg, MessageType type){
        this.message = msg;
        this.level = type;
    }
    
    public ServerMessage(){
        
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getLevel() {
        return level;
    }

    public void setLevel(MessageType level) {
        this.level = level;
    }
    
    public boolean hasLink(){
        return !StringUtils.isEmpty(link);
    }
}
