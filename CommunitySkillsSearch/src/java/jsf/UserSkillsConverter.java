/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.UserSkills;
import entities.UserSkillsPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sb.UserSkillsFacade;

/**
 *
 * @author andyc
 */
public class UserSkillsConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        UserSkillsPK id = getId(string);
        UserSkillsController controller = (UserSkillsController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "userSkills");
        return controller.getJpaController().find(id);
    }

    UserSkillsPK getId(String string) {
        UserSkillsPK id = new UserSkillsPK();
        String[] params = new String[2];
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
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 2 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setUserId(Integer.parseInt(params[0]));
        id.setSkillsId(Integer.parseInt(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof UserSkills) {
            UserSkills o = (UserSkills) object;
            UserSkillsPK id = o.getUserSkillsPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String userId = String.valueOf(id.getUserId());
            userId = userId.replace(escape, escape + escape);
            userId = userId.replace(delim, escape + delim);
            String skillsId = String.valueOf(id.getSkillsId());
            skillsId = skillsId.replace(escape, escape + escape);
            skillsId = skillsId.replace(delim, escape + delim);
            return userId + delim + skillsId;
            // TODO: no setter methods were found in your primary key class
            //    entities.UserSkillsPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entities.UserSkills");
        }
    }
    
}
