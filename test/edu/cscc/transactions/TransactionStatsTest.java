package edu.cscc.transactions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionStatsTest {

    @Test
    public void itReturnsZeroTotalRentalsWhenTheTransactionListIsEmpty() {
        TransactionStats transactionStats = new TransactionStats(new ArrayList<>());

        assertEquals(0, transactionStats.totalRentals());
        assertEquals(0, new TransactionStats().totalRentals());
    }

    @Test
    public void itReturnsTheTotalRentalsWhenTheTransactionListIsNotEmpty() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(TransactionBuilder.create().build());
        transactions.add(TransactionBuilder.create().build());
        TransactionStats transactionStats = new TransactionStats(transactions);

        assertEquals(2, transactionStats.totalRentals());
    }

}