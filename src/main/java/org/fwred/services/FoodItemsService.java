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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.fwred.model.entities.DiscountsEntity;
import org.fwred.model.entities.FoodItemsEntity;
import org.fwred.model.entities.FoodItemsVWEntity;

import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Path("fooditems")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FoodItemsService {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(FoodItemsService.class.getName());

    @Inject
    EntityManager em;


    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("show")
    public Response showFoodItems(String jsonString)  {

        String namedQuery;
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        String orgType = String.valueOf(String.valueOf(jsonBody.get("userType")));
        orgType = orgType.replace("\"", "");

        String orgIdString = String.valueOf(String.valueOf(jsonBody.get("orgId")));
        orgIdString = orgIdString.replace("\"", "");
        BigDecimal orgId = new BigDecimal(orgIdString);

        System.out.println("UserType: "+orgType);
        System.out.println("OrgId: "+orgId);

        jsonReader.close();

        if (orgType.equals("DONOR")){
            namedQuery = "FoodItemsVWEntity.findByOrgId";
        }else{
            namedQuery = "FoodItemsVWEntity.findAll";
        }

        Query foodItemsVWEntityTypedQuery  =  em.createNamedQuery(namedQuery, FoodItemsVWEntity.class);
        if (!orgType.equals("RECEIVER")){
            foodItemsVWEntityTypedQuery.setParameter("orgId", orgId);
        }

        List<FoodItemsVWEntity> foodItemsVWEntities = foodItemsVWEntityTypedQuery.getResultList();

        return  Response.ok(foodItemsVWEntities).build();
    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("add")
    @Transactional
    public Response addNewFoodItem(String jsonString) {
        System.out.println(jsonString);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal orgId = new BigDecimal(0);
        BigDecimal deliveryPointId = new BigDecimal(0);
        BigDecimal discountId = new BigDecimal(0);
        System.out.println(jsonString);
        try {

            String expirationDateString = jsonBody.getString("expirationDate");
            Date expirationDate = Date.valueOf(expirationDateString);

            Integer orgIdInt = jsonBody.getInt("orgId");
            if (!"null".equals(orgIdInt)) {
                orgId = new BigDecimal(orgIdInt);
            } else {
                orgId = null;
            }

            Integer deliveryPointIdInt = jsonBody.getInt("deliveryPointId");
            if (!"null".equals(deliveryPointIdInt)) {
                deliveryPointId = new BigDecimal(deliveryPointIdInt);
            } else {
                deliveryPointId = null;
            }

            Integer listPriceInt = jsonBody.getInt("listPrice");
            BigDecimal listPrice = new BigDecimal(listPriceInt);

            Integer discountIdInt = jsonBody.getInt("discountId");
            if (!"null".equals(discountIdInt)) {
                discountId = new BigDecimal(discountIdInt);
            } else {
                discountId = null;
            }

            String name = jsonBody.getString("name");
            String description = jsonBody.getString("description");
            Integer availableQuantityInt = jsonBody.getInt("availableQuantity");
            BigDecimal availableQuantity = new BigDecimal(availableQuantityInt);

            String quantityType = jsonBody.getString("quantityType");



            jsonReader.close();

            FoodItemsEntity foodItemsEntity = new FoodItemsEntity();
            foodItemsEntity.setActive("Y");
            foodItemsEntity.setDescription(description);
            foodItemsEntity.setAvailableQuantity(availableQuantity);
            foodItemsEntity.setDiscountId(discountId);
            foodItemsEntity.setName(name);
            foodItemsEntity.setExpirationDate(expirationDate);
            foodItemsEntity.setOrgId(orgId);
            foodItemsEntity.setQuantityType(quantityType);
            foodItemsEntity.setDeliveryPointId(deliveryPointId);
            foodItemsEntity.setListPrice(listPrice);


            em.persist(foodItemsEntity);
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
    public Response editFoodItem(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal deliveryPointId;
        BigDecimal discountId;
        BigDecimal foodItemId;

        try {

            String expirationDateString = jsonBody.getString("expirationDate");
            Date expirationDate = Date.valueOf(expirationDateString);


            Integer deliveryPointIdInt = jsonBody.getInt("deliveryPointId");
            if (!"null".equals(deliveryPointIdInt)) {
                deliveryPointId = new BigDecimal(deliveryPointIdInt);
            } else {
                deliveryPointId = null;
            }

            Integer foodItemIdInt = jsonBody.getInt("foodItemId");
            if (!"null".equals(foodItemIdInt)) {
                foodItemId = new BigDecimal(foodItemIdInt);
            } else {
                foodItemId = null;
            }

            Integer listPriceInt = jsonBody.getInt("listPrice");
            BigDecimal listPrice = new BigDecimal(listPriceInt);

            Integer discountIdInt = jsonBody.getInt("discountId");
            if (!"null".equals(discountIdInt)) {
                discountId = new BigDecimal(discountIdInt);
            } else {
                discountId = null;
            }

            String name = jsonBody.getString("name");
            String description = jsonBody.getString("description");
            Integer availableQuantityInt = jsonBody.getInt("availableQuantity");
            BigDecimal availableQuantity = new BigDecimal(availableQuantityInt);

            String quantityType = jsonBody.getString("quantityType");

            jsonReader.close();


            Query queryFoodItems  =  em.createNamedQuery("FoodItemsEntity.findById", FoodItemsEntity.class);
            queryFoodItems.setParameter("foodItemId", foodItemId);
            FoodItemsEntity foodItemsEntity = (FoodItemsEntity)queryFoodItems.getSingleResult();


            foodItemsEntity.setDescription(description);
            foodItemsEntity.setAvailableQuantity(availableQuantity);
            foodItemsEntity.setDiscountId(discountId);
            foodItemsEntity.setName(name);
            foodItemsEntity.setExpirationDate(expirationDate);
            foodItemsEntity.setQuantityType(quantityType);
            foodItemsEntity.setDeliveryPointId(deliveryPointId);
            foodItemsEntity.setListPrice(listPrice);


            em.merge(foodItemsEntity);
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
    @Path("delete")
    @Transactional
    public Response inactivateFoodItem(String jsonString)  {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal foodItemId;
        try {
            Integer foodItemIdInt = jsonBody.getInt("foodItemId");
            if (!"null".equals(foodItemIdInt)) {
                foodItemId = new BigDecimal(foodItemIdInt);
            } else {
                foodItemId = null;
            }

            jsonReader.close();

            Query queryFoodItems  =  em.createNamedQuery("FoodItemsEntity.findById", FoodItemsEntity.class);
            queryFoodItems.setParameter("foodItemId", foodItemId);
            FoodItemsEntity foodItemsEntity = (FoodItemsEntity)queryFoodItems.getSingleResult();
            foodItemsEntity.setActive("N");

            em.merge(foodItemsEntity);
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
