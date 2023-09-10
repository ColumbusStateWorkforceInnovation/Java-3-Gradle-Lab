package edu.cscc.transactions;

public class TransactionConsumer implements Runnable {
    private TransactionStats transactionStats;
    private TransactionProducer transactionProducer;
    private String date;

    public TransactionConsumer(TransactionProducer transactionProducer, String date) {
        this.transactionProducer = transactionProducer;
        this.date = date;
        this.transactionStats = new TransactionStats();
    }

    @Override
    public void run() {
        while (transactionProducer.hasNext()) {
            Transaction transaction = transactionProducer.peek();
            if (transaction != null && date.equals(transaction.getDate())) {
                transactionStats.add(transactionProducer.next());
            }
        }
    }

    public TransactionStats getTransactionStats() {
        return transactionStats;
    }

    public String getDate() {
        return date;
    }
}
