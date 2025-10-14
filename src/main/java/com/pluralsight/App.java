package com.pluralsight;

import java.util.List;
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
        System.out.println("Welcome to your personal Finance Tracker!\n" + options);
        String choice = scanner.nextLine();
        switch(choice) {
            case "D":
                TransactionManager.addTransaction();
                break;
            case "P":
                TransactionManager.addPayment();
                break;
            case "L":
//                Ledger.displayLedgerScreen();
            case "X":
                return true;
            default:
                System.out.println("That's not an option!");
                break;

        }
        return false;
    }
}
