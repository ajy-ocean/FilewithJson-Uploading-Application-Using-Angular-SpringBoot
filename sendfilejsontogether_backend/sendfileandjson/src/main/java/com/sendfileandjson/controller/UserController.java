package com.sendfileandjson.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendfileandjson.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> addUserInfo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userData") String userData
    ) {
        this.logger.info("Adding User Information");
        logger.info("File Name : {}", file.getOriginalFilename());
        // logger.info("user : {}", userData);

        User user = null;
        // Converting String to JSON
        try {
            user = objectMapper.readValue(userData, User.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }

        this.logger.info("User Data is {}", user);
        // return ResponseEntity.ok("Done");
        return ResponseEntity.ok(user);
    }
}
