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
    public static boolean handlePhotoUpload(final HttpServlet servlet, HttpServletRequest request, int userId) {
        String photoFolder = getImageFolder(servlet);
        String photoPath = photoFolder + File.separator + userId + ".jpg";
        
        if(createFolder(photoFolder)){
            return createPhoto(request, photoPath);
        }
        
        return false;
    }
    
    public static String getImageFolder(final HttpServlet servlet){
        String absoluteFilePath = servlet.getServletContext().getRealPath("");
        absoluteFilePath += File.separator + "UserPhotos";
        
        return absoluteFilePath;
    }
    
    public static boolean createFolder(String path){
        // creates the save directory if it does not exists
        File fileSaveDir = new File(path);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        return fileSaveDir.exists();
    }
    
    public static boolean createPhoto(HttpServletRequest request, String photoPath) {
        try {
            if (request.getParts() == null) {
                return false;
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

                            //File uniqueFile = File.createTempFile("img", ".jpg", new File(absoluteFilePath));
                            File photoFile = new File(photoPath);
                            if (photoFile.exists()) {
                                //delete old photo
                                photoFile.delete();
                            }
                            
                            FileOutputStream os = new FileOutputStream(photoFile);

                            os.write(b);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            return false;
        }

        File f = new File(photoPath);
        return f.exists();
    }
    
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
