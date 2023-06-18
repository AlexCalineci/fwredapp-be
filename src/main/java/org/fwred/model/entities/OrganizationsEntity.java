package org.fwred.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="ORGANIZATIONS")
@NamedQuery(name = "OrganizationsEntity.findAll", query = "from OrganizationsEntity")
@NamedQuery(name = "OrganizationsEntity.findByOrgId", query = "from OrganizationsEntity where orgId = :orgId")

public class OrganizationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;

    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @Column(name = "LEGAL_DETAILS")
    private String legalDetails;

    @Column(name = "FIC")
    private String fic;

    @Column(name = "ACTIVE")
    private String active;


    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLegalDetails() {
        return legalDetails;
    }

    public void setLegalDetails(String legalDetails) {
        this.legalDetails = legalDetails;
    }

    public String getFic() {
        return fic;
    }

    public void setFic(String fic) {
        this.fic = fic;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
