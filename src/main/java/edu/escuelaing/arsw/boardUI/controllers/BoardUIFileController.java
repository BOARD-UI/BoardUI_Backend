package edu.escuelaing.arsw.boardUI.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import org.springframework.http.HttpStatus;

@RestController
public class BoardUIFileController {
    
    @CrossOrigin
    @RequestMapping(path = "/test/{roomId}/{fileId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> getFile(@PathVariable int roomId, @PathVariable String fileId) {
        try {
            File file = new File("./src/main/resources/"+roomId+"/"+fileId);
            if (file.exists()){
                
            }
            return new ResponseEntity<>(HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}
}
