package edu.escuelaing.arsw.boardUI.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.escuelaing.arsw.boardUI.model.Permission;

public interface IPermissionRepo extends JpaRepository <Permission, Integer>{
    void deleteByPermissionId(int permissionId);
}
