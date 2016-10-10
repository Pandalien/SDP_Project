/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author andyc
 */

public class ServletUtils {
    //create a user avatar image path for html use, default avatar is returned is not found
    public static String getUserAvatar(final HttpServlet servlet, HttpServletRequest request, final String imgName){
        if (!StringUtils.isEmpty(imgName)) {
            String photoFolder = getPhotoFolder(servlet);
            String photoPath = photoFolder + File.separator + imgName;

            File photoFile = new File(photoPath);
            if (photoFile.exists()) {
                photoFile = null;
                return request.getContextPath() + "/UserPhotos/" + imgName;
            }
        }
        
        return request.getContextPath() + "/res/images/default_avatar.svg";
    }
    
    //create a advert photo for html, empty is returned is not found
    public static String getUserPhoto(final HttpServlet servlet, HttpServletRequest request, final String imgName){
        if (StringUtils.isEmpty(imgName)) {
            return null;
        }
        
        String photoFolder = getPhotoFolder(servlet);
        String photoPath = photoFolder + File.separator + imgName;
        
        File photoFile = new File(photoPath);
        if (photoFile.exists()) {
            return request.getContextPath() + "/UserPhotos/" + imgName;
        }else{
            return "";
        }
    }
    
    //delete an image: both avavtar and photo for adverts
    public static boolean deleteUserPhoto(final HttpServlet servlet,final String imgName){
        String photoFolder = getPhotoFolder(servlet);
        String photoPath = photoFolder + File.separator + imgName;
        
        File photoFile = new File(photoPath);
        if (photoFile.exists()) {
            //delete old photo
            return photoFile.delete();
        }
        return true;
    }
    
    //call this method in servlet to handle photo upload
    public static String handlePhotoUpload(final HttpServlet servlet, HttpServletRequest request) {
        String photoFolder = getPhotoFolder(servlet);
        
        if(createFolder(photoFolder)){
            return createPhoto(request, photoFolder);
        }
        
        return null;
    }
    
    //get the default image folder for storage
    public static String getPhotoFolder(final HttpServlet servlet){
        String absoluteFilePath = servlet.getServletContext().getRealPath("");
        absoluteFilePath += File.separator + "UserPhotos";
        
        return absoluteFilePath;
    }
    
    //create a folder
    public static boolean createFolder(String path){
        // creates the save directory if it does not exists
        File fileSaveDir = new File(path);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        return fileSaveDir.exists();
    }
    
    //save images to UserPhoto folder and return the a name for the image created 
    public static String createPhoto(HttpServletRequest request, String folder) {
        File uniqueFile = null;
        
        try {
            if (request.getParts() == null) {
                return null;
            }
            
            for (Part part : request.getParts()) {
                if (part.getName().equalsIgnoreCase("file")) {
                    //String fileName = getFileName(part);
                    //part.write(absoluteFilePath + File.separator + fileName);
                    try (InputStream is = request.getPart(part.getName()).getInputStream()) {
                        int i = is.available();
                        if (i > 0) {
                            byte[] b = new byte[i];
                            is.read(b);

                            uniqueFile = File.createTempFile("img", ".jpg", new File(folder));
                            FileOutputStream os = new FileOutputStream(uniqueFile);

                            os.write(b);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            return null;
        }

        if (uniqueFile != null) {
            File f = new File(uniqueFile.getAbsolutePath());
            if (f.exists()) {
                //return file name only
                return uniqueFile.getName();
            }
        }
        
        return null;
    }
    
    //get the name of the file being uploading
    public static String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
