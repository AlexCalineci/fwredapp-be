package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="RESERVATIONS_VW")

@NamedQueries({
        @NamedQuery(name = "ReservationsVWEntity.findAll", query = "from ReservationsVWEntity"),
        @NamedQuery(name = "ReservationsVWEntity.findByDonor", query = "from ReservationsVWEntity where donorOrgId = :orgId"),
        @NamedQuery(name = "ReservationsVWEntity.findByReceiver", query = "from ReservationsVWEntity where receiverOrgId = :orgId"),
        @NamedQuery(name = "ReservationsVWEntity.findByDonorandReceiver", query = "from ReservationsVWEntity where receiverOrgId = :receiverOrgId and donorOrgId = :donorOrgId")
})

public class ReservationsVWEntity {


    @Id
    @Column(name = "RESERVATION_ID")
    private BigDecimal reservationId;

    @Column(name = "QUANTITY_TYPE")
    private String quantityType;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "FINALYZED")
    private String finalyzed;


    @Column(name = "DONOR_ORG_ID")
    private BigDecimal donorOrgId;

    @Column(name = "RECEIVER_ORG_ID")
    private BigDecimal receiverOrgId;

    @Column(name = "DONOR_NAME")
    private String donorName;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;


    @Column(name = "FOOD_ITEM_NAME")
    private String foodItemName;

    @Column(name = "FOOD_ITEM_ID")
    private BigDecimal foodItemId;

    @Column(name = "RESERVATION_DATE")
    private Date reservationDate;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "AVAILABLE_QUANTITY")
    private String availableQuantity;

    @Column(name = "TOTAL_COST_PRICE")
    private BigDecimal totalCostPrice;

    public BigDecimal getReservationId() {
        return reservationId;
    }

    public void setReservationId(BigDecimal reservationId) {
        this.reservationId = reservationId;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
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

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public BigDecimal getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(BigDecimal foodItemId) {
        this.foodItemId = foodItemId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(BigDecimal totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
