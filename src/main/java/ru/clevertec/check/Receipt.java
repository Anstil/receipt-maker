package main.java.ru.clevertec.check;

import java.text.DecimalFormat;
import java.util.*;

public final class Receipt {

    private Map<Product, Integer> products;

    private String date;
    private String time;
    private int discountAmount = 0;
    private int wholesaleDiscount = 0;
    private String unitOfAccount = "";
    private double totalWithDiscount = 0;

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void setWholesaleDiscount(int wholesaleDiscount) {
        this.wholesaleDiscount = wholesaleDiscount;
    }

    public void setUnitOfAccount(String unitOfAccount) {
        this.unitOfAccount = unitOfAccount;
    }

    public double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public List<List<String>> getDateAndTimeOfPayment() {
        List<List<String>> dateAndTime = new ArrayList<>();
        dateAndTime.add((Arrays.asList("DATE", "TIME")));
        dateAndTime.add(Arrays.asList(date, time));
        return dateAndTime;
    }

    public List<List<String>> getStringListOfProducts() {
        List<List<String>> listOfProducts = new ArrayList<>();
        listOfProducts.add(Arrays.asList("QTY", "DESCRIPTION", "PRICE", "DISCOUNT", "TOTAL"));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        int i = 1;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            double productPrice = entry.getKey().price();
            int productQuantity = entry.getValue();
            double discount = entry.getKey().isWholesaleProduct()
                    ? Double.parseDouble(decimalFormat.format(productPrice * wholesaleDiscount * 0.01).replaceFirst(",", "."))
                    : Double.parseDouble(decimalFormat.format(productPrice * discountAmount * 0.01).replaceFirst(",", "."));
            double total = Double.parseDouble(decimalFormat.format(productQuantity * (productPrice - discount)).replaceFirst(",", "."));
            listOfProducts.add(new ArrayList<>());
            listOfProducts.get(i).add(String.valueOf(productQuantity).replaceFirst("\\.", ","));
            listOfProducts.get(i).add(entry.getKey().description().replaceFirst("\\.", ","));
            listOfProducts.get(i).add(decimalFormat.format(productPrice).replaceFirst("\\.", ",") + unitOfAccount);
            listOfProducts.get(i).add(decimalFormat.format(discount).replaceFirst("\\.", ",") + unitOfAccount);
            listOfProducts.get(i).add(decimalFormat.format(total).replaceFirst("\\.", ",") + unitOfAccount);
            i++;
        }
        return listOfProducts;
    }

    public List<List<String>> getTotalPrice() {
        List<List<String>> priceResult = new ArrayList<>();
        priceResult.add(Arrays.asList("TOTAL PRICE", "TOTAL DISCOUNT", "TOTAL WITH DISCOUNT"));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double totalPrice = 0;
        double totalDiscount = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            double productPrice = entry.getKey().price();
            int productQuantity = entry.getValue();
            totalPrice += Double.parseDouble(decimalFormat.format(productPrice * productQuantity).replaceFirst(",", "."));
            double discount = entry.getKey().isWholesaleProduct()
                    ? Double.parseDouble(decimalFormat.format(productPrice * wholesaleDiscount * 0.01).replaceFirst(",", "."))
                    : Double.parseDouble(decimalFormat.format(productPrice * discountAmount * 0.01).replaceFirst(",", "."));
            totalDiscount = Double.parseDouble(decimalFormat.format(discount * productQuantity).replaceFirst(",", "."));
        }
        totalWithDiscount = Double.parseDouble(decimalFormat.format(totalPrice - totalDiscount).replaceFirst(",", "."));
        priceResult.add(new ArrayList<>());
        priceResult.get(1).add(decimalFormat.format(totalPrice).replaceFirst("\\.", ",") + unitOfAccount);
        priceResult.get(1).add(decimalFormat.format(totalDiscount).replaceFirst("\\.", ",") + unitOfAccount);
        priceResult.get(1).add(decimalFormat.format(totalWithDiscount).replaceFirst("\\.", ",") + unitOfAccount);
        return priceResult;
    }
}
