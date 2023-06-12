package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="FOOD_ITEMS")
public class FoodItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ITEM_ID")
    private BigDecimal foodItemId;

    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;
    @Column(name = "AVAILABLE_QUANTITY")
    private BigDecimal availableQuantity;

    @Column(name = "QUANTITY_TYPE")
    private String quantityType;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "DELIVERY_POINT_ID")
    private BigDecimal deliveryPointId;

    @Column(name = "DISCOUNT_ID")
    private BigDecimal discountId;

    public BigDecimal getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(BigDecimal foodItemId) {
        this.foodItemId = foodItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public BigDecimal getDeliveryPointId() {
        return deliveryPointId;
    }

    public void setDeliveryPointId(BigDecimal deliveryPointId) {
        this.deliveryPointId = deliveryPointId;
    }

    public BigDecimal getDiscountId() {
        return discountId;
    }

    public void setDiscountId(BigDecimal discountId) {
        this.discountId = discountId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }
}
