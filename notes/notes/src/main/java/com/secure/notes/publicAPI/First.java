package com.secure.notes.publicAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class First {

    @GetMapping("first")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/second")
    public String helloWorld2() {
        return "Hello World 2";
    }
}
