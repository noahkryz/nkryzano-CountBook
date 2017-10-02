package com.example.noahkryzanowski.nkryzano_countbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Counter {
    private String name;
    private Date date;
    private Integer currentValue;
    private Integer initialValue;
    private String comment;

    public Counter(String name, Integer initialValue) {
        date = new Date();
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = this.initialValue;
        comment = "";
    }

    public Counter(String name, Integer initialValue, String comment) {
        date = new Date();
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = this.initialValue;
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    public Integer getInitialValue() {
        return initialValue;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = formatter.format(date);
        return time;
    }

    @Override
    public String toString(){
        return name.toString() + ": " + currentValue.toString() +
                "\nDate: " + getDate();
    }

    public void reset() {
        this.currentValue = this.initialValue;
    }

    public void increment() {
        this.currentValue += 1;
    }

    public void decrement() {
        if(this.currentValue > 0) {
            this.currentValue -= 1;
        }
    }
}
