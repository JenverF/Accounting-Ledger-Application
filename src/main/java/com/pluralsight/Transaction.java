package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    @Override
    public String toString() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }

    public String toCSV() {
        // DateTimeFormatter to format the date and time, also formats the amount to round it to the hundredths, formats the inputted transactions
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "\n" + date.format(dtf) + "|" + time.format(tf) + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

}
