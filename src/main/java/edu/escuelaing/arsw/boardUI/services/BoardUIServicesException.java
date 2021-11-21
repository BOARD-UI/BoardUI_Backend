package edu.escuelaing.arsw.boardUI.services;

public class BoardUIServicesException extends Exception {

    public static final String USER_NOT_FOUND = "User was not found";
    public static final String USER_ALREADY_EXISTS= "User already exists";
    public static final String ROOM_NOT_FOUND = "Room was not found";
    
    public BoardUIServicesException(String message) {
        super(message);
    }

    public BoardUIServicesException(String message, Throwable cause) {
        super(message, cause);
    }
}
