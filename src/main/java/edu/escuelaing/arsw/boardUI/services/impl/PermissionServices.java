package edu.escuelaing.arsw.boardUI.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.boardUI.model.Permission;
import edu.escuelaing.arsw.boardUI.persistence.IPermissionPersistence;
import edu.escuelaing.arsw.boardUI.services.IPermissionServices;

@Service
public class PermissionServices implements IPermissionServices{

    @Autowired
    IPermissionPersistence pp;

    @Override
    public void savePermission(Permission permission) {
        
        pp.savePermission(permission);
        
    }

    public void removePermission(int roomId, String username){
        pp.removePermission(roomId, username);
    }
    
}
