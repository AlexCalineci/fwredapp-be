package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="Countries")
@NamedQueries({
        @NamedQuery(name = "CountriesEntity.findAll", query = "SELECT c FROM CountriesEntity c")
})
public class CountriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID")
    private BigDecimal countryId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    public BigDecimal getCountryId() {
        return countryId;
    }

    public void setCountryId(BigDecimal countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
