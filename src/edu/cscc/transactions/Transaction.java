package edu.cscc.transactions;

import java.util.Objects;

/**
 * Represents a transaction in the the rental_transactions.csv.
 */
public class Transaction {

    private String employeeId;
    private String customerId;
    private String storeNumber;
    private String rentalId;
    private String rentalName;
    private Double rentalCost;
    private String date;

    public Transaction() {}

    public Transaction(String employeeId, String customerId, String storeNumber, String rentalId, String rentalName, Double rentalCost, String date) {

        this.employeeId = employeeId;
        this.customerId = customerId;
        this.storeNumber = storeNumber;
        this.rentalId = rentalId;
        this.rentalName = rentalName;
        this.rentalCost = rentalCost;
        this.date = date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    public Double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(Double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(storeNumber, that.storeNumber) &&
                Objects.equals(rentalId, that.rentalId) &&
                Objects.equals(rentalName, that.rentalName) &&
                Objects.equals(rentalCost, that.rentalCost) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, customerId, storeNumber, rentalId, rentalName, rentalCost, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "employeeId='" + employeeId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", storeNumber='" + storeNumber + '\'' +
                ", rentalId='" + rentalId + '\'' +
                ", rentalName='" + rentalName + '\'' +
                ", rentalCost=" + rentalCost +
                ", date='" + date + '\'' +
                '}';
    }
}
