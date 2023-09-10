package edu.cscc;

import edu.cscc.transactions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("." + File.separator + "rental_transactions.csv");
            TransactionsReader transactionsReader = new TransactionsReader(fileReader);
            TransactionProducer transactionProducer = new TransactionProducer(transactionsReader);

            List<String> dates = Arrays.asList(
                    "2020-09-07",
                    "2020-09-08",
                    "2020-09-09",
                    "2020-09-10",
                    "2020-09-11",
                    "2020-09-12",
                    "2020-09-13"
            );
            List<TransactionConsumer> transactionConsumers = createTransactionConsumers(transactionProducer, dates);

            List<Thread> threads = createThreads(transactionConsumers);
            threads.forEach(thread -> thread.start());
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            transactionsReader.close();
            printDailyStats(transactionConsumers);

            List<Transaction> allTransactions = getTotalTransactions(transactionConsumers);
            printTotalStats(allTransactions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTotalStats(List<Transaction> allTransactions) {
        getTransactionStats("Total", new TransactionStats(allTransactions));
    }

    private static List<Transaction> getTotalTransactions(List<TransactionConsumer> transactionConsumers) {
        List<Transaction> allTransactions = transactionConsumers
                .stream()
                .map(transactionConsumer -> {
                    return transactionConsumer.getTransactionStats().getTransactions();
                }).reduce(new ArrayList<>(), (accum, item) -> {
                    accum.addAll(item);
                    return accum;
                });
        return allTransactions;
    }

    private static void printDailyStats(List<TransactionConsumer> transactionConsumers) {
        transactionConsumers.forEach(transactionConsumer -> {
            getTransactionStats(transactionConsumer.getDate(), transactionConsumer.getTransactionStats());
        });
    }

    private static List<Thread> createThreads(List<TransactionConsumer> transactionConsumers) {
        List<Thread> threads = transactionConsumers.stream().map(transactionConsumer -> {
            return new Thread(transactionConsumer, transactionConsumer.getDate());
        }).collect(Collectors.toList());
        return threads;
    }

    private static List<TransactionConsumer> createTransactionConsumers(TransactionProducer transactionProducer, List<String> dates) {
        List<TransactionConsumer> transactionConsumers = dates
                .stream()
                .map(date -> new TransactionConsumer(transactionProducer, date))
                .collect(Collectors.toList());
        return transactionConsumers;
    }

    private static void getTransactionStats(String day, TransactionStats stats) {
        System.out.println(day + " stats:");
        System.out.println("Total rentals: " + stats.totalRentals());
        System.out.println("Most popular movie: " + stats.mostPopularMovie());
        System.out.println("Best employee: " + stats.bestEmployee());
        System.out.println("Best store: " + stats.bestStore());
        System.out.println("Gross revenue: " + stats.totalRevenue());
        System.out.println();
    }
}
