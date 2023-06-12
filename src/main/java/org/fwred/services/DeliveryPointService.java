package org.fwred.services;


import io.quarkus.arc.log.LoggerName;
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
import org.jboss.logging.Logger;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

@Path("deliverypoints")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class DeliveryPointService {

    @Inject
    Logger LOGGER;

    @Inject
    EntityManager em;
    @GET
    @Path("show")
    public Response showDeliveryPoints()  {
        Query query  =  em.createNamedQuery("DeliveryPointsVWEntity.findAll", DeliveryPointsVWEntity.class);
        List<DeliveryPointsVWEntity> addressEntityList = query.getResultList();
        System.out.println(addressEntityList);
        return  Response.ok(addressEntityList).build();
    }

    @GET
    @Path("show/{orgId}")
    public Response showDeliveryPointsByOrgId(Integer orgId)  {
        System.out.println("OrgId"+orgId);
        Query query  =  em.createNamedQuery("DeliveryPointsVWEntity.findByOrgId", DeliveryPointsVWEntity.class);
        query.setParameter("orgId", orgId);
        List<DeliveryPointsVWEntity> deliveryPointsVWEntities = query.getResultList();
        System.out.println(deliveryPointsVWEntities);
        return  Response.ok(deliveryPointsVWEntities).build();
    }

    @GET
    @Path("cities/{regionId}")
    public Response getCities(Integer regionId) {
        Query query = em.createNamedQuery("CitiesEntity.findByRegionId", CitiesEntity.class);
        query.setParameter("regionId",regionId);
        List<CitiesEntity> cities = query.getResultList();
        return Response.ok(cities).build();
    }

    @GET
    @Path("regions/{countryId}")
    public Response getRegions(BigDecimal countryId) {
        Query query = em.createNamedQuery("RegionsEntity.findByCountry", RegionsEntity.class);
        query.setParameter("countryId",countryId);
        List<RegionsEntity> regions = query.getResultList();
        return Response.ok(regions).build();
    }

    @GET
    @Path("countries")
    public Response getCountries() {
        Query query = em.createNamedQuery("CountriesEntity.findAll", CountriesEntity.class);
        List<CountriesEntity> countries = query.getResultList();
        return Response.ok(countries).build();
    }


    @POST
    @Path("save")
    @Transactional
    public Response SaveDeliveryPoint(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal addressId =  new BigDecimal(0);
        BigDecimal longitude =  new BigDecimal(0);
        BigDecimal latitude =  new BigDecimal(0);
        BigDecimal cityId =  new BigDecimal(0);
        BigDecimal orgId = new BigDecimal(0);
        try {
            String addressDetails = String.valueOf(String.valueOf(jsonBody.get("addressDetails")));
            addressDetails =   addressDetails.replace("\"", "");
            String zipcode = String.valueOf(String.valueOf(jsonBody.get("zipcode")));
            zipcode =   zipcode.replace("\"", "");
            String streetNumber = String.valueOf(String.valueOf(jsonBody.get("streetNumber")));
            streetNumber = streetNumber.replace("\"", "");
            String street = String.valueOf(String.valueOf(jsonBody.get("street")));
            street = street.replace("\"", "");
            String cityIdString = String.valueOf(String.valueOf(jsonBody.get("cityId")));
            cityIdString = cityIdString.replace("\"", "");
            if (!"null".equals(cityIdString)) {
                cityId = new BigDecimal(cityIdString);
            }else{
                cityId = null;
            }

            String latitudeString = String.valueOf(String.valueOf(jsonBody.get("latitude")));
            latitudeString = latitudeString.replace("\"", "");

            if (!"null".equals(latitudeString)) {
                latitude = new BigDecimal(latitudeString);
            }else{
                latitude = null;
            }

            String longitudeString = String.valueOf(String.valueOf(jsonBody.get("longitude")));
            longitudeString = longitudeString.replace("\"", "");

            if (!"null".equals(longitudeString)) {
                longitude = new BigDecimal(longitudeString);
            }else{
                longitude = null;
            }

            String deliveryPointAlias = String.valueOf(String.valueOf(jsonBody.get("deliveryPointAlias")));
            deliveryPointAlias = deliveryPointAlias.replace("\"", "");

            String orgIdString = String.valueOf(String.valueOf(jsonBody.get("orgId")));
            orgIdString = orgIdString.replace("\"", "");
            if (!"null".equals(orgIdString)) {
                orgId = new BigDecimal(orgIdString);
            }else{
                orgId = null;
            }

            jsonReader.close();


            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setActive("Y");
            addressEntity.setAdressDetails(addressDetails);
            addressEntity.setCityId(cityId);
            addressEntity.setLatitude(latitude);
            addressEntity.setLongitude(longitude);
            addressEntity.setStreetNumber(streetNumber);
            addressEntity.setZipCode(zipcode);
            addressEntity.setStreet(street);
            LOGGER.info("Address entity "+addressEntity);


            em.persist(addressEntity);

            try {
                em.flush();
                addressId = addressEntity.getAdressId();
            } catch (PersistenceException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }

            DeliveryPointsEntity deliveryPointsEntity = new DeliveryPointsEntity();
            deliveryPointsEntity.setDeliveryPointAlias(deliveryPointAlias);
            deliveryPointsEntity.setAddressId(addressId);
            deliveryPointsEntity.setOrgId(orgId);
            deliveryPointsEntity.setActive("Y");

            LOGGER.info("DP entity "+deliveryPointsEntity);

            em.persist(deliveryPointsEntity);

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

    @POST
    @Path("show")
    public Response showDeliveryPoints(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal orgId;
        String namedQuery;
        if (jsonBody.containsKey("orgId") && !jsonBody.isNull("orgId")) {
            String orgIdString = jsonBody.get("orgId").toString().replaceAll("\"", "");
            orgId = new BigDecimal(orgIdString);
            namedQuery = "DeliveryPointsVWEntity.findByOrgId";
        } else {
            orgId = null;
            namedQuery = "DeliveryPointsVWEntity.findAll";
        }
        jsonReader.close();

        Query queryDeliveyPoints = em.createNamedQuery(namedQuery, DeliveryPointsEntity.class);
        if (orgId != null) {
            queryDeliveyPoints.setParameter("orgId", orgId);
        }
        List<DeliveryPointsEntity> deliveryPointsEntities = queryDeliveyPoints.getResultList();

        return Response.ok(deliveryPointsEntities).build();
    }
}