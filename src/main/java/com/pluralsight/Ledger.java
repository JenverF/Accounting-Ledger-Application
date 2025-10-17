package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Ledger {
    static Scanner scanner = new Scanner(System.in);

    // Display ledger menu screen with each options
    public static void displayLedgerScreen() {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while (true) {
            String options = """
                    Display..
                        A) All - Display all entries 👀
                        D) Deposits 🍞
                        P) Payments 💸
                        R) Reports 📈
                        H) Home - Go back to home page
                    Enter choice (A, D, P, R, H)""";
            System.out.println("===== Ledger ===== \n" + options);
            String choice = scanner.nextLine().toUpperCase().trim();
            switch (choice) {
                case "A":
                    System.out.println("===== \uD83D\uDCAF Here are all the transactions \uD83D\uDCAF ===== ");
                    TransactionManager.displayEntries(transactions);
                    break;
                case "D":
                    System.out.println("===== ➕ Here are all the deposits ➕ ===== ");
                    TransactionManager.filterByAmount(transactions, true);
                    break;
                case "P":
                    System.out.println("===== ➖ Here are all the payments ➖ ===== ");
                    TransactionManager.filterByAmount(transactions, false);
                    break;
                case "R":
                    // If user inputs H in report screen menu, it will take the user back to the home screen
                    boolean goHome = Report.displayReports();
                    if(goHome) {
                        return;
                    }
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
