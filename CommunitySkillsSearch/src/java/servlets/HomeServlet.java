package servlets;

import entities.Adverts;
import entities.Requirements;
import entities.User;
import entities.UserSkills;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        request.setAttribute("current_path", "Help");
        getView(request, response, "help.jsp");
    }
    
    public void index(HttpServletRequest request, HttpServletResponse response){
        User user = getCurrentUser(request);
        if (user != null) {
            //get all usr skills
            List<Adverts> ads = new ArrayList<>();
            List<Integer> adIds = new ArrayList<>();
            int id;
            
            List<UserSkills> userSkills = userSkillsFacade.findByUserId(user.getId());
            if (userSkills != null) {
                for (UserSkills us : userSkills) {
                    //get all requirements form adverts
                    List<Requirements> reqs = requirementsFacade.findBySkillsId(us.getUserSkillsPK().getSkillsId());
                    if (reqs != null) {
                        for (Requirements r : reqs) {
                            //get advert for at most once only
                            id = r.getRequirementsPK().getAdvertsId();
                            if (adIds.contains(id)) {
                                continue;
                            }
                            adIds.add(id);

                            //get matched adverts
                            Adverts ad = advertsFacade.find(id);
                            if (ad == null || ad.getClosed()) {
                                continue;
                            }

                            //don't show own adverts
                            if (Objects.equals(ad.getUserId().getId(), user.getId())) {
                                continue;
                            }

                            //add to recomemded list
                            ads.add(ad);
                        }
                    }
                }
            }
            request.setAttribute(Contract.ADVERTS, ads);
        }
        request.setAttribute("current_path", "Welcome");
        getView(request, response, "index.jsp");
    }
}
