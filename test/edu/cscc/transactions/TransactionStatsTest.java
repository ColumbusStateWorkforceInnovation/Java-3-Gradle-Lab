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

    @Test
    public void itCanAddATransactionToTheTransactionList() {
        TransactionStats transactionStats= new TransactionStats();
        Transaction transaction = TransactionBuilder.create().build();

        transactionStats.add(transaction);

        assertEquals(1, transactionStats.totalRentals());
    }

    @Test
    public void itReturnsTheMostPopularMovie() {
        String theMatrix = "The Matrix";
        String fellowship = "Lord of the Rings: The Fellowship of the Ring";
        TransactionStats transactionStats = new TransactionStats();
        transactionStats.add(TransactionBuilder.create().withRentalName(theMatrix).build());
        transactionStats.add(TransactionBuilder.create().withRentalName(theMatrix).build());
        transactionStats.add(TransactionBuilder.create().withRentalName(fellowship).build());

        assertEquals(3, transactionStats.totalRentals());
        assertEquals(theMatrix, transactionStats.mostPopularMovie());
    }

}