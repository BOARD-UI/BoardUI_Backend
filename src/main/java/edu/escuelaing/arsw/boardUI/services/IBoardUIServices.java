package edu.escuelaing.arsw.boardUI.services;

import java.util.List;

import edu.escuelaing.arsw.boardUI.model.*;

public interface IBoardUIServices {
    
    public List<Room> getUserRooms();
    
    public void createNewRoom();

    public List<File> getRoomFiles();

    public void createNewFile();

    public void createNewRoomPermission();

    public boolean authenticateUser(String username, String password);

    public void createUser();
}
