package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Report {
    static Scanner scanner = new Scanner(System.in);
    // Display reports screen menu with each option
    public static boolean displayReports() {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while(true) {
            String options = """
                Search by..
                    1) Month to Date 📅
                    2) Previous Month ⏮
                    3) Year to Date 📆
                    4) Previous Year ⏪
                    5) Vendor 🔍
                    0) Back - Go back to ledger page
                H) Home - Go back to home page
                Enter choice (0-6, H)""";
            System.out.println("===== Report options ===== \n" + options);
            String choice = scanner.nextLine().toUpperCase().trim();
            switch(choice) {
                case "1":
                    monthToDateSearch(transactions);

                    break;
                case "2":
                    prevMonthSearch(transactions);

                    break;
                case "3":
                    yearToDateSearch(transactions);

                    break;
                case "4":
                    prevYearSearch(transactions);

                    break;
                case "5":
                    vendorSearch(transactions);

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

    // Made each case into a method for better visibility and easier to read
    // Search by vendor to see all transactions with that vendor name
    public static void vendorSearch(List<Transaction> transactions) {
        System.out.println("Enter Vendor name to search: ");
        String vendorToSearchBy = scanner.nextLine().trim();
        List<Transaction> matchesVendor = TransactionManager.searchByVendor(transactions, vendorToSearchBy);
        TransactionManager.displayEntries(matchesVendor);
        if(matchesVendor.isEmpty()) {
            System.out.println("No transaction(s) from this Vendor.\n");
        }
    }

    // Search by month to date to see all transactions from this month all the way to today
    public static void monthToDateSearch(List<Transaction> transactions) {
        System.out.println("===== Here are this month's transactions ===== ");
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
        List<Transaction> transactionsThisMonth = TransactionManager.searchByDate(transactions, firstDayOfMonth, today);
        TransactionManager.displayEntries(transactionsThisMonth);
    }

    // Search by previous month to see all transactions from the previous month
    public static void prevMonthSearch(List<Transaction> transactions) {
        System.out.println("===== Here are last month's transactions ===== ");
        LocalDate todayDate = LocalDate.now();
        LocalDate lastMonth = todayDate.minusMonths(1);
        LocalDate startLastMonth = LocalDate.of(lastMonth.getYear(), lastMonth.getMonth(), 1);
        LocalDate endLastMonth = LocalDate.of(todayDate.getYear(), todayDate.getMonth(), 1).minusDays(1);
        List<Transaction> transactionsLastMonth = TransactionManager.searchByDate(transactions, startLastMonth, endLastMonth);
        TransactionManager.displayEntries(transactionsLastMonth);
    }
    // Search by year to date to see all transactions from this year to today
    public static void yearToDateSearch(List<Transaction> transactions) {
        System.out.println("===== Here are this year's transactions ===== ");
        LocalDate thisYear = LocalDate.now();
        LocalDate firstMonthOfYear = LocalDate.of(thisYear.getYear(), 1, 1);
        List<Transaction> transactionsThisYear = TransactionManager.searchByDate(transactions, firstMonthOfYear, thisYear);
        TransactionManager.displayEntries(transactionsThisYear);
    }
    // Search by previous year to see all transactions from the previous year
    public static void prevYearSearch(List<Transaction> transactions) {
        System.out.println("===== Here are last year's transactions ====== ");
        LocalDate thisLastYear = LocalDate.now();
        LocalDate startLastYear = LocalDate.of(thisLastYear.getYear() - 1, 1, 1);
        LocalDate endLastYear = LocalDate.of(thisLastYear.getYear() -1, 12, 31);
        List<Transaction> transactionsLastYear = TransactionManager.searchByDate(transactions, startLastYear, endLastYear);
        TransactionManager.displayEntries(transactionsLastYear);
    }
}
