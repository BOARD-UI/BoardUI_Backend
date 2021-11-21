package edu.escuelaing.arsw.boardUI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arsw.boardUI.model.User;
import edu.escuelaing.arsw.boardUI.services.impl.BoardUIServices;

import org.springframework.http.ResponseEntity;

@RestController
public class BoardUIApiController {

    @Autowired
    BoardUIServices bs;

    @RequestMapping(path = "/welcome/newUser", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody User newUser) {
        try {
            System.out.println(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody String username, @RequestBody String password){
        return new ResponseEntity<>(bs.authenticateUser(username, password),HttpStatus.OK);
    }

	
}
