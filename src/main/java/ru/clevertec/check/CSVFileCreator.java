package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileCreator implements FileCreator {

    @Override
    public void createFile(String fileName, List<List<String>> fileData) {
        try (FileWriter csvWriter = new FileWriter(fileName)) {
            for (List<String> rowData : fileData) {
                csvWriter.append(String.join(";", rowData));
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
