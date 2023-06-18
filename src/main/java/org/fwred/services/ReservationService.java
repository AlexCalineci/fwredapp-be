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
import org.fwred.model.entities.*;

import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Path("reservation")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ReservationService {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ReservationService.class.getName());

    @Inject
    EntityManager em;

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("show")
    public Response showReservations(String jsonString)  {

        String namedQuery;

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        String orgType = String.valueOf(String.valueOf(jsonBody.get("orgType")));
        orgType = orgType.replace("\"", "");


        String orgIdString = String.valueOf(String.valueOf(jsonBody.get("orgId")));
        orgIdString = orgIdString.replace("\"", "");
        BigDecimal orgId = new BigDecimal(orgIdString);

        jsonReader.close();

        if (orgType.equals("DONOR")){
            namedQuery = "ReservationsVWEntity.findByDonor";
        }else{
            namedQuery = "ReservationsVWEntity.findByReceiver";
        }
        Query reservationsQuery  =  em.createNamedQuery(namedQuery, ReservationsVWEntity.class);
        reservationsQuery.setParameter("orgId", orgId);
        List<ReservationsVWEntity> reservationsVWEntities = reservationsQuery.getResultList();

        return  Response.ok(reservationsVWEntities).build();
    }


    @GET
    @Path("show/{donor}/{receiver}")
    public Response showReservations(BigDecimal donor,BigDecimal receiver)  {

        Query reservationsQuery  =  em.createNamedQuery("ReservationsVWEntity.findByDonorandReceiver", ReservationsVWEntity.class);
        reservationsQuery.setParameter("receiverOrgId", receiver);
        reservationsQuery.setParameter("donorOrgId", donor);
        List<ReservationsVWEntity> reservationsVWEntities = reservationsQuery.getResultList();
        return  Response.ok(reservationsVWEntities).build();
    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("add")
    @Transactional
    public Response addReservation(String jsonString) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();


        Date reservationDate = Date.valueOf(LocalDate.now());
        Date dueDate = Date.valueOf(LocalDate.now().minusDays(-1));
        BigDecimal donorOrgId = new BigDecimal(0);
        BigDecimal receiverOrgId = new BigDecimal(0);
        BigDecimal foodItemId = new BigDecimal(0);
        BigDecimal quantity = new BigDecimal(0);
        BigDecimal listPrice = new BigDecimal(0);
        BigDecimal discountPercentage = new BigDecimal(0);
        BigDecimal totalCostPrice = new BigDecimal(0);
        try {

            Integer donorOrgIdInt = jsonBody.getInt("donorOrgId");
            if (!"null".equals(donorOrgIdInt)) {
                donorOrgId = new BigDecimal(donorOrgIdInt);
            } else {
                donorOrgId = null;
            }

            Integer receiverOrgIdInt = jsonBody.getInt("receiverOrgId");
            if (!"null".equals(donorOrgIdInt)) {
                receiverOrgId = new BigDecimal(receiverOrgIdInt);
            } else {
                receiverOrgId = null;
            }

            Integer foodItemIdInt = jsonBody.getInt("foodItemId");
            if (!"null".equals(foodItemIdInt)) {
                foodItemId = new BigDecimal(foodItemIdInt);
            } else {
                foodItemId = null;
            }

            Integer quantityInt = jsonBody.getInt("quantity");
            if (!"null".equals(quantityInt)) {
                quantity = new BigDecimal(quantityInt);
            } else {
                quantity = null;
            }


            Integer listPriceInt = jsonBody.getInt("listPrice");
            listPrice = new BigDecimal(listPriceInt);

            Integer discountPercentageInt = jsonBody.getInt("discountPercentage");
            discountPercentage = new BigDecimal(discountPercentageInt);

            jsonReader.close();

            totalCostPrice = quantity.multiply(listPrice.subtract(listPrice.multiply(discountPercentage.divide(BigDecimal.valueOf(100)))));

            ReservationsEntity reservationsEntity = new ReservationsEntity();

            reservationsEntity.setDonorOrgId(donorOrgId);
            reservationsEntity.setFoodItemId(foodItemId);
            reservationsEntity.setQuantity(quantity);
            reservationsEntity.setReceiverOrgId(receiverOrgId);
            reservationsEntity.setStatus("PENDING");
            reservationsEntity.setTotalCostPrice(totalCostPrice);
            reservationsEntity.setDueDate(dueDate);
            reservationsEntity.setFinalyzed("N");
            reservationsEntity.setReservationDate(reservationDate);

            em.persist(reservationsEntity);
            try {
                em.flush();
            } catch (PersistenceException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }

            return Response.ok().build();
        } catch (IllegalArgumentException | PersistenceException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }

    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("edit")
    @Transactional
    public Response editReservation(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        try {

            Integer reservationIdString = Integer.valueOf(String.valueOf(jsonBody.get("reservationId")));
            BigDecimal reservationId = new BigDecimal(reservationIdString);

            Integer quantityString = Integer.valueOf(String.valueOf(jsonBody.get("quantity")));
            BigDecimal quantity = new BigDecimal(quantityString);

            Integer foodItemIdString = Integer.valueOf(String.valueOf(jsonBody.get("foodItemId")));
            BigDecimal foodItemId = new BigDecimal(foodItemIdString);

            jsonReader.close();

            Query queryReservation  =  em.createNamedQuery("ReservationsEntity.findById", ReservationsEntity.class);
            queryReservation.setParameter("reservationId", reservationId);
            ReservationsEntity reservationEntity = (ReservationsEntity)queryReservation.getSingleResult();

            Query queryFoodItem = em.createNamedQuery("FoodItemsVWEntity.findbyItemId", FoodItemsVWEntity.class);
            queryFoodItem.setParameter("foodItemId",foodItemId);
            FoodItemsVWEntity foodItemsEntity = (FoodItemsVWEntity) queryFoodItem.getSingleResult();

            BigDecimal totalCostprice = quantity.multiply(foodItemsEntity.getListPrice().subtract(foodItemsEntity.getListPrice().multiply(foodItemsEntity.getDiscountPercentage().divide(BigDecimal.valueOf(100)))));
            reservationEntity.setQuantity(quantity);
            reservationEntity.setTotalCostPrice(totalCostprice);

            em.merge(reservationEntity);
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
    @Path("cancel")
    @Transactional
    public Response cancelReservation(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        try {

            Integer reservationIdString = Integer.valueOf(String.valueOf(jsonBody.get("reservationId")));
            BigDecimal reservationId = new BigDecimal(reservationIdString);

            jsonReader.close();

            Query queryReservation  =  em.createNamedQuery("ReservationsEntity.findById", ReservationsEntity.class);
            queryReservation.setParameter("reservationId", reservationId);
            ReservationsEntity reservationEntity = (ReservationsEntity)queryReservation.getSingleResult();


            reservationEntity.setStatus("CANCELLED");
            reservationEntity.setFinalyzed("N");

            em.merge(reservationEntity);
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
    @Path("finalise")
    @Transactional
    public Response finaliseReservation(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        try {

            Integer reservationIdString = Integer.valueOf(String.valueOf(jsonBody.get("reservationId")));
            BigDecimal reservationId = new BigDecimal(reservationIdString);

            jsonReader.close();

            Query queryReservation  =  em.createNamedQuery("ReservationsEntity.findById", ReservationsEntity.class);
            queryReservation.setParameter("reservationId", reservationId);
            ReservationsEntity reservationEntity = (ReservationsEntity)queryReservation.getSingleResult();


            reservationEntity.setStatus("FINALISED");
            reservationEntity.setFinalyzed("Y");

            em.merge(reservationEntity);
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
