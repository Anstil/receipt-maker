package main.java.ru.clevertec.check;

import java.util.Map;

public class ReceiptConfiguration {

    public Receipt getReceipt(Map<Product, Integer> products, long discountId, FileParser fileParser) {
        Receipt receipt = new Receipt();
        DateAndTimeFormat dateAndTimeFormat = new DateAndTimeFormat();
        DiscountCardStorage discountCardStorage = new DiscountCardStorage(fileParser, "src/main/resources/discountCards.csv");
        receipt.setDate(dateAndTimeFormat.getDateFormats().get("european_format"));
        receipt.setTime(dateAndTimeFormat.getTimeFormats().get("european_format"));
        receipt.setProducts(products);
        if (discountCardStorage.getStorage().containsKey(discountId)) {
            receipt.setDiscountAmount(discountCardStorage.getStorage().get(discountId).discountAmount());
        } else if (discountId != 0) receipt.setDiscountAmount(2);
        receipt.setWholesaleDiscount(10);
        receipt.setUnitOfAccount("$");
        return receipt;
    }
}
