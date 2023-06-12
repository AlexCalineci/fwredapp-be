package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name="Regions")
@NamedQueries({
        @NamedQuery(name = "RegionsEntity.findAll", query = "SELECT r FROM RegionsEntity r"),
        @NamedQuery(name = "RegionsEntity.findByCountry", query = "SELECT r FROM RegionsEntity r where countryId = :countryId")
})
public class RegionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    private BigDecimal regionId;

    @Column(name = "REGION_NAME")
    private String regionName;

    @Column(name = "COUNTRY_ID")
    private BigDecimal countryId;

    public BigDecimal getRegionId() {
        return regionId;
    }

    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public BigDecimal getCountryId() {
        return countryId;
    }

    public void setCountryId(BigDecimal countryId) {
        this.countryId = countryId;
    }
}