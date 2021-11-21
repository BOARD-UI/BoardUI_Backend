package edu.escuelaing.arsw.boardUI.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.boardUI.model.*;
import edu.escuelaing.arsw.boardUI.services.BoardUIServicesException;

@Service
public class BoardUIServices {

    @Autowired
    RoomServices rs;

    @Autowired
    UserServices us;

    @Autowired
    FileServices fs;

    @Autowired
    PermissionServices ps;

    public List<Room> getUserRooms(){
        return null;
    }

    public void createNewRoom(){}

    public List<File> getRoomFiles(){
        return null;
    }

    public void createNewFile(){}

    public void createNewRoomPermission(){}

    public boolean authenticateUser(String username, String password){
        boolean isAuthenticated = true;
        try {
            isAuthenticated = us.getUserByUsername(username).getPassword().equals(password);
        } catch (BoardUIServicesException e) {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    public void createUser(){}
    
}
