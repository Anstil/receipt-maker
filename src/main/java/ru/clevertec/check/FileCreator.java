package main.java.ru.clevertec.check;

import java.util.List;

public interface FileCreator {

    void create(String fileName, List<List<String>> fileData);
}
