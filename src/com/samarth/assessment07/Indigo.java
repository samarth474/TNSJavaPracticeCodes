package com.samarth.assessment07;

public class Indigo implements Airfare {

    private Integer hours;
    private Double costPerHour;

    // Default constructor
    public Indigo() {
    }

    // Parameterized constructor
    public Indigo(Integer hours, Double costPerHour) {
        this.hours = hours;
        this.costPerHour = costPerHour;
    }

    // Getter for hours
    public Integer getHours() {
        return hours;
    }

    // Setter for hours
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    // Getter for costPerHour
    public Double getCostPerHour() {
        return costPerHour;
    }

    // Setter for costPerHour
    public void setCostPerHour(Double costPerHour) {
        this.costPerHour = costPerHour;
    }

    // Calculate total amount
    @Override
    public double calculateAmount() {
        return hours * costPerHour * 8;
    }
}