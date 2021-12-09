package edu.escuelaing.arsw.boardUI.persistence;

import edu.escuelaing.arsw.boardUI.model.File;

public interface IFilePersistence {
    
    void saveFile(File file);
    
    void updateContentById(int id, String content);
}
