package com.samarth.assessment03;

public class ticketbooking {

    private String stageEvent;
    private String customer;
    private Integer noOfSeats;

    // Default constructor
    public ticketbooking() {
    }

    // Parameterized constructor
    public ticketbooking(String stageEvent, String customer, Integer noOfSeats) {
        this.stageEvent = stageEvent;
        this.customer = customer;
        this.noOfSeats = noOfSeats;
    }

    public String getStageEvent() {
        return stageEvent;
    }

    public void setStageEvent(String stageEvent) {
        this.stageEvent = stageEvent;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    // Cash payment
    public void makePayment(Double amount) {
        System.out.println("Stage event:" + stageEvent);
        System.out.println("Customer:" + customer);
        System.out.println("Number of seats:" + noOfSeats);
        System.out.printf("Amount %.1f paid in cash%n", amount);
    }

    // Wallet payment
    public void makePayment(Double amount, String walletNumber) {
        System.out.println("Stage event:" + stageEvent);
        System.out.println("Customer:" + customer);
        System.out.println("Number of seats:" + noOfSeats);
        System.out.printf("Amount %.1f paid using wallet number %s%n",
                amount, walletNumber);
    }

    // Credit card payment
    public void makePayment(Double amount, String cardType,
                            String name, String ccv) {
        System.out.println("Stage event:" + stageEvent);
        System.out.println("Customer:" + customer);
        System.out.println("Number of seats:" + noOfSeats);
        System.out.println("Holder name:" + name);
        System.out.printf("Amount %.1f paid using %s card%n",
                amount, cardType);
        System.out.println("CCV:" + ccv);
    }
}