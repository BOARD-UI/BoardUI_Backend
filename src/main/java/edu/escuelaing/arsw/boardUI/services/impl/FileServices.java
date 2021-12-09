package edu.escuelaing.arsw.boardUI.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.boardUI.model.File;
import edu.escuelaing.arsw.boardUI.persistence.IFilePersistence;
import edu.escuelaing.arsw.boardUI.services.IFileServices;

@Service
public class FileServices implements IFileServices{
    
    @Autowired
    IFilePersistence rp;

    public FileServices() {}

    @Override
    public void addFile(File file) {
        rp.saveFile(file);
    }

    public void updateContentById(int id, String content){
        rp.updateContentById(id, content);
    }
}
