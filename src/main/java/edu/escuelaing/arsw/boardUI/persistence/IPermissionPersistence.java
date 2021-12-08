package edu.escuelaing.arsw.boardUI.persistence;

import edu.escuelaing.arsw.boardUI.model.Permission;

public interface IPermissionPersistence {

    void savePermission(Permission permission);
    
    void removePermission(int roomId, String username);
}
