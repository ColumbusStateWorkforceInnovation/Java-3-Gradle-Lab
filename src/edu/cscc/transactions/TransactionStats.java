package edu.cscc.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionStats {

    private List<Transaction> transactions;

    public TransactionStats() {
        this.transactions = new ArrayList<>();
    }

    public TransactionStats(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int totalRentals() {
        return this.transactions.size();
    }

    public void add(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String mostPopularMovie() {
        Map<String, List<Transaction>> rentalsGroupedByName =
                transactions
                        .stream()
                        .collect(Collectors.groupingBy(item -> item.getRentalName()));
        Optional<Map.Entry<String, List<Transaction>>> bestByCount = bestByCount(rentalsGroupedByName);

        return bestByCount.get().getKey();
    }

    public String bestEmployee() {
        Map<String, List<Transaction>> rentalsGroupByEmployee =
                transactions
                        .stream()
                        .collect(Collectors.groupingBy(item -> item.getEmployeeId()));
        Optional<Map.Entry<String, List<Transaction>>> bestByCount = bestByCount(rentalsGroupByEmployee);

        return bestByCount.get().getKey();
    }

    public String bestStore() {
        Map<String, List<Transaction>> rentalsByStore = transactions
                .stream()
                .collect(Collectors.groupingBy(item -> item.getStoreNumber()));
        Optional<Map.Entry<String, List<Transaction>>> bestByCount = bestByCount(rentalsByStore);

        return bestByCount.get().getKey();
    }

    public Double totalRevenue() {
        return transactions
                .stream()
                .map(transaction -> transaction.getRentalCost())
                .reduce(0d, (accum, item) -> accum + item);
    }

    private Optional<Map.Entry<String, List<Transaction>>> bestByCount(Map<String, List<Transaction>> rentalsGroupedByName) {
        Optional<Map.Entry<String, List<Transaction>>> mostPopular =
                rentalsGroupedByName.entrySet()
                        .stream()
                        .sorted((first, second) -> {
            if (first.getValue().size() > second.getValue().size()) {
                return 1;
            } else if (first.getValue().size() < second.getValue().size()) {
                return - 1;
            }

            return 0;
        }).findFirst();
        return mostPopular;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
