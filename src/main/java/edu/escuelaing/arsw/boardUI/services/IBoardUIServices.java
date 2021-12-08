package edu.escuelaing.arsw.boardUI.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.boardUI.model.*;

public interface IBoardUIServices {
    
    public String authenticateUser(String username, String password);

    public void createUser(User user) throws BoardUIServicesException;

    public List<Room> getUserRooms(String username) throws BoardUIServicesException;
    
    public void createNewRoom(Room room, String username) throws BoardUIServicesException;

    public void createRoomAccessPermission(String roomUrl, String username) throws BoardUIServicesException;

    public List<File> getRoomFiles(int roomId) throws BoardUIServicesException;

    public void createNewFile(MultipartFile file, String extension, int roomId);
    
}
