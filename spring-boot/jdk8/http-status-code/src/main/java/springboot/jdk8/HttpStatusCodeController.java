package springboot.jdk8;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class HttpStatusCodeController {
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getHttpStatusRequest(@RequestParam String httpStatusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("timestamp", new Date().toString());
        if (httpStatusCode == null) {
            return new ResponseEntity<>("status: 204 No Content", HttpStatus.NO_CONTENT);
        }
        else if (httpStatusCode.equals("200")) {
            headers.set("status", "200 OK");
            return new ResponseEntity<>(headers.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("status: 400 Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity postHttpStatusRequest() {
        return new ResponseEntity("405 Post Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

}
