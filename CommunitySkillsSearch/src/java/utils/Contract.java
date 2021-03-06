/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Stores the constant string values used across the JSPs and Servlets
 */
package utils;

/**
 *
 * @author andyc
 */
public class Contract {
    final public static String CURRENT_USER = "session_current_user";
    final public static String OTHER_USER = "session_other_user";
    final public static String USERS = "session_users";
    
    final public static String ADVERTS = "session_adverts";
    final public static String CLASSIFICATIONS = "session_classifications";
    final public static String SUBURBS = "session_suburbs";
    final public static String SKILLS = "session_skills";
    
    final public static String USER_ADVERTS = "current_user_adverts";
    final public static String VIEW_ADVERT = "view_advert";
    final public static String ADVERT_SKILL_IDS = "advert_skill_ids";
    
    final public static String MESSAGES_FROM_SERVER = "messages_from_server";
    final public static String MESSAGES_RECEIVED = "messages_received";
    final public static String MESSAGES_SENT = "messages_sent";
    final public static String MESSAGES_SELECTED = "messages_selected";
    
    final public static String USER_AS_RESPONDER = "user_as_responder";
    final public static String ADVERT_RESPONDERS = "advert_responders";
    
    final public static String ADVERT_VIEW_RESPONDER = "advert_view_responder";
    
    //this status also being saved in database table: Responders
    public enum ResponderStatus{
        DEFAULT,
        SELECTED,
        ACCEPTED,
        DECLINED,
        JOB_DONE,
        FEEDBACK,
        FEEDBACK_WORKER
    }
}
