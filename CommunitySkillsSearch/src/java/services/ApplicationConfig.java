/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author andyc
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(services.AdvertsFacadeREST.class);
        resources.add(services.ClassificationFacadeREST.class);
        resources.add(services.MessagesFacadeREST.class);
        resources.add(services.RequirementsFacadeREST.class);
        resources.add(services.RespondersFacadeREST.class);
        resources.add(services.SkillsFacadeREST.class);
        resources.add(services.SuburbFacadeREST.class);
        resources.add(services.UserFacadeREST.class);
        resources.add(services.UserSkillsFacadeREST.class);
    }
    
}
