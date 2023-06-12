package org.fwred.model.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="APP_USER_TYPE")
@NamedQuery(name = "AppUserType", query = "from AppUserTypeEntity")
@NamedQuery(name = "AppUserType.findById", query = "from AppUserTypeEntity where userTypeId = :userTypeId")

public class AppUserTypeEntity {

    @Id
    @Column(name = "USER_TYPE_ID")
    private BigDecimal userTypeId;

    @Column(name = "USER_TYPE")
    private String userType;

    public BigDecimal getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(BigDecimal userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
