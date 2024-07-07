package main.java.ru.clevertec.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStorage<T> {

    protected Map<Long, T> storage;
    protected List<List<String>> fileData;

    AbstractStorage(FileParser fileParser, String filePath){
        this.fileData = fileParser.readFile(filePath);
        storage = new HashMap<>();
        fillStorageFromList();
    }

    protected abstract void fillStorageFromList();

    public Map<Long, T> getStorage() {
        return storage;
    }
}
