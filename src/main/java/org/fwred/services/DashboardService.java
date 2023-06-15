package org.fwred.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.fwred.model.entities.DashboardVWEntity;
import org.fwred.model.entities.FoodItemsVWEntity;

import java.util.List;

@Path("dashboard")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class DashboardService {

    @Inject
    EntityManager em;

    @GET
    @Path("stats")
    public Response showDashboardStats()  {
        Query query = em.createNamedQuery("DashboardVWEntity.findAll", DashboardVWEntity.class);
        DashboardVWEntity dashboardVWEntities = (DashboardVWEntity)query.getSingleResult();
        return Response.ok(dashboardVWEntities).build();
    }


    @GET
    @Path("fooditems")
    public Response showAllFoodItems()  {
        Query foodItemsVWEntityTypedQuery  =  em.createNamedQuery("FoodItemsVWEntity.findAll", FoodItemsVWEntity.class);
        List<FoodItemsVWEntity> foodItemsVWEntities = foodItemsVWEntityTypedQuery.getResultList();
        return  Response.ok(foodItemsVWEntities).build();
    }

}
