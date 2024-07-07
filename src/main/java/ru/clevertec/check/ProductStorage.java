package main.java.ru.clevertec.check;

import java.util.List;
import java.util.Objects;

public class ProductStorage extends AbstractStorage<Product> {

    ProductStorage(FileParser fileParser, String filePath) {
        super(fileParser, filePath);
    }

    @Override
    protected void fillStorageFromList() {
        for (List<String> row : fileData) {
            storage.put(Long.parseLong(row.get(0)),
                    new Product(Long.parseLong(row.get(0)),
                            row.get(1),
                            Double.parseDouble(row.get(2).replaceFirst(",", ".")),
                            Integer.parseInt(row.get(3)),
                            isWholesaleProduct(row.get(4))));
        }
    }

    private boolean isWholesaleProduct(String value) {
        return Objects.equals(value, "+");
    }
}
