package org.fwred.model.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="FEEDBACK_RATINGS_VW")
@NamedQuery(name = "FeedbackRatingsVWEntity.findByRated", query = "from FeedbackRatingsVWEntity where ratedOrgId = :orgId")
@NamedQuery(name = "FeedbackRatingsVWEntity.findByRater", query = "from FeedbackRatingsVWEntity where raterOrgId = :orgId")

public class FeedbackRatingsVWEntity {

    @Id
    @Column(name = "FEEDBACK_ID")
    private BigDecimal feebackId;

    @Column(name = "RESERVATION_ID")
    private BigDecimal reservationId;

    @Column(name = "RATER_ORG_ID")
    private BigDecimal raterOrgId;

    @Column(name = "RATED_ORG_ID")
    private BigDecimal ratedOrgId;

    @Column(name = "STAR_RATING")
    private BigDecimal startRating;

    @Column(name = "RATED_ORG_TYPE")
    private String ratedOrgType;

    @Column(name = "RATE_DATE")
    private Date rateDate;

    @Column(name = "RATER_ORG_TYPE")
    private String raterOrgType;

    @Column(name = "RATED_ORGANIZATION_NAME")
    private String ratedOrganizationName;

    @Column(name = "RATER_ORGANIZATION_NAME")
    private String raterOrganizationName;

    @Column(name = "RESERVATION_DETAILS")
    private String reservationDetails;

    public BigDecimal getFeebackId() {
        return feebackId;
    }

    public void setFeebackId(BigDecimal feebackId) {
        this.feebackId = feebackId;
    }

    public BigDecimal getReservationId() {
        return reservationId;
    }

    public void setReservationId(BigDecimal reservationId) {
        this.reservationId = reservationId;
    }

    public BigDecimal getRaterOrgId() {
        return raterOrgId;
    }

    public void setRaterOrgId(BigDecimal raterOrgId) {
        this.raterOrgId = raterOrgId;
    }

    public BigDecimal getStartRating() {
        return startRating;
    }

    public void setStartRating(BigDecimal startRating) {
        this.startRating = startRating;
    }

    public String getRatedOrgType() {
        return ratedOrgType;
    }

    public void setRatedOrgType(String ratedOrgType) {
        this.ratedOrgType = ratedOrgType;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public String getRaterOrgType() {
        return raterOrgType;
    }

    public void setRaterOrgType(String raterOrgType) {
        this.raterOrgType = raterOrgType;
    }

    public String getRatedOrganizationName() {
        return ratedOrganizationName;
    }

    public void setRatedOrganizationName(String ratedOrganizationName) {
        this.ratedOrganizationName = ratedOrganizationName;
    }

    public String getRaterOrganizationName() {
        return raterOrganizationName;
    }

    public void setRaterOrganizationName(String raterOrganizationName) {
        this.raterOrganizationName = raterOrganizationName;
    }

    public BigDecimal getRatedOrgId() {
        return ratedOrgId;
    }

    public void setRatedOrgId(BigDecimal ratedOrgId) {
        this.ratedOrgId = ratedOrgId;
    }

    public String getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(String reservationDetails) {
        this.reservationDetails = reservationDetails;
    }
}
