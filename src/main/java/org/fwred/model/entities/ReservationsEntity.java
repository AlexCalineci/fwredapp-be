package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="RESERVATIONS")
public class ReservationsEntity {

    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal reservationId;

    @Column(name = "FOOD_ITEM_ID")
    private BigDecimal foodItemId;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "QUANTITY_TYPE")
    private String quantityType;

    @Column(name = "RESERVATION_DATE")
    private Date reservationDate;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "FINALYZED")
    private String finalyzed;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DONOR_ORG_ID")
    private BigDecimal donorOrgId;

    @Column(name = "RECEIVER_ORG_ID")
    private BigDecimal receiverOrgId;

    public BigDecimal getReservationId() {
        return reservationId;
    }

    public void setReservationId(BigDecimal reservationId) {
        this.reservationId = reservationId;
    }

    public BigDecimal getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(BigDecimal foodItemId) {
        this.foodItemId = foodItemId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getFinalyzed() {
        return finalyzed;
    }

    public void setFinalyzed(String finalyzed) {
        this.finalyzed = finalyzed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDonorOrgId() {
        return donorOrgId;
    }

    public void setDonorOrgId(BigDecimal donorOrgId) {
        this.donorOrgId = donorOrgId;
    }

    public BigDecimal getReceiverOrgId() {
        return receiverOrgId;
    }

    public void setReceiverOrgId(BigDecimal receiverOrgId) {
        this.receiverOrgId = receiverOrgId;
    }
}
