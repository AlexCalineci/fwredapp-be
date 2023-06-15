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
import org.fwred.model.entities.FeedbackRatingsEntity;
import org.fwred.model.entities.FeedbackRatingsVWEntity;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Path("feedback")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FeebackRatingsService {

    @Inject
    EntityManager em;

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("show")
    public Response showFeedbacks(String jsonString)  {

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
            namedQuery = "FeedbackRatingsVWEntity.findByRated";
        }else{
            namedQuery = "FeedbackRatingsVWEntity.findByRater";
        }
        Query feedbackRatings  =  em.createNamedQuery(namedQuery, FeedbackRatingsVWEntity.class);
        feedbackRatings.setParameter("orgId", orgId);
        List<FeedbackRatingsVWEntity> feedbackRatingsVWEntities = feedbackRatings.getResultList();

        return  Response.ok(feedbackRatingsVWEntities).build();
    }

    @RolesAllowed({"DONOR", "RECEIVER"})
    @POST
    @Path("add")
    @Transactional
    public Response addFeedback(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal raterOrgId = new BigDecimal(0);
        BigDecimal ratedOrgId = new BigDecimal(0);
        BigDecimal reservationId = new BigDecimal(0);
        BigDecimal starRating = new BigDecimal(0);
        Date rateDate = Date.valueOf(LocalDate.now());
        try {

            Integer raterOrgIdInt = jsonBody.getInt("raterOrgId");
            if (!"null".equals(raterOrgIdInt)) {
                raterOrgId = new BigDecimal(raterOrgIdInt);
            } else {
                raterOrgId = null;
            }

            Integer ratedOrgIdInt = jsonBody.getInt("ratedOrgId");
            if (!"null".equals(ratedOrgIdInt)) {
                ratedOrgId = new BigDecimal(ratedOrgIdInt);
            } else {
                ratedOrgId = null;
            }

            Integer reservationIdInt = jsonBody.getInt("reservationId");
            if (!"null".equals(reservationIdInt)) {
                reservationId = new BigDecimal(reservationIdInt);
            } else {
                reservationId = null;
            }

            Integer starRatingInt = jsonBody.getInt("starRating");
            if (!"null".equals(starRatingInt)) {
                starRating = new BigDecimal(starRatingInt);
            } else {
                starRating = null;
            }

            jsonReader.close();

            FeedbackRatingsEntity feedbackRatings = new FeedbackRatingsEntity();
            feedbackRatings.setRatedOrgId(ratedOrgId);
            feedbackRatings.setRaterOrgId(raterOrgId);
            feedbackRatings.setReservationId(reservationId);
            feedbackRatings.setStarRating(starRating);
            feedbackRatings.setRateDate(rateDate);

            em.persist(feedbackRatings);
            try {
                em.flush();
            } catch (PersistenceException e) {
                throw new RuntimeException(e);
            }

            return Response.ok().build();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new RuntimeException(e);
        }

    }

}
