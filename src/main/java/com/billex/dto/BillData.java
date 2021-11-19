package com.billex.dto;

public class BillData {

    String consumerNumber;
    String address;
    String mobileNumber;
    String email;
    String billNumber;
    String averageConsumption;
    String netAmount;
    String totalUnit;

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(String averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }

    public BillData setAllBillDetails(String consumerNumber,
            String address,
            String mobileNumber,
            String email,
            String billNumber,
            String averageConsumption,
            String netAmount,
            String totalUnit)
    {
        BillData data = new BillData();
        data.setConsumerNumber(consumerNumber);
        data.setAddress(address);
        data.setMobileNumber(mobileNumber);
        data.setEmail(email);
        data.setBillNumber(billNumber);
        data.setAverageConsumption(averageConsumption);
        data.setNetAmount(netAmount);
        data.setTotalUnit(totalUnit);
        return data;
    }
}
