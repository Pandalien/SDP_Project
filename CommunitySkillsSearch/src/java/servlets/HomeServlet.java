package servlets;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeServlet extends AbstractServlet {
    
    public void help(HttpServletRequest request, HttpServletResponse response){
        getView(request, response, "help.jsp");
    }
    
    public void index(HttpServletRequest request, HttpServletResponse response){
        getView(request, response, "index.jsp");
    }
}
