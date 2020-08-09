package pl.maciejdluzen.hotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {

        return "admin/dashboard";
    }
}
