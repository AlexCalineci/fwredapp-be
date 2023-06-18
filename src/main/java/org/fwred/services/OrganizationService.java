package org.fwred.services;


import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.fwred.model.entities.OrganizationsEntity;
import org.fwred.model.entities.OrganizationsVWEntity;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

@Path("organizations")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class OrganizationService {

    @Inject
    EntityManager em;


    @GET
    @Path("show")
    public Response getOrganizations() {
        Query  query = em.createNamedQuery("OrganizationsVWEntity.findAll");
        List<OrganizationsEntity> organizations = query.getResultList();
        return Response.ok(organizations).build();
    }
    @GET
    @Path("show/{orgType}")
    public Response getOrganizationByType(String orgType) {
        Query query;
        query = em.createNamedQuery("OrganizationsVWEntity.findByOrgType");

        if (orgType.equals("DONOR")) {
            query.setParameter("orgType", "DONOR");
        } else if (orgType.equals("RECEIVER")) {
            query.setParameter("orgType", "RECEIVER");
        }
        List<OrganizationsVWEntity> organizations = query.getResultList();
        return Response.ok(organizations).build();
    }

    @RolesAllowed({"ADMIN"})
    @POST
    @Path("activate")
    @Transactional
    public Response activateOrganization(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal orgId;
        try {
            Integer orgIdInt = jsonBody.getInt("orgId");
            if (!"null".equals(orgIdInt)) {
                orgId = new BigDecimal(orgIdInt);
            } else {
                orgId = null;
            }

            jsonReader.close();

            Query queryOrganization  =  em.createNamedQuery("OrganizationsEntity.findByOrgId", OrganizationsEntity.class);
            queryOrganization.setParameter("orgId", orgId);
            OrganizationsEntity organizationsEntity = (OrganizationsEntity)queryOrganization.getSingleResult();
            organizationsEntity.setActive("Y");

            em.merge(organizationsEntity);
            try {
                em.flush();
            } catch (PersistenceException e) {
                throw new RuntimeException(e);
            }

            return Response.ok().build();
        } catch (IllegalArgumentException nfe) {
            throw new RuntimeException(nfe.getMessage());
        } catch(PersistenceException pe){
            throw new RuntimeException(pe.getMessage());
        }
    }

    @RolesAllowed({"ADMIN"})
    @POST
    @Path("inactivate")
    @Transactional
    public Response inactivateOrganization(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal orgId;
        try {
            Integer orgIdInt = jsonBody.getInt("orgId");
            if (!"null".equals(orgIdInt)) {
                orgId = new BigDecimal(orgIdInt);
            } else {
                orgId = null;
            }

            jsonReader.close();

            Query queryOrganization  =  em.createNamedQuery("OrganizationsEntity.findByOrgId", OrganizationsEntity.class);
            queryOrganization.setParameter("orgId", orgId);
            OrganizationsEntity organizationsEntity = (OrganizationsEntity)queryOrganization.getSingleResult();
            organizationsEntity.setActive("N");

            em.merge(organizationsEntity);
            try {
                em.flush();
            } catch (PersistenceException e) {
                throw new RuntimeException(e);
            }

            return Response.ok().build();
        } catch (IllegalArgumentException nfe) {
            throw new RuntimeException(nfe.getMessage());
        } catch(PersistenceException pe){
            throw new RuntimeException(pe.getMessage());
        }
    }
}
