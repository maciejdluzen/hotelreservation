package pl.maciejdluzen.hotelreservation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloWorldController {

    @GetMapping
    public String helloWorld() {

        return "Hello Maciej";
    }
}
