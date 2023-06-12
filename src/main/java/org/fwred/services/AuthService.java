package org.fwred.services;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fwred.model.entities.AppUserTypeEntity;
import org.fwred.model.entities.AppUsersEntity;
import utils.authorisation.PBKDF2Encoder;
import utils.authorisation.Token;
import utils.authorisation.model.AppUser;
import utils.authorisation.model.AuthRequest;
import utils.authorisation.model.AuthResponse;
import utils.authorisation.model.Roles;

import java.math.BigDecimal;
import java.util.Collections;

@Path("auth")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

public class AuthService {
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(UserService.class.getName());

    @ConfigProperty(name = "quarkusjwt.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;
    @Inject
    EntityManager em;

    @Inject
    PBKDF2Encoder passwordEncoder;

    public AppUser findByUsername(String userName) {

        userName =   userName.replace("\"", "");
        String adminFlag;
        String connectionSecureString;
        BigDecimal userTypeId;
        String usernameDB;
        BigDecimal orgId;

        try {
            Query queryUsers = em.createNamedQuery("AppUsersEntity.findByUserName", AppUserTypeEntity.class);
            queryUsers.setParameter("name", userName);
            AppUsersEntity usersEntity = (AppUsersEntity) queryUsers.getSingleResult();
            adminFlag = usersEntity.getAdmin() != null ? usersEntity.getAdmin() : "N";
            connectionSecureString = usersEntity.getConnectionSecureString();
            userTypeId = usersEntity.getUserTypeId();
            orgId = usersEntity.getOrgId();
            usernameDB = usersEntity.getName();
        }catch (NoResultException e){
            return null;
        }
       try {
            Query queryUserType = em.createNamedQuery("AppUserType.findById", AppUserTypeEntity.class);
            queryUserType.setParameter("userTypeId", userTypeId);
            AppUserTypeEntity userTypeEntity = (AppUserTypeEntity) queryUserType.getSingleResult();

            if ( usernameDB.equalsIgnoreCase(userName) && adminFlag.equals("N") && userTypeEntity.getUserType().equals("RECEIVER")) {
                return new AppUser(userName, connectionSecureString, Collections.singleton(Roles.RECEIVER),orgId);
            }else {
                return new AppUser(userName, connectionSecureString, Collections.singleton(Roles.DONOR),orgId);
            }
        } catch (NoResultException e) {
           if (adminFlag.equalsIgnoreCase("Y")){
               return new AppUser(userName,connectionSecureString, Collections.singleton(Roles.ADMIN),null);
           }else
               return null;

        }
    }

    @PermitAll
    @POST
    @Path("login")
    public Response login(AuthRequest authRequest) {
        AppUser appUser = findByUsername(authRequest.username);
        if (appUser != null && appUser.password.equals(passwordEncoder.encode(authRequest.password))) {
            try {
                AuthResponse authResponse = new AuthResponse(Token.generateToken(appUser.userName, appUser.roles, duration, issuer), appUser.userName,appUser.roles,appUser.orgId);
                return Response.ok(authResponse).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
