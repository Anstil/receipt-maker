package main.java.ru.clevertec.check;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class CSVFileParser implements FileParser {

    @Override
    public List<List<String>> read(String filePath) {
        List<List<String>> fileData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Stream<String> lines = reader.lines().skip(1);
            lines.forEachOrdered(line -> {
                List<String> splitLine = new ArrayList<>(Arrays.asList(line.split(";")));
                fileData.add(splitLine);
            });
        } catch (IOException e) {
//            TO DO logging an error to a file
            e.getStackTrace();
            System.out.println("BAD REQUEST");
        }
        return fileData;
    }
}
