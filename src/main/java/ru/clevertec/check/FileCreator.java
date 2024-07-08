package main.java.ru.clevertec.check;

import java.util.List;

public interface FileCreator {

    void createFile(String fileName, List<List<String>> fileData);
}
