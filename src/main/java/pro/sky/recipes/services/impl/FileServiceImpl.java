package pro.sky.recipes.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipes.services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {

    @Value("${path.to.files}")
    private String filePath;


    @Override
    public boolean saveToFile(String json, String fileName) {
        try {
            cleanFile(fileName);
            Files.writeString(Path.of(filePath, fileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(filePath, fileName));
        } catch (NoSuchFileException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public File getDatafile(String fileName){
        return new File(filePath + "/" + fileName);
    }

    @Override
    public boolean cleanFile(String fileName) {
        try {
            Path path = Path.of(filePath, fileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
