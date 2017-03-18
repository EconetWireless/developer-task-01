package com.econetwireless.in.soap.messages;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public class CreditRequest {
    private String partnerCode;
    private String msisdn;
    private double amount;
    private String referenceNumber;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditRequest{");
        sb.append("partnerCode='").append(partnerCode).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", referenceNumber='").append(referenceNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
