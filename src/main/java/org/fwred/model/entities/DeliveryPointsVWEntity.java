package org.fwred.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="DELIVERY_POINTS_VW")
@NamedQuery(name = "DeliveryPointsVWEntity.findAll", query = "from DeliveryPointsVWEntity where active = 'Y' order by deliveryPointId desc")
@NamedQuery(name = "DeliveryPointsVWEntity.findByOrgId", query = "from DeliveryPointsVWEntity where active = 'Y' and orgId = :orgId order by deliveryPointId desc")

public class DeliveryPointsVWEntity {

    @Id
    @Column(name = "DELIVERY_POINT_ID")
    private BigDecimal deliveryPointId;

    @Column(name = "ADDRESS_ID")
    private BigDecimal addressId;

    @Column(name = "STREET")
    private String street;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "REGION_NAME")
    private String regionName;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "DELIVERY_POINT_ALIAS")
    private String deliveryPointAlias;

    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "STREET_NUMBER")
    private String streetNumner;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "TITLE")
    private String title;

    public DeliveryPointsVWEntity() {
    }

    public BigDecimal getDeliveryPointId() {
        return deliveryPointId;
    }

    public void setDeliveryPointId(BigDecimal deliveryPointId) {
        this.deliveryPointId = deliveryPointId;
    }

    public BigDecimal getAddressId() {
        return addressId;
    }

    public void setAddressId(BigDecimal addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDeliveryPointAlias() {
        return deliveryPointAlias;
    }

    public void setDeliveryPointAlias(String deliveryPointAlias) {
        this.deliveryPointAlias = deliveryPointAlias;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetNumner() {
        return streetNumner;
    }

    public void setStreetNumner(String streetNumner) {
        this.streetNumner = streetNumner;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
