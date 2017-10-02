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

    /**
     * Create an instance of the counter object without a comment. The comment is set to a
     * blank string
     *
     * @param name
     * @param initialValue
     */
    public Counter(String name, Integer initialValue) {
        date = new Date();
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = this.initialValue;
        comment = "";
    }

    /**
     * Create an instance of the counter object with a comment.
     *
     * @param name
     * @param initialValue
     * @param comment
     */
    public Counter(String name, Integer initialValue, String comment) {
        date = new Date();
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = this.initialValue;
        this.comment = comment;
    }

    /**
     * Sets the name of the counter object
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the counter object
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the current value of the counter object
     *
     * @param currentValue
     */
    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Gets the current value of the counter object
     *
     * @return
     */
    public Integer getCurrentValue() {
        return currentValue;
    }

    /**
     * Set the initial value of the counter object
     *
     * @param initialValue
     */
    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * Gets the initial value of the counter object
     *
     * @return
     */
    public Integer getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the comment of the counter object
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the comment of the counter object
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gets the date of the counter object. It will return the current date, by calling
     * new Date()
     *
     * @return
     */
    public String getDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = formatter.format(date);
        return time;
    }

    /**
     * Converts the object to a string format, by overriding the toString() call. This is
     * how the counter object is displayed in the list view
     *
     * @return
     */
    @Override
    public String toString(){
        return name.toString() + ": " + currentValue.toString() +
                "\nDate: " + getDate();
    }

    /**
     * Resets the current value of the counter object to the initial
     * value of the counter object
     *
     */
    public void reset() {
        this.currentValue = this.initialValue;
    }

    /**
     * Increments the current value of the counter object by one
     *
     */
    public void increment() {
        this.currentValue += 1;
    }

    /**
     * Decrements the current value of the counter object by one, but will
     * not go below zero
     *
     */
    public void decrement() {
        if(this.currentValue > 0) {
            this.currentValue -= 1;
        }
    }
}
