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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            fs.addFile(newFile);
        }
    }

    @Override
    public void createRoomAccessPermission(String roomUrl, String username) throws BoardUIServicesException{
        try {
            Room currentRoom = rs.getRoomByURL(roomUrl);
            User currentUser = us.getUserByUsername(username);
            Permission permission = new Permission();
            permission.setRoomId(currentRoom.getRoomId());
            permission.setUserId(currentUser.getId());
            permission.setType("Access");
            ps.savePermission(permission);
        } catch (Exception e) {
            throw new BoardUIServicesException("Room not found");
        }
        
    }

    @Override
    public String authenticateUser(String username, String password){
        String userEmail = null;
        try {
            userEmail = us.getUserByUsername(username).getEmail();
        } catch (BoardUIServicesException e) {
            userEmail = null;
        }
        return userEmail;
    }

    @Override
    public void createUser(User user) throws BoardUIServicesException{
        us.addUser(user); 
    }

    public void removeRoomAccessPermission(int roomId, String username){
        ps.removePermission(roomId, username);
    }
    
    public void updateFileContent(String id, String content){
        fs.updateContentById(Integer.parseInt(id) , content);
    }

}
