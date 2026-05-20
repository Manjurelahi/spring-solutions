package springboot.jdk21.microservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/date")
public class DateController {

    @GetMapping("/now")
    public ResponseEntity<String> getDate() {
        return ResponseEntity.ok("{\"Date Now\":\"+"+LocalDateTime.now()+"+\"}");
    }
}
