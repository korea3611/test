package com.example.test.async.controller;

import com.example.test.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping("/async")
    public String goAsync() {
        for(int i=1;i<100;i++) {
            asyncService.onAsync(i);
        }

        String str = "Hello Spring Boot Async!!";
        return str;
    }

    @GetMapping("/save-data")
    public String saveData() {
        for(int i=1;i<100;i++) {
            asyncService.save(i);
        }

        String str = "success save data";
        return str;
    }
}
