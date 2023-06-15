package org.fwred.services;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.fwred.model.entities.OrganizationsEntity;
import org.fwred.model.entities.OrganizationsVWEntity;

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
        Query  query = em.createNamedQuery("OrganizationsEntity.findAll");
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
}
