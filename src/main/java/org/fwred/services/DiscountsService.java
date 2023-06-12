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
import org.fwred.model.entities.DeliveryPointsVWEntity;
import org.fwred.model.entities.DiscountsEntity;

import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Path("discount")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class DiscountsService {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(UserService.class.getName());

    @Inject
    EntityManager em;
    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("add")
    @Transactional
    public Response addNewDiscount(String jsonString) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        try {
            String startDateString = String.valueOf(String.valueOf(jsonBody.get("startDate")));
            startDateString = startDateString.replace("\"", "");
            Date startDate = Date.valueOf(startDateString);

            String endDateString = String.valueOf(String.valueOf(jsonBody.get("endDate")));
            endDateString = endDateString.replace("\"", "");
            Date endDate = Date.valueOf(endDateString);

            Integer discountPercentageString = Integer.valueOf(String.valueOf(jsonBody.get("discountPercentage")));
            //discountPercentageString = discountPercentageString.replace("\"", "");
            BigDecimal discountPercentage = new BigDecimal(discountPercentageString);
            String orgIdString = String.valueOf(String.valueOf(jsonBody.get("orgId")));
            orgIdString = orgIdString.replace("\"", "");
            BigDecimal orgId = new BigDecimal(orgIdString);

            jsonReader.close();

            DiscountsEntity discountsEntity = new DiscountsEntity();
            discountsEntity.setDiscountPercentage(discountPercentage);
            discountsEntity.setStartDate(startDate);
            discountsEntity.setEndDate(endDate);
            discountsEntity.setOrgId(orgId);
            discountsEntity.setActive("Y");

            em.persist(discountsEntity);
            try {
                em.flush();
            } catch (PersistenceException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }

            return Response.ok().build();
        } catch (IllegalArgumentException nfe) {
            throw new RuntimeException(nfe.getMessage());
        } catch(PersistenceException pe){
            throw new RuntimeException(pe.getMessage());
        }
    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("show")
    public Response showDiscounts(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        String orgIdString = String.valueOf(String.valueOf(jsonBody.get("orgId")));
        orgIdString = orgIdString.replace("\"", "");
        BigDecimal orgId = new BigDecimal(orgIdString);

        jsonReader.close();

        Query queryDiscounts  =  em.createNamedQuery("DiscountsEntity.findByOrgId", DiscountsEntity.class);
        queryDiscounts.setParameter("orgId", orgId);
        List<DiscountsEntity> discountsEntities = queryDiscounts.getResultList();

        return  Response.ok(discountsEntities).build();
    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("edit")
    @Transactional
    public Response editDiscounts(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        try {
            String startDateString = String.valueOf(String.valueOf(jsonBody.get("startDate")));
            startDateString = startDateString.replace("\"", "");
            Date startDate = Date.valueOf(startDateString);

            String endDateString = String.valueOf(String.valueOf(jsonBody.get("endDate")));
            endDateString = endDateString.replace("\"", "");
            Date endDate = Date.valueOf(endDateString);

            Integer discountPercentageString = Integer.valueOf(String.valueOf(jsonBody.get("discountPercentage")));
            BigDecimal discountPercentage = new BigDecimal(discountPercentageString);
            String discountIdString = String.valueOf(String.valueOf(jsonBody.get("discountId")));
            discountIdString = discountIdString.replace("\"", "");
            BigDecimal discountId = new BigDecimal(discountIdString);

            jsonReader.close();

            Query queryDiscounts  =  em.createNamedQuery("DiscountsEntity.findByDiscountId", DiscountsEntity.class);
            queryDiscounts.setParameter("discountId", discountId);
            DiscountsEntity discountsEntities = (DiscountsEntity)queryDiscounts.getSingleResult();

            discountsEntities.setDiscountPercentage(discountPercentage);
            discountsEntities.setStartDate(startDate);
            discountsEntities.setEndDate(endDate);

            em.merge(discountsEntities);
            try {
                em.flush();
            } catch (PersistenceException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }

            return Response.ok().build();
        } catch (IllegalArgumentException nfe) {
            throw new RuntimeException(nfe.getMessage());
        } catch(PersistenceException pe){
            throw new RuntimeException(pe.getMessage());
        }
    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("delete")
    @Transactional
    public Response deleteDiscounts(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        try {
            String discountIdString = String.valueOf(String.valueOf(jsonBody.get("discountId")));
            discountIdString = discountIdString.replace("\"", "");
            BigDecimal discountId = new BigDecimal(discountIdString);

            jsonReader.close();

            Query queryDiscounts  =  em.createNamedQuery("DiscountsEntity.findByDiscountId", DiscountsEntity.class);
            queryDiscounts.setParameter("discountId", discountId);
            DiscountsEntity discountsEntities = (DiscountsEntity)queryDiscounts.getSingleResult();
            discountsEntities.setActive("N");

            em.merge(discountsEntities);
            try {
                em.flush();
            } catch (PersistenceException e) {
                LOGGER.error(e);
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