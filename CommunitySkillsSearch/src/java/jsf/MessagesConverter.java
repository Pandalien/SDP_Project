/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Messages;
import entities.MessagesPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sb.MessagesFacade;

/**
 *
 * @author andyc
 */
public class MessagesConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        MessagesPK id = getId(string);
        MessagesController controller = (MessagesController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "messages");
        return controller.getJpaController().find(id);
    }

    MessagesPK getId(String string) {
        MessagesPK id = new MessagesPK();
        String[] params = new String[3];
        int p = 0;
        int grabStart = 0;
        String delim = "#";
        String escape = "~";
        Pattern pattern = Pattern.compile(escape + "*" + delim);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String found = matcher.group();
            if (found.length() % 2 == 1) {
                params[p] = string.substring(grabStart, matcher.start());
                p++;
                grabStart = matcher.end();
            }
        }
        if (p != params.length - 1) {
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 3 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setId(Integer.parseInt(params[0]));
        id.setSenderId(Integer.parseInt(params[1]));
        id.setReceiverId(Integer.parseInt(params[2]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Messages) {
            Messages o = (Messages) object;
            MessagesPK id = o.getMessagesPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String id2 = String.valueOf(id.getId());
            id2 = id2.replace(escape, escape + escape);
            id2 = id2.replace(delim, escape + delim);
            String senderId = String.valueOf(id.getSenderId());
            senderId = senderId.replace(escape, escape + escape);
            senderId = senderId.replace(delim, escape + delim);
            String receiverId = String.valueOf(id.getReceiverId());
            receiverId = receiverId.replace(escape, escape + escape);
            receiverId = receiverId.replace(delim, escape + delim);
            return id2 + delim + senderId + delim + receiverId;
            // TODO: no setter methods were found in your primary key class
            //    entities.MessagesPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entities.Messages");
        }
    }
    
}
