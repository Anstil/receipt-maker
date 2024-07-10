package main.java.ru.clevertec.check;

import java.util.List;

public interface FileCreator {

    void writeFile(String fileName, List<List<String>> fileData, boolean appendData);
}
