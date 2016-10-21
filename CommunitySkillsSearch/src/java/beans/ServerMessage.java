/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Java bean for messages, used in JSP pages
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
