package com.econetwireless.epay.domain;

import com.econetwireless.utils.constants.SystemConstants;
import com.econetwireless.utils.keygen.KeyGen;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@Entity(name = "request")
@NamedQueries({@NamedQuery(name = "SubscriberRequest.findByPartnerCode", query = "select r from Request r where r.partnerCode = :partnerCode order by r.dateCreated desc ")})
@Table(name = "e_request", indexes = {@Index(name = "req_msisdn_indx", columnList = "mobile_number")})
public class SubscriberRequest {
    @Id
    private Long id;
    @Column(name = "request_type", length = 30)
    private String requestType;
    @Column(name = "partner_code", length = 30)
    private String partnerCode;
    @Column(name = "mobile_number", length = 8)
    private String msisdn;
    @Column(name = "balance_before")
    private double balanceBefore;
    @Column(name = "balance_after")
    private double balanceAfter;
    private double amount;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastUpdated;
    @Column(name = "status", length = 30)
    private String status;
    @Column(name = "reference_number", length = 30)
    private String reference;
    @Version
    private long version;

    @PreInsert
    protected void init() {
        if(id == null) {
            id = KeyGen.getUniqueId();
        }
        dateCreated = new Date();

        if(status == null) {
            status = SystemConstants.STATUS_NEW;
        }

    }

    @PreUpdate
    protected void reload() {
        dateLastUpdated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public double getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(double balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(Date dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SubscriberRequest{");
        sb.append("id=").append(id);
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append(", partnerCode='").append(partnerCode).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", balanceBefore=").append(balanceBefore);
        sb.append(", balanceAfter=").append(balanceAfter);
        sb.append(", amount=").append(amount);
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateLastUpdated=").append(dateLastUpdated);
        sb.append(", status='").append(status).append('\'');
        sb.append(", reference='").append(reference).append('\'');
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
