package com.econetwireless.in.soap.messages;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public class CreditResponse {
    private String responseCode;
    private String narrative;
    private String msisdn;
    private double balance;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditResponse{");
        sb.append("responseCode='").append(responseCode).append('\'');
        sb.append(", narrative='").append(narrative).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
