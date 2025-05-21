package com.example.demo.booking;

public class Ticket {

    Passenger passenger; //агрегация
    Seat seat; //композиция

    public Ticket(Passenger passenger, Seat seat) {
        this.passenger = passenger;
        this.seat = seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
