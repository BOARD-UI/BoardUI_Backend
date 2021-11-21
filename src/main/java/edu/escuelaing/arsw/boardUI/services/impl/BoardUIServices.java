package edu.escuelaing.arsw.boardUI.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.boardUI.model.*;
import edu.escuelaing.arsw.boardUI.services.BoardUIServicesException;
import edu.escuelaing.arsw.boardUI.services.IBoardUIServices;

@Service
public class BoardUIServices implements IBoardUIServices{

    @Autowired
    RoomServices rs;

    @Autowired
    UserServices us;

    @Autowired
    FileServices fs;

    @Autowired
    PermissionServices ps;

    @Override
    public List<Room> getUserRooms(String username) throws BoardUIServicesException{
        List<Room> rooms = new LinkedList<Room>();
        User currentUser = us.getUserByUsername(username);
        rooms = rs.loadRoomsByUser(currentUser.getId());
        return rooms;
    }

    @Override
    public void createNewRoom(Room room, String username) throws BoardUIServicesException{
        room.setURL(username+"#"+room.gettitle());
        rs.saveRoom(room);
    }

    @Override
    public List<File> getRoomFiles(int roomId) throws BoardUIServicesException{
        return rs.loadRoomFiles(roomId);
    }

    @Override
    public void createNewFile(MultipartFile file, String extension, int roomId){
        if(!file.isEmpty()){
            File newFile = new File();
            try {
                String line, content ="";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                while( (line = bufferedReader.readLine()) != null ) content += line+"\n";
                newFile.setName(file.getOriginalFilename());
                newFile.setExtension(extension);
                newFile.setContent(content);
                newFile.setRoomId(roomId);
                newFile.setRequestId(1);
            } catch (Exception e) {}
            fs.addFile(newFile);
        }
    }

    @Override
    public void createRoomAccessPermission(String roomUrl, int userId){
        Room currentRoom = rs.getRoomByURL(roomUrl);
        Permission permission = new Permission();
        permission.setRoomId(currentRoom.getRoomId());
        permission.setUserId(userId);
        permission.setType("Access");
        ps.savePermission(permission);
    }

    @Override
    public boolean authenticateUser(String username, String password){
        boolean isAuthenticated = true;
        try {
            isAuthenticated = us.getUserByUsername(username).getPassword().equals(password);
        } catch (BoardUIServicesException e) {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    @Override
    public void createUser(User user) throws BoardUIServicesException{
        us.addUser(user);
    }
    
}
