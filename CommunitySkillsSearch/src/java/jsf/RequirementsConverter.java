/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Requirements;
import entities.RequirementsPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sb.RequirementsFacade;

/**
 *
 * @author andyc
 */
public class RequirementsConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        RequirementsPK id = getId(string);
        RequirementsController controller = (RequirementsController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "requirements");
        return controller.getJpaController().find(id);
    }

    RequirementsPK getId(String string) {
        RequirementsPK id = new RequirementsPK();
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
        id.setAdvertsId(Integer.parseInt(params[0]));
        id.setSkillsId(Integer.parseInt(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Requirements) {
            Requirements o = (Requirements) object;
            RequirementsPK id = o.getRequirementsPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String advertsId = String.valueOf(id.getAdvertsId());
            advertsId = advertsId.replace(escape, escape + escape);
            advertsId = advertsId.replace(delim, escape + delim);
            String skillsId = String.valueOf(id.getSkillsId());
            skillsId = skillsId.replace(escape, escape + escape);
            skillsId = skillsId.replace(delim, escape + delim);
            return advertsId + delim + skillsId;
            // TODO: no setter methods were found in your primary key class
            //    entities.RequirementsPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: entities.Requirements");
        }
    }
    
}
