package org.api.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("news/api")
public class NewsAPIController {
    @Autowired
    NewsAPIService newsAPIService;

    @GetMapping(produces = "application/json")
    public ResponseEntity fetchNewsAPIData(
            @RequestParam(value="from") @DateTimeFormat(pattern="ddMMyyyy") Date from,
            @RequestParam(value="to")   @DateTimeFormat(pattern="ddMMyyyy") Date to) {
        if (from != null && to != null) {
            Object[] returnArticles = newsAPIService.getFilteredNewsAPIData(from, to);
            if (returnArticles.length == 0) {
                return new ResponseEntity("No Data Found", HttpStatus.CREATED);
            }
            return new ResponseEntity<>(returnArticles, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity postNewsAPIData() {
        return new ResponseEntity("Post HTTP Request Type Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping
    public ResponseEntity putNewsAPIData() {
        return new ResponseEntity("Put HTTP Request Type Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PatchMapping
    public ResponseEntity patchNewsAPIData() {
        return new ResponseEntity("Patch HTTP Request Type Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping
    public ResponseEntity deleteNewsAPIData() {
        return new ResponseEntity("Delete HTTP Request Type Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
