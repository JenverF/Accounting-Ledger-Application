package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
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

    public static void saveTransaction(Transaction t) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/transactions.csv", true));
            bw.write(t.toCSV());
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    public static void displayEntries(List<Transaction> transactions) {
        for(Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
