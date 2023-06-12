package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="DELIVERY_POINTS")
public class DeliveryPointsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_POINT_ID")
    private BigDecimal deliveryPointId;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "DELIVERY_POINT_ALIAS")
    private String deliveryPointAlias;

    @Column(name = "ADDRESS_ID")
    private BigDecimal addressId;

    @Column(name = "ACTIVE")
    private String active;

    public BigDecimal getDeliveryPointId() {
        return deliveryPointId;
    }

    public void setDeliveryPointId(BigDecimal deliveryPointId) {
        this.deliveryPointId = deliveryPointId;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getDeliveryPointAlias() {
        return deliveryPointAlias;
    }

    public void setDeliveryPointAlias(String deliveryPointAlias) {
        this.deliveryPointAlias = deliveryPointAlias;
    }

    public BigDecimal getAddressId() {
        return addressId;
    }

    public void setAddressId(BigDecimal addressId) {
        this.addressId = addressId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
