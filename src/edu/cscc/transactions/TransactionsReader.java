package edu.cscc.transactions;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * This class reads {@link Transaction}s from the rental_transactions.csv
 * file. Rather than reading the entire file at once, which wouldn't be performant if the file was large, this reads
 * a single record at a time. The TransactionReader itself provides no access to the records. Instead it implements
 * the {@link Iterable} interface and provides access to an {@link Iterator} which implements the read functionality.
 */
public class TransactionsReader implements Iterable<Transaction> {
    private final CSVParser csvParser;
    private final CSVReader csvReader;

    /**
     * Create a new TransactionsReader from the provided {@link FileReader}.
     * @param fileReader
     */
    public TransactionsReader(FileReader fileReader) {
        csvParser = new CSVParserBuilder().withQuoteChar('"').withSeparator(',').build();
        csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).withSkipLines(1).build();
    }

    /**
     * Close the file for reading.
     * @throws IOException If there was a problem closing the file.
     */
    public void close() throws IOException {
        csvReader.close();
    }

    /**
     * Get a new instance of {@link TransactionIterator} to read the transactions file.
     * @return An {@link Iterator}.
     */
    @Override
    public Iterator<Transaction> iterator() {
        return new TransactionIterator(csvReader);
    }

    /**
     * Iterator for reading {@link Transaction}s.
     */
    public class TransactionIterator implements Iterator {

        private CSVReader csvReader;

        public TransactionIterator(CSVReader csvReader) {
            this.csvReader = csvReader;
        }

        /**
         * Indicates if there is another record to be read or not.
         * @return true if another record can be read, false otherwise.
         */
        @Override
        public synchronized boolean hasNext() {
            try {
                return csvReader.peek() != null;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        public synchronized Transaction peek() {
            try {
                String[] peek = csvReader.peek();
                if (peek != null) return ConvertTransaction.convert(peek);
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        /**
         * Read the next transaction.
         * @return A {@link Transaction}, or null if one is not available.
         */
        @Override
        public synchronized Transaction next() {
            try {
                return ConvertTransaction.convert(csvReader.readNext());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
