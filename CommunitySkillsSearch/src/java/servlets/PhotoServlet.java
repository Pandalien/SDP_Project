
package servlets;

import beans.RequestData;
import entities.Adverts;
import entities.User;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ServletUtils;
import utils.StringUtils;

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
        if (StringUtils.isBlank(user.getImg())) {
            alertWarning(request, "You don't have a photo to delete");
        }else{
            ServletUtils.deleteUserPhoto(this, user.getImg());
            user.setImg("");
            userFacade.edit(user);
            alertSuccess(request, "Your photo has been deleted.");
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

        //original photo
        String oldPhoto = user.getImg();
        
        //handle uploading user photo
        String imgName = ServletUtils.handlePhotoUpload(this, request);
        if (!StringUtils.isEmpty(imgName)) {
            //update photo
            user.setImg(imgName);
            userFacade.edit(user);
            
            //delete old one
            ServletUtils.deleteUserPhoto(this, oldPhoto);
            
            alertSuccess(request, "Photo updated sucessfully.");
        } else {
            alertDanger(request, "Not supported file format.");
        }

        upload(request, response);
    }

    public void deleteAdPhoto(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        //confirm with user first

        showConfirmPage(request, response, "Do you want to delete your photo?", "photo?action=deleteAdPhoto", data.id);
    }
    
    public void deleteAdPhotoPost(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        Adverts ad = advertsFacade.find(data.id);
        if (ad == null) {
            alertWarning(request, "The advert was not found.");
            showGoBackPage(request, response);
            return;
        }
        
        if (StringUtils.isBlank(ad.getImg())) {
            alertWarning(request, "You don't have a photo to delete");
        } else {
            ServletUtils.deleteUserPhoto(this, ad.getImg());
            ad.setImg("");
            advertsFacade.edit(ad);
            alertSuccess(request, "Your photo has been deleted.");
        }

        try {
            //return to view page
            response.sendRedirect(request.getContextPath() + "/jobs?action=view&id=" + ad.getId());
        } catch (Exception ex) {
            alertDanger(request, "Page not found");
            showGoBackPage(request, response);
        }
    }
    
    public void uploadAdPhotoPost(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        Adverts ad = advertsFacade.find(data.id);
        if (ad == null || !Objects.equals(ad.getUserId().getId(), data.user.getId())) {
            alertWarning(request, "The advert was not found.");
            showGoBackPage(request, response);
            return;
        }
        
        //original photo
        String oldPhoto = ad.getImg();

        //handle uploading user photo
        String imgName = ServletUtils.handlePhotoUpload(this, request);
        if (!StringUtils.isEmpty(imgName)) {
            //update photo
            ad.setImg(imgName);
            advertsFacade.edit(ad);

            //delete old one
            ServletUtils.deleteUserPhoto(this, oldPhoto);

            alertSuccess(request, "Photo updated sucessfully.");
        } else {
            alertDanger(request, "Not supported file format.");
        }

        try {
            //return to view page
            response.sendRedirect(request.getContextPath() + "/jobs?action=view&id=" + ad.getId());
        } catch (Exception ex) {
            alertDanger(request, "Page not found");
            showGoBackPage(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action;
        
        try {
            action = request.getParameter("action");
        } catch (Exception e) {
            // File or request is too big!
            alertDanger(request, "File is too big (maximum 2 MB).");
            showGoBackPage(request, response);
            
            return;
        }
        
        super.doPost(request, response);
    }  
}