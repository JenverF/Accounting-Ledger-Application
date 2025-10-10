package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
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
                if(transactionArr.length != 5) {
                    System.out.println("This doesn't appear to have 5 parts");
                    continue;
                }
                Transaction transaction = new Transaction(LocalDate.parse(transactionArr[0], dtf), LocalTime.parse(transactionArr[1], tf), transactionArr[2], transactionArr[3], Double.parseDouble(transactionArr[4]));
                transactions.add(transaction);
            }
            br.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }

    public static void addDeposit() {
        System.out.println("Enter description for your deposit: ");


    }
}
