
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
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return;
        }
        //confirm with user first

        showConfirmPage(request, response, "Do you want to delete your photo?", "photo?action=deleteAvatar", user.getId());
    }

    public void deleteAvatarPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return;
        }
        
        if (StringUtils.isBlank(user.getImg())) {
            alertWarning(request, "You don't have a photo to delete");
        }else{
            ServletUtils.deleteUserPhoto(this, user.getImg());
            user.setImg("");
            userFacade.edit(user);
            alertSuccess(request, "Your photo has been deleted.");
        }

        redirect(request, response, "/photo?action=upload");
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) {
        getView(request, response, "user/photo.jsp");
    }

    public void uploadPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return;
        }

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
        redirect(request, response, "/photo?action=upload");
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

        redirect(request, response, "/jobs?action=view&id=" + ad.getId());
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

        redirect(request, response, "/jobs?action=view&id=" + ad.getId());
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

    @Override
    protected void invokeMethod(HttpServletRequest req, HttpServletResponse resp, boolean doPost) {
        req.setAttribute("current_path", "User");
        
        //features in this servlet require users to login
        User user = getCurrentUserOrLogin(req, resp);
        if (user == null) {
            return;
        }

        super.invokeMethod(req, resp, doPost); //To change body of generated methods, choose Tools | Templates.
    }
}
