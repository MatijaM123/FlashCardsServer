package com.example.flashcardsserver.Controller;


import com.example.flashcardsserver.DTO.RegisterRequest;
import com.example.flashcardsserver.Model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @GetMapping()
    public ResponseEntity<List<Card>> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request);
            return ResponseEntity.ok("User registered successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());}
    }

}
