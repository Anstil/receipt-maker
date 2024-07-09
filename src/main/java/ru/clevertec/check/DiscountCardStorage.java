package main.java.ru.clevertec.check;

import java.util.List;

public class DiscountCardStorage extends AbstractStorage<DiscountCard> {

    DiscountCardStorage(FileParser fileParser, String filePath) {
        super(fileParser, filePath);
    }

    @Override
    protected void fillStorageFromList() {
        for (List<String> row : fileData) {
            storage.put(Long.parseLong(row.get(1)),
                    new DiscountCard(Long.parseLong(row.get(1)),
                            Integer.parseInt(row.get(2))));
        }
    }
}
