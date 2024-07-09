package main.java.ru.clevertec.check;

import java.util.*;

public final class Receipt {

    private Map<Product, Integer> products;

    private String date;
    private String time;
    private int discountAmount = 0;
    private int wholesaleDiscount = 0;
    private String unitOfAccount = "";

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

    public List<String> getDateAndTimeOfPayment() {
        List<String> dateAndTime = new ArrayList<>();
        dateAndTime.add(date);
        dateAndTime.add(time);
        return dateAndTime;
    }

    public List<List<String>> getStringListOfProducts() {
        List<List<String>> listOfProducts = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            double productPrice = entry.getKey().price();
            int productQuantity = entry.getValue();
            double discount = entry.getKey().isWholesaleProduct() ? entry.getKey().price() * wholesaleDiscount * 0.01 : entry.getKey().price() * discountAmount * 0.01;
            listOfProducts.add(new ArrayList<>());
            listOfProducts.get(i).add(String.valueOf(productQuantity));
            listOfProducts.get(i).add(entry.getKey().description());
            listOfProducts.get(i).add(String.valueOf(productPrice).replaceFirst("\\.", ","));
            listOfProducts.get(i).add(String.valueOf(discount).replaceFirst("\\.", ","));
            listOfProducts.get(i).add(String.valueOf(productQuantity * (productPrice - discount)).replaceFirst("\\.", ","));
            i++;
        }
        return listOfProducts;
    }

    public List<String> getPrice() {
        List<String> priceResult = new ArrayList<>();
        double totalPrice = 0;
        double totalDiscount = 0;
        double total;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            double productPrice = entry.getKey().price();
            int productQuantity = entry.getValue();
            totalPrice += productPrice * productQuantity;
            if (entry.getKey().isWholesaleProduct()) {
                totalDiscount += productPrice * productQuantity * wholesaleDiscount * 0.01;
            } else totalDiscount += productPrice * productQuantity * discountAmount * 0.01;
        }
        total = totalPrice - totalDiscount;
        priceResult.add(String.valueOf(totalPrice).replaceFirst("\\.", ",") + unitOfAccount);
        priceResult.add(String.valueOf(totalDiscount).replaceFirst("\\.", ",") + unitOfAccount);
        priceResult.add(total + unitOfAccount);
        return priceResult;
    }
}
