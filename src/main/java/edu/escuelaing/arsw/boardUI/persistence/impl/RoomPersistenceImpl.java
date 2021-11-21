package edu.escuelaing.arsw.boardUI.persistence.impl;

import edu.escuelaing.arsw.boardUI.model.File;
import edu.escuelaing.arsw.boardUI.model.Room;
import edu.escuelaing.arsw.boardUI.persistence.repo.IRoomRepo;
import edu.escuelaing.arsw.boardUI.persistence.IRoomPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@ComponentScan({ "edu.escuelaing.ars.boardUI.persistence.repo" })
public class RoomPersistenceImpl implements IRoomPersistence {

    @Autowired
    IRoomRepo ro;

    @PersistenceContext
    EntityManager manager;

    public RoomPersistenceImpl() {
    }

    @Override
    public void saveRoom(Room room) {
        ro.save(room);
    }

    @Override
    public List<Room> loadRoomsByUser(int userID) {
        List<Room> rooms = new LinkedList<>();
        Query query = manager.createNativeQuery("SELECT x.room_id, title, num_members, url FROM rooms x JOIN permissions y ON x.room_id = y.room_id WHERE user_id = ?", Room.class);
        query.setParameter(1, userID);
        for(Object room: query.getResultList()){
            rooms.add((Room) room);
        }
        return rooms;
    }

    @Override
    public List<File> loadRoomFiles(int roomID) {
        List<File> files = new LinkedList<>();
        Query query = manager.createNativeQuery("SELECT * from files WHERE room_id = ?", File.class);
        query.setParameter(1, roomID);  
        for(Object file: query.getResultList()){
            files.add((File) file);
        }      
        return files;
    }

    @Override
    public Room getRoomByURL(String url) {
        Query query = manager.createNativeQuery("SELECT * from rooms WHERE url = ?", Room.class);
        query.setParameter(1, url);
        return (Room) query.getSingleResult();
    }

}
