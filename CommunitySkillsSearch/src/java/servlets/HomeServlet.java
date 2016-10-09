package servlets;

import entities.Adverts;
import entities.Requirements;
import entities.User;
import entities.UserSkills;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.AdvertsFacade;
import sb.RequirementsFacade;
import sb.UserSkillsFacade;
import utils.Contract;


public class HomeServlet extends AbstractServlet {

    @EJB
    private AdvertsFacade advertsFacade;

    @EJB
    private RequirementsFacade requirementsFacade;

    @EJB
    private UserSkillsFacade userSkillsFacade;
    
    public void help(HttpServletRequest request, HttpServletResponse response){
        getView(request, response, "help.jsp");
    }
    
    public void index(HttpServletRequest request, HttpServletResponse response){
        User user = getCurrentUser(request);
        if (user != null) {
            List<Adverts> ads = new ArrayList<>();
            //get all usr skills
            List<UserSkills> userSkills = userSkillsFacade.findByUserId(user.getId());
            
            for (UserSkills us : userSkills) {
                //get all requirements form adverts
                List<Requirements> reqs = requirementsFacade.findBySkillsId(us.getUserSkillsPK().getSkillsId());
                for(Requirements r : reqs){
                    //get all matched adverts
                    Adverts ad = advertsFacade.find(r.getRequirementsPK().getAdvertsId());
                    if (ad.getClosed()) {
                        continue;
                    }
                    ads.add(ad);
                }
            }
            request.setAttribute(Contract.ADVERTS, ads);
        }
        getView(request, response, "index.jsp");
    }
}
