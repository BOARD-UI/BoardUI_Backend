package edu.escuelaing.arsw.boardUI.persistence.impl;

import edu.escuelaing.arsw.boardUI.model.File;
import edu.escuelaing.arsw.boardUI.persistence.repo.IFileRepo;
import edu.escuelaing.arsw.boardUI.persistence.IFilePersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@ComponentScan({ "edu.escuelaing.ars.boardUI.persistence.repo" })
public class FilePersistenceImpl implements IFilePersistence{
    @Autowired
    IFileRepo ro;

    @PersistenceContext
    EntityManager manager;

    @Override
    public void saveFile(File file) {
        ro.save(file); 
    }

}
