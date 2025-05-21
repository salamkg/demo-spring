package com.example.demo.booking;

public class Flight {

    //ассоциация
    Airplane airplane;
    Airport airport;

    public Flight(Airplane airplane, Airport airport) {
        this.airplane = airplane;
        this.airport = airport;
    }

//    public reserveSeat() {
//
//    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}
