package edu.escuelaing.arsw.boardUI.services;

import java.util.List;

import edu.escuelaing.arsw.boardUI.model.File;
import edu.escuelaing.arsw.boardUI.model.Room;

public interface IRoomServices {

    void saveRoom(Room room);

    List<Room> loadRoomsByUser(int userID);

    List<File> loadRoomFiles(int roomID);

    Room getRoomByURL(String url);
}
