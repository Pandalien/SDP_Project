/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * This class is used verify the input from user
 */
package utils;

import entities.Adverts;
import entities.Responders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public boolean apply(int idAd, int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean cancel(int idAd, int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deselectResponder(int idAd, int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Responders getResponder(int idAd, int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isApplied(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean selectResponder(int idAd, int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
        if (content.length() >2999) {
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
