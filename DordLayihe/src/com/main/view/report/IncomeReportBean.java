
package com.main.view.report;

import java.time.LocalDate;

 
public class IncomeReportBean {
    private int id;
    private String note;
    private double amount;
    private String category;
    private LocalDate date;

    public IncomeReportBean(int id, String note, double amount, String category, LocalDate date) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
