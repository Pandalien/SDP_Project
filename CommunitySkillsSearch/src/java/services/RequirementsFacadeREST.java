/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Web services for Requirements table
 */
package services;

import entities.Requirements;
import entities.RequirementsPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author andyc
 */
@Stateless
@Path("entities.requirements")
public class RequirementsFacadeREST extends AbstractFacade<Requirements> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    private RequirementsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;advertsId=advertsIdValue;skillsId=skillsIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.RequirementsPK key = new entities.RequirementsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> advertsId = map.get("advertsId");
        if (advertsId != null && !advertsId.isEmpty()) {
            key.setAdvertsId(new java.lang.Integer(advertsId.get(0)));
        }
        java.util.List<String> skillsId = map.get("skillsId");
        if (skillsId != null && !skillsId.isEmpty()) {
            key.setSkillsId(new java.lang.Integer(skillsId.get(0)));
        }
        return key;
    }

    public RequirementsFacadeREST() {
        super(Requirements.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Requirements create(Requirements entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Requirements entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entities.RequirementsPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Requirements find(@PathParam("id") PathSegment id) {
        entities.RequirementsPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Requirements> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Requirements> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
