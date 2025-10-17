package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    // Reads the transaction.csv file and formats it split on the pipe
    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        try{
            BufferedReader br = new BufferedReader(new FileReader("files/transactions.csv"));
            br.readLine();

            String transactionString;

            while((transactionString = br.readLine()) != null) {
                String[] transactionArr = transactionString.split("\\|");
                Transaction transaction = new Transaction(LocalDate.parse(transactionArr[0], dtf), LocalTime.parse(transactionArr[1], tf), transactionArr[2], transactionArr[3], Double.parseDouble(transactionArr[4]));
                transactions.add(transaction);
            }
            br.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }

    // Save each transaction to the transactions.csv file
    public static void saveTransaction(Transaction t) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/transactions.csv", true));
            bw.write(t.toCSV());
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    // Displays all the entries in the transactions.csv file
    public static void displayEntries(List<Transaction> transactions) {
        double totalDeposits = 0;
        double totalPayments = 0;

        for(Transaction t : transactions) {
            System.out.println(t);

            double amount = t.getAmount();
            if (amount > 0) {
                totalDeposits += amount;
            } else {
                totalPayments += amount;
            }
        }
        // Calculates the total deposits, payments, and balance
        double total = totalDeposits + totalPayments;
        System.out.printf("""
            ===== Transaction Summary =====
                Total Income 💲: $%.2f
                Total Expenses ➖: $%.2f
                Total Balance 💰: $%.2f \n""", totalDeposits, totalPayments, total);
    }

    // Works with deposits and payments, if it's a deposit it will save it as a positive number, if it's payment, save it as a negative number
    public static void filterByAmount(List<Transaction> transactions, boolean isPositive) {
        for(Transaction t : transactions) {
            if(isPositive && t.getAmount() > 0) {
                System.out.println(t);
            } else if(!isPositive && t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }

    // Being able to search by the vendor to look up transactions from that vendor
    public static List<Transaction> searchByVendor(List<Transaction> transactions, String vendor) {
        List<Transaction> filteredReports = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
                filteredReports.add(t);
            }
        }
        return filteredReports;
    }

    // Being able to search by dates including month/year to date and previous month/year
    public static List<Transaction> searchByDate(List<Transaction> transactions, LocalDate start, LocalDate end) {
        List<Transaction> filteredReports = new ArrayList<>();

        for(Transaction t : transactions) {
            if((t.getDate().isAfter(start) || t.getDate().isEqual(start)) && (t.getDate().isBefore(end) || t.getDate().isEqual(end))) {
                filteredReports.add(t); // Includes the first and last days of the month
            }
        }
        return filteredReports;
    }
}
