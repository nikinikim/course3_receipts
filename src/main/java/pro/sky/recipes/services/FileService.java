package pro.sky.recipes.services;

import java.io.File;

public interface FileService {


    boolean saveToFile(String json, String fileName);

    String readFromFile(String fileName);

    File getDatafile(String fileName);

    boolean cleanFile(String fileName);
}
