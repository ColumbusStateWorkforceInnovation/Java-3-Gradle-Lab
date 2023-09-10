package edu.cscc.transactions;

// TransactionBuilder.java

public class TransactionBuilder {

    private Transaction transaction;

    public TransactionBuilder() {
        transaction = new Transaction();
    }

    public TransactionBuilder withEmployeeId(String employeeId) {
        transaction.setEmployeeId(employeeId);
        return this;
    }

    public TransactionBuilder withCustomerId(String customerId) {
        transaction.setCustomerId(customerId);
        return this;
    }

    public TransactionBuilder withStoreNumber(String storeNumber) {
        transaction.setStoreNumber(storeNumber);
        return this;
    }

    public TransactionBuilder withRentalId(String rentalId) {
        transaction.setRentalId(rentalId);
        return this;
    }

    public TransactionBuilder withRentalName(String rentalName) {
        transaction.setRentalName(rentalName);
        return this;
    }

    public TransactionBuilder withRentalCost(Double rentalCost) {
        transaction.setRentalCost(rentalCost);
        return this;
    }

    public TransactionBuilder withDate(String date) {
        transaction.setDate(date);
        return this;
    }

    public Transaction build() {
        return transaction;
    }

    // Static method to get an instance of the builder
    public static TransactionBuilder create() {
        return new TransactionBuilder();
    }
}
