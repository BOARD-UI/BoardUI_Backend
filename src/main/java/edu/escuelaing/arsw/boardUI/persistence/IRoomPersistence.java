package edu.escuelaing.arsw.boardUI.persistence;

import java.util.List;

import edu.escuelaing.arsw.boardUI.model.File;
import edu.escuelaing.arsw.boardUI.model.Room;

public interface IRoomPersistence {

    void saveRoom(Room room);

    List<Room> loadRoomsByUser(int userID);

    List<File> loadRoomFiles(int roomID);

    Room getRoomByURL(String url);
    
}
