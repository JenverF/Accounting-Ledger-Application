package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);

    static void main() {
        boolean endProgram = false;
        while(!endProgram) {
            endProgram = displayHomeScreen();
        }

    }
    public static boolean displayHomeScreen() {
        String options = """
                D) Add Deposit
                P) Make Payment(Debit)
                L) Ledger
                X) Exit""";
        System.out.println("Welcome to your personal Accounting Tracker!\n" + options);
        String choice = scanner.nextLine().toUpperCase();
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

    public static void addTransaction(boolean isDeposit) {
        System.out.println("Please enter a description: ");
        String description = scanner.nextLine();

        System.out.println("Please enter vendor: ");
        String vendor = scanner.nextLine();

        boolean isValid = false;
        while(!isValid) {
            System.out.println("Please enter an amount (ex: 100.00): ");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                isValid = true;

                if (!isDeposit) amount *= -1;
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
