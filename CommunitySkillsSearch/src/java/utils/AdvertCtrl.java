/*
This class is used verify the input from user
 */
package utils;

import entities.Adverts;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.clients.AdvertsClient;

/**
 *
 * @author andyc
 */
public class AdvertCtrl {
    private AdvertsClient adClient;
    
    public AdvertCtrl(){
        adClient = new AdvertsClient();
    }
    
    public boolean verifyTitle(String title){
        if (StringUtils.isBlank(title)) {
            return false;
        }
        
        if (title.length() >45) {
            return false;
        }
        
        return true;
    }
    
    public boolean verifyContent(String content){
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        
        if (content.length() >500) {
            return false;
        }
        
        return true;
    }
    
    public boolean verifyExpiryDate(String dateStr){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            
            Date today = new Date();
            
            return date.after(today);
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public Adverts create(Adverts ad){
        return adClient.create_XML(ad, Adverts.class);
    }
    
    public Adverts findById(int id){
        return adClient.find_XML(Adverts.class, String.format("%d", id));
    }
    
    public void update(Adverts ad){
        adClient.edit_XML(ad, String.format("%d", ad.getId()));
    }
    
    public void delete(Adverts ad){
        adClient.remove(String.format("%d", ad.getId()));
    }
}
