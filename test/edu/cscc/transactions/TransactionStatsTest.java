package edu.cscc.transactions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionStatsTest {

    @Test
    public void itReturnsZeroTotalRentalsWhenTheTransactionListIsEmpty() {
        TransactionStats transactionStats = new TransactionStats(new ArrayList<>());

        assertEquals(0, transactionStats.totalRentals());
    }

}