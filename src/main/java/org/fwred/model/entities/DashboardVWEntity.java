package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="DASHBOARD_VW")
@NamedQuery(name = "DashboardVWEntity.findAll", query = "from DashboardVWEntity")

public class DashboardVWEntity {

    @Id
    @Column(name = "JOIN_COL")
    private BigDecimal joinCol;

    @Column(name = "TOTAL_REGISTERED_RECEIVERS")
    private BigDecimal totalRegisteredReceivers;

    @Column(name = "TOTAL_REGISTERED_DONORS")
    private BigDecimal totalRegisteredDonors;

    @Column(name = "TOTAL_FOOD_RESERVATIONS")
    private BigDecimal totalFoodReservations;

    @Column(name = "TOTAL_REGISTERED_FOOD_ITEMS")
    private BigDecimal totalRegisteredFoodItems;


    public BigDecimal getJoinCol() {
        return joinCol;
    }

    public void setJoinCol(BigDecimal joinCol) {
        this.joinCol = joinCol;
    }

    public BigDecimal getTotalRegisteredReceivers() {
        return totalRegisteredReceivers;
    }

    public void setTotalRegisteredReceivers(BigDecimal totalRegisteredReceivers) {
        this.totalRegisteredReceivers = totalRegisteredReceivers;
    }

    public BigDecimal getTotalRegisteredDonors() {
        return totalRegisteredDonors;
    }

    public void setTotalRegisteredDonors(BigDecimal totalRegisteredDonors) {
        this.totalRegisteredDonors = totalRegisteredDonors;
    }

    public BigDecimal getTotalFoodReservations() {
        return totalFoodReservations;
    }

    public void setTotalFoodReservations(BigDecimal totalFoodReservations) {
        this.totalFoodReservations = totalFoodReservations;
    }

    public BigDecimal getTotalRegisteredFoodItems() {
        return totalRegisteredFoodItems;
    }

    public void setTotalRegisteredFoodItems(BigDecimal totalRegisteredFoodItems) {
        this.totalRegisteredFoodItems = totalRegisteredFoodItems;
    }
}
