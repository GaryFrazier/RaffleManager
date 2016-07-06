package com.fireboxenterprises.rafflemanager;

public class Ticket {
    public String numberOfTickets;
    public String phoneNumber;

    public Ticket(String number, String tickets) {
        this.phoneNumber = number;
        this.numberOfTickets = tickets;
    }
}
