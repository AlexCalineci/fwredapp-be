package org.fwred.model.entities;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name="Address")

public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private BigDecimal adressId;

    @Column(name = "ADDRESS_DETAILS")
    private String adressDetails;

    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    private BigDecimal  longitude;

    @Column(name = "ACTIVE")
    private String  active;

    @Column(name = "STREET")
    private String  street;

    @Column(name = "ZIPCODE")
    private String  zipCode;

    @Column(name = "STREET_NUMBER")
    private String  streetNumber;
    @Column(name = "CITY_ID")
    private BigDecimal  cityId;


    public AddressEntity() {

    }

    public BigDecimal getAdressId() {
        return adressId;
    }

    public void setAdressId(BigDecimal adressId) {
        this.adressId = adressId;
    }

    public String getAdressDetails() {
        return adressDetails;
    }

    public void setAdressDetails(String adressDetails) {
        this.adressDetails = adressDetails;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public BigDecimal getCityId() {
        return cityId;
    }

    public void setCityId(BigDecimal cityId) {
        this.cityId = cityId;
    }
}


