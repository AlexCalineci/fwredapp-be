package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="APP_USERS")
@NamedQuery(name = "AppUsersEntity.findByUserName", query = "from AppUsersEntity where active ='Y' and lower(name) = lower(:name)")

public class AppUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private BigDecimal userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USER_TYPE_ID")
    private BigDecimal userTypeId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CONNECTION_SECURE_STRING")
    private String connectionSecureString;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "ADMIN")
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }


    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(BigDecimal userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConnectionSecureString() {
        return connectionSecureString;
    }

    public void setConnectionSecureString(String connectionSecureString) {
        this.connectionSecureString = connectionSecureString;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
