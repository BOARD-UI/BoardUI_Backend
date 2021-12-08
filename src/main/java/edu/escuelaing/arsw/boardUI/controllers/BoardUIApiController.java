package edu.escuelaing.arsw.boardUI.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.boardUI.model.*;
import edu.escuelaing.arsw.boardUI.services.impl.BoardUIServices;

import org.springframework.http.ResponseEntity;

@RestController
public class BoardUIApiController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    BoardUIServices bs;

    @CrossOrigin
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        try {
            bs.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println("k" + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> body){
        return new ResponseEntity<>(bs.authenticateUser(body.get("username"), body.get("password")),HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/user/{username}/rooms", method = RequestMethod.GET)
    public ResponseEntity<?> getRooms(@PathVariable String username) {
        try {
            return new ResponseEntity<>(bs.getUserRooms(username), HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/room/{roomId}/files", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomFiles(@PathVariable int roomId) {
        try {
            return new ResponseEntity<>(bs.getRoomFiles(roomId), HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/room/permission", method = RequestMethod.POST)
    public ResponseEntity<?> createRoomAccessPermission(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String roomUrl = body.get("roomUrl");
        System.out.println("\nSi entra\n");
        System.out.println("\n"+username+"\n");
        try {
            bs.createRoomAccessPermission(roomUrl, username);
            return new ResponseEntity<>(HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/room/{roomId}/permission/remove", method = RequestMethod.POST)
    public ResponseEntity<?> removeRoomAccessPermission(@PathVariable int roomId, @RequestBody Map<String, String> body) {
        String username = body.get("username");
        try {
            bs.removeRoomAccessPermission(roomId, username);
            return new ResponseEntity<>(HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/room/{roomId}/file", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> createNewFile(@RequestParam("file") MultipartFile file, @RequestParam("extension") String extension, @PathVariable int roomId) {
        try {
            bs.createNewFile(file, extension, roomId);
            return new ResponseEntity<>(HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}

    @CrossOrigin
    @RequestMapping(path = "/user/{username}/rooms", method = RequestMethod.POST)
    public ResponseEntity<?> createNewRoom(@RequestBody Room room, @PathVariable("username") String username) {
        try {
            bs.createNewRoom(room, username);
            bs.createRoomAccessPermission(username+"#"+room.gettitle(), username);
            return new ResponseEntity<>(HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/beans", method = RequestMethod.GET)
    public ResponseEntity<?> getBeans() {
        return new ResponseEntity<>(context.getBeanDefinitionNames() ,HttpStatus.OK);  
    }
	
}
