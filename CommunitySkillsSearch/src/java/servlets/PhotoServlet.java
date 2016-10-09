
package servlets;

import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ServletUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB 
        maxFileSize = 1024 * 1024 * 2, // 2 MB
        maxRequestSize = 1024 * 1024 * 2, // 2 MB
        location = "/")
public class PhotoServlet extends AbstractServlet {
    
    public void deleteAvatar(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        //confirm with user first

        showConfirmPage(request, response, "Do you want to delete your photo?", "photo?action=deleteAvatar", user.getId());
    }

    public void deleteAvatarPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        if (ServletUtils.deletePhoto(this, user.getId())) {
            alertSuccess(request, "Your photo has been deleted.");
        } else {
            alertWarning(request, "The photo was not found.");
        }

        try {
            //return to edit page
            getView(request, response, "user/photo.jsp");
        } catch (Exception ex) {
            alertDanger(request, "Page not found");
            showGoBackPage(request, response);
        }
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) {
        getView(request, response, "user/photo.jsp");
    }

    public void uploadPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);

        //handle uploading user photo
        if (ServletUtils.handlePhotoUpload(this, request, user.getId())) {
            alertSuccess(request, "Photo updated sucessfully.");
        } else {
            alertDanger(request, "Not supported file format.");
        }

        upload(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action;
        try {
            action = request.getParameter("action");
        } catch (Exception e) {
            // File or request is too big!

            alertDanger(request, "File is too big.");
            getView(request, response, "user/photo.jsp");
            return;
        }
        
        super.doPost(request, response);
    }  
}
