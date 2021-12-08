package edu.escuelaing.arsw.boardUI.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import edu.escuelaing.arsw.boardUI.model.Permission;
import edu.escuelaing.arsw.boardUI.persistence.IPermissionPersistence;
import edu.escuelaing.arsw.boardUI.persistence.repo.IPermissionRepo;

@Repository
@ComponentScan({ "edu.escuelaing.ars.boardUI.persistence.repo" })
public class PermissionPersistenceImpl implements IPermissionPersistence{

    @Autowired
    IPermissionRepo ro;

    @PersistenceContext
    EntityManager manager;

    @Override
    public void savePermission(Permission permission) {
        ro.save(permission);
    }
    
    @Transactional
    public void removePermission(int roomId, String username){
        Query query = manager.createNativeQuery("SELECT * FROM permissions WHERE room_id = ? AND user_id = (SELECT user_id FROM users WHERE username = ?)", Permission.class);
        query.setParameter(1, roomId); 
        query.setParameter(2, username); 
        Permission toDelete = (Permission) query.getSingleResult();
        ro.deleteByPermissionId(toDelete.getPermissionId());
    }

}
