package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckRunner {
    public static void main(String[] args) {

        Map<Product, Integer> productList = new HashMap<>();
        ProductStorage productStorage = new ProductStorage(new CSVFileParser(), "src/main/resources/products.csv");
        ReceiptConfiguration receiptConfiguration = new ReceiptConfiguration();
        CSVFileCreator csvFileCreator = new CSVFileCreator();

        String fileName = "result";
        long discountId = 0;
        double balanceDebitCard = 0;
        for (String arg : args) {
            if (arg.matches("\\d+-\\d+")) {
                List<String> entry = Arrays.asList(arg.split("-"));
                productList.put(productStorage.getStorage().get(Long.parseLong(entry.get(0))), Integer.parseInt(entry.get(1)));
            } else if (arg.matches("discountCard=\\d+")) {
                discountId = Long.parseLong(arg.replaceAll("\\D+", ""));
            } else if (arg.matches("balanceDebitCard=\\d+")) {
                balanceDebitCard = Double.parseDouble(arg.replaceAll("\\D+", ""));
            } else {
                try (FileWriter csvWriter = new FileWriter(fileName + ".csv")) {
                    csvWriter.append("ERROR\n");
                    csvWriter.append("BAD REQUEST");
                    System.exit(0);
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }

        Receipt receipt = receiptConfiguration.getReceipt(productList, discountId, new CSVFileParser());
        if (balanceDebitCard < receipt.getTotalWithDiscount()) {
            try (FileWriter writer = new FileWriter(fileName + ".csv")) {
                writer.append("ERROR\n");
                writer.append("NOT ENOUGH MONEY");
                System.exit(0);
            } catch (IOException e) {
                e.getStackTrace();
            }
        }

        csvFileCreator.writeFile(fileName, receipt.getDateAndTimeOfPayment(), false);
        csvFileCreator.writeFile(fileName, receipt.getStringListOfProducts(), true);
        csvFileCreator.writeFile(fileName, receipt.getTotalPrice(), true);
        System.out.println(receipt.getDateAndTimeOfPayment());
        System.out.println(receipt.getStringListOfProducts());
        System.out.println(receipt.getTotalPrice());
    }
}
