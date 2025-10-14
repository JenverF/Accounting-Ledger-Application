package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Ledger {
    static Scanner scanner = new Scanner(System.in);

    // Ledger that displays screen
    public static void displayLedgerScreen() {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while (true) {
            String options = """
                    A) All - Display all entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home - Go back to home page""";
            System.out.println("Ledger: \n" + options);
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    System.out.println("Here are all the transactions: ");
                    TransactionManager.displayEntries(transactions);
                    break;
                case "D":
                    TransactionManager.displayTransactions(transactions, true);
                    break;
                case "P":
                    TransactionManager.displayTransactions(transactions, false);
                    break;
                case "R":
                    // displayReports();
                    break;
                case "H":
                    return;
                default:
                    System.out.println("That's not an option!");
                    break;
            }
        }
    }
}
