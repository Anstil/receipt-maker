package main.java.ru.clevertec.check;

import java.util.List;

public interface FileParser {

    List<List<String>> read(String filePath);
}
