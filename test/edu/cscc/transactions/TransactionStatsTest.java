package edu.cscc.transactions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionStatsTest {

    public static final String THE_MATRIX = "The Matrix";
    public static final String FELLOWSHIP = "Lord of the Rings: The Fellowship of the Ring";

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
        TransactionStats transactionStats = new TransactionStats();
        transactionStats.add(TransactionBuilder.create().withRentalName(THE_MATRIX).build());
        transactionStats.add(TransactionBuilder.create().withRentalName(THE_MATRIX).build());
        transactionStats.add(TransactionBuilder.create().withRentalName(FELLOWSHIP).build());

        assertEquals(3, transactionStats.totalRentals());
        assertEquals(THE_MATRIX, transactionStats.mostPopularMovie());
    }

    @Test
    public void itReturnsTheBestEmployee() {
        String employee1 = "33945809289";
        String employee2 = "33945810408";

        TransactionStats transactionStats = new TransactionStats();
        transactionStats.add(TransactionBuilder.create().withEmployeeId(employee1).build());
        transactionStats.add(TransactionBuilder.create().withEmployeeId(employee2).build());
        transactionStats.add(TransactionBuilder.create().withEmployeeId(employee1).build());

        assertEquals(employee1, transactionStats.bestEmployee());
    }

    @Test
    public void itReturnsTheBestStore() {
        String firstStore = "39458";
        String secondStore = "39123";

        TransactionStats transactionStats = new TransactionStats();
        transactionStats.add(TransactionBuilder.create().withStoreNumber(firstStore).build());
        transactionStats.add(TransactionBuilder.create().withStoreNumber(firstStore).build());
        transactionStats.add(TransactionBuilder.create().withStoreNumber(secondStore).build());

        assertEquals(firstStore, transactionStats.bestStore());
    }
}