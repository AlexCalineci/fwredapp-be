package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="DISCOUNTS")
@NamedQuery(name = "DiscountsEntity.findByOrgId", query = "from DiscountsEntity where active = 'Y' and orgId = :orgId order by  discountId desc")
@NamedQuery(name = "DiscountsEntity.findByDiscountId", query = "from DiscountsEntity where active = 'Y' and discountId = :discountId order by discountId desc")

public class DiscountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISCOUNT_ID")
    private BigDecimal discountId;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "DISCOUNT_PERCENTAGE")
    private BigDecimal discountPercentage;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "ACTIVE")
    private String active;


    public BigDecimal getDiscountId() {
        return discountId;
    }

    public void setDiscountId(BigDecimal discountId) {
        this.discountId = discountId;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
