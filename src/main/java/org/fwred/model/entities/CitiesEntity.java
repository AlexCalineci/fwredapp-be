package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name="Cities")
@NamedQueries({
        @NamedQuery(name = "CitiesEntity.findAll", query = "SELECT c FROM CitiesEntity c"),
        @NamedQuery(name = "CitiesEntity.findByRegionId", query = "SELECT c FROM CitiesEntity c where regionId = :regionId")
})
public class CitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private BigDecimal cityId;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "REGION_ID")
    private BigDecimal regionId;

    @Column(name = "COUNTRY_ID")
    private BigDecimal countryId;

    public BigDecimal getCityId() {
        return cityId;
    }

    public void setCityId(BigDecimal cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BigDecimal getRegionId() {
        return regionId;
    }

    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public BigDecimal getCountryId() {
        return countryId;
    }

    public void setCountryId(BigDecimal countryId) {
        this.countryId = countryId;
    }
}
