package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name="FOOD_ITEMS_VW")
@NamedQuery(name = "FoodItemsVWEntity.findByOrgId", query = "from FoodItemsVWEntity where active = 'Y' and orgId = :orgId")
@NamedQuery(name = "FoodItemsVWEntity.findAll", query = "from FoodItemsVWEntity where active = 'Y'")

public class FoodItemsVWEntity {
    @Id
    @Column(name = "FOOD_ITEM_ID")
    private Integer foodItemId;

    @Column(name = "DISCOUNT_ID")
    private BigDecimal discountId;

    @Column(name = "QUANTITY_TYPE")
    private String quantityType;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AVAILABLE_QUANTITY")
    private BigDecimal availableQuantity;

    @Column(name = "DELIVERY_POINT_ID")
    private BigDecimal deliveryPointId;


    @Column(name = "DELIVERY_POINT_ALIAS")
    private String deliveryPointAlias;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DISCOUNT_PERCENTAGE")
    private BigDecimal discountPercentage;

    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;


    public Integer getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(Integer foodItemId) {
        this.foodItemId = foodItemId;
    }

    public BigDecimal getDiscountId() {
        return discountId;
    }

    public void setDiscountId(BigDecimal discountId) {
        this.discountId = discountId;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public BigDecimal getDeliveryPointId() {
        return deliveryPointId;
    }

    public void setDeliveryPointId(BigDecimal deliveryPointId) {
        this.deliveryPointId = deliveryPointId;
    }

    public String getDeliveryPointAlias() {
        return deliveryPointAlias;
    }

    public void setDeliveryPointAlias(String deliveryPointAlias) {
        this.deliveryPointAlias = deliveryPointAlias;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }
}
