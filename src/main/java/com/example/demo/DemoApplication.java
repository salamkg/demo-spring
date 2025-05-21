package com.example.demo;

import com.example.demo.booking.*;
import com.example.demo.calc.AddOperation;
import com.example.demo.calc.SubtractOperation;
import com.example.demo.document.DocumentFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.example.demo.services")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        Ticket ticket = new Ticket(new Passenger("John Doe"), new Seat(1));
        Flight flight = new Flight(new Airplane("Boeing 775"), new Airport("Airport 1"));


//        TicketFactory ticketFactory = new TicketFactory();
//        ticketFactory.booking(flight, ticket);

        AddOperation addOperation = new AddOperation();
        SubtractOperation subtractOperation = new SubtractOperation();

        System.out.println(addOperation.add(1, 5));
        System.out.println(subtractOperation.subtract(6, 5));

        DocumentFactory documentFactory = new DocumentFactory();
        documentFactory.save("text");
    }
}

