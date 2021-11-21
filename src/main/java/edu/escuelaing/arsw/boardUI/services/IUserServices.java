package edu.escuelaing.arsw.boardUI.services;

import edu.escuelaing.arsw.boardUI.model.User;

public interface IUserServices {
    
    void addUser(User user);

    User getUserByUsername(String username) throws BoardUIServicesException;

}
