package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class
App {
    static Scanner scanner = new Scanner(System.in);

    static void main() {
        boolean endProgram = false;
        while(!endProgram) {
            endProgram = displayHomeScreen();
        }

    }
    // Display home screen menu with options
    public static boolean displayHomeScreen() {
        String options = """
                What would you like to do?
                    D) Add Deposit 🍞
                    P) Make Payment(Debit) 💸‍
                    L) Ledger 📄
                    X) Exit  ️✌️☹️
                Enter choice (D, P, L, X)""";
        System.out.println("===== \uD83D\uDCB0 Welcome to your personal Accounting Tracker! \uD83D\uDCB0 =====\n" + options);
        String choice = scanner.nextLine().toUpperCase().trim();
        switch(choice) {
            case "D":
                addTransaction(true);
                break;
            case "P":
                addTransaction(false);
                break;
            case "L":
                Ledger.displayLedgerScreen();
                break;
            case "X":
                System.out.println("Thanks for using your personal Accounting Tracker! Check in later!");
                return true;
            default:
                System.out.println("That's not an option!");
                break;

        }
        return false;
    }
    // Transaction method that works with both deposits and payments
    public static void addTransaction(boolean isDeposit) {
        System.out.println("Welcome! Let's record this transaction: \n");
        System.out.println("Please enter a description: ");
        String description = scanner.nextLine();

        System.out.println("Please enter vendor: ");
        String vendor = scanner.nextLine();

        // If user does not input a number, asks to enter an amount again
        boolean isValid = false;
        while(!isValid) {
            System.out.println("Please enter an amount (ex: 100.00): ");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                isValid = true;

                // Automatically makes amount positive if user inputs a negative number for deposits, if user enters negative number for payments, number stays negative
                amount = Math.abs(amount) * (isDeposit ? 1 : -1);

                // Save transaction to transactions.csv file
                Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
                TransactionManager.saveTransaction(t);
                System.out.println("Transaction saved successfully!");
            } catch (Exception e) {
                System.out.println("Error, not a valid input.");
                scanner.nextLine();
            }
        }
    }
}
