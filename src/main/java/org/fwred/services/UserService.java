package org.fwred.services;

import jakarta.annotation.security.PermitAll;
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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fwred.model.entities.AppUserTypeEntity;
import org.fwred.model.entities.AppUsersEntity;
import org.fwred.model.entities.OrganizationsEntity;
import utils.authorisation.*;
import utils.authorisation.model.AppUser;
import utils.authorisation.model.AuthRequest;
import utils.authorisation.model.AuthResponse;
import utils.authorisation.model.Roles;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Path("users")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

public class UserService {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(UserService.class.getName());

    @Inject
    EntityManager em;

    @Inject
    PBKDF2Encoder passwordEncoder;
    @GET
    @Path("usertype")
    public Response showUserTypes()  {
        Query query  =  em.createNamedQuery("AppUserType", AppUserTypeEntity.class);
        List<AppUserTypeEntity> userTypeEntities = query.getResultList();
        return  Response.ok(userTypeEntities).build();
    }


    @POST
    @Path("registration")
    @Transactional
    public Response registerUserAndOrganisation(String jsonString) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonBody = jsonReader.readObject();

        BigDecimal orgId =  new BigDecimal(0);
        try {
            String userName = String.valueOf(String.valueOf(jsonBody.get("userName")));
            userName =   userName.replace("\"", "");
            String userTypeIdString = String.valueOf(String.valueOf(jsonBody.get("userTypeId")));
            userTypeIdString =   userTypeIdString.replace("\"", "");
            BigDecimal userTypeId = new BigDecimal(userTypeIdString);
            String email = String.valueOf(String.valueOf(jsonBody.get("email")));
            email = email.replace("\"", "");
            String connectionSecureString = String.valueOf(String.valueOf(jsonBody.get("connectionSecureString")));
            connectionSecureString = connectionSecureString.replace("\"", "");
            String organizationName = String.valueOf(String.valueOf(jsonBody.get("organizationName")));
            organizationName = organizationName.replace("\"", "");
            String description = String.valueOf(String.valueOf(jsonBody.get("description")));
            description = description.replace("\"", "");
            String contactPerson = String.valueOf(String.valueOf(jsonBody.get("contactPerson")));
            contactPerson = contactPerson.replace("\"", "");
            String contactEmail = String.valueOf(String.valueOf(jsonBody.get("contactEmail")));
            contactEmail = contactEmail.replace("\"", "");
            String contactPhone = String.valueOf(String.valueOf(jsonBody.get("contactPhone")));
            contactPhone = contactPhone.replace("\"", "");
            String legalDetails = String.valueOf(String.valueOf(jsonBody.get("legalDetails")));
            legalDetails = legalDetails.replace("\"", "");
            String fic = String.valueOf(String.valueOf(jsonBody.get("fic")));
            fic = fic.replace("\"", "");

            jsonReader.close();

            OrganizationsEntity organizationsEntity = new OrganizationsEntity();
            organizationsEntity.setContactEmail(contactEmail);
            organizationsEntity.setContactPerson(contactPerson);
            organizationsEntity.setContactPhone(contactPhone);
            organizationsEntity.setFic(fic);
            organizationsEntity.setLegalDetails(legalDetails);
            organizationsEntity.setName(organizationName);
            organizationsEntity.setDescription(description);
            organizationsEntity.setActive("Y");
            em.persist(organizationsEntity);

            try {
                em.flush();
                orgId = organizationsEntity.getOrgId();
            } catch (PersistenceException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }

            AppUsersEntity usersEntity = new AppUsersEntity();
            usersEntity.setEmail(email);
            usersEntity.setName(userName);
            usersEntity.setConnectionSecureString(passwordEncoder.encode(connectionSecureString));
            usersEntity.setUserTypeId(userTypeId);
            usersEntity.setOrgId(orgId);
            usersEntity.setActive("Y");

            em.persist(usersEntity);

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
