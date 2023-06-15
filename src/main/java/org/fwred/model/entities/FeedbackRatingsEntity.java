package org.fwred.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="FEEDBACK_RATINGS")
public class FeedbackRatingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACK_ID")
    private BigDecimal feebackId;

    @Column(name = "RESERVATION_ID")
    private BigDecimal reservationId;

    @Column(name = "RATED_ORG_ID")
    private BigDecimal ratedOrgId;

    @Column(name = "RATER_ORG_ID")
    private BigDecimal raterOrgId;


    @Column(name = "STAR_RATING")
    private BigDecimal starRating;

    @Column(name = "RATE_DATE")
    private Date rateDate;


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

    public BigDecimal getRatedOrgId() {
        return ratedOrgId;
    }

    public void setRatedOrgId(BigDecimal ratedOrgId) {
        this.ratedOrgId = ratedOrgId;
    }

    public BigDecimal getRaterOrgId() {
        return raterOrgId;
    }

    public void setRaterOrgId(BigDecimal raterOrgId) {
        this.raterOrgId = raterOrgId;
    }

    public BigDecimal getStarRating() {
        return starRating;
    }

    public void setStarRating(BigDecimal starRating) {
        this.starRating = starRating;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }
}
