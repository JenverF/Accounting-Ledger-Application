package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Report {
    static Scanner scanner = new Scanner(System.in);

    public static boolean displayReports() {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while(true) {
            String options = """
                What do you want to search by:
                    1) Month to Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Vendor
                    0) Back - Go back to ledger page
                H) Home - Go back to home page""";
            System.out.println("Report options: \n" + options);
            String choice = scanner.nextLine().toUpperCase();
            switch(choice) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "0":
                    return false;
                case "H":
                    return true;
                default:
                    System.out.println("That's not an option!");
                    break;
            }
        }
    }
}
