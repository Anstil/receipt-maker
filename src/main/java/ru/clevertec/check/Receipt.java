package main.java.ru.clevertec.check;

import java.text.DecimalFormat;
import java.util.*;

public final class Receipt {

    private Map<Product, Integer> products;
    List<List<String>> listOfProducts;

    private String date;
    private String time;
    private int discountAmount = 0;
    private int wholesaleDiscount = 0;

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

    public List<String> getDateAndTimeOfPayment() {
        List<String> dateAndTime = new ArrayList<>();
        dateAndTime.add(date);
        dateAndTime.add(time);
        return dateAndTime;
    }

    public List<List<String>> getStringListOfProducts() {
        listOfProducts = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        int i = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            double productPrice = entry.getKey().price();
            int productQuantity = entry.getValue();
            double discount = entry.getKey().isWholesaleProduct()
                    ? Double.parseDouble(decimalFormat.format(productPrice * wholesaleDiscount * 0.01).replaceFirst(",", "."))
                    : Double.parseDouble(decimalFormat.format(productPrice * discountAmount * 0.01).replaceFirst(",", "."));
            double total = Double.parseDouble(decimalFormat.format(productQuantity * (productPrice - discount)).replaceFirst(",", "."));
            listOfProducts.add(new ArrayList<>());
            listOfProducts.get(i).add(String.valueOf(productQuantity));
            listOfProducts.get(i).add(entry.getKey().description());
            listOfProducts.get(i).add(decimalFormat.format(productPrice));
            listOfProducts.get(i).add(decimalFormat.format(discount));
            listOfProducts.get(i).add(String.valueOf(total).replaceFirst("\\.", ","));
            i++;
        }
        return listOfProducts;
    }

    public List<String> getTotalPrice() {
        List<String> priceResult = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double totalPrice = 0;
        double totalDiscount = 0;
        double total;
        for (List<String> product : listOfProducts) {
            totalPrice += Double.parseDouble(product.get(2).replaceFirst(",", ".")) * Integer.parseInt(product.get(0));
            totalDiscount+= Double.parseDouble(product.get(3).replaceFirst(",", ".")) * Integer.parseInt(product.get(0));
        }
        total = totalPrice - totalDiscount;
        priceResult.add(decimalFormat.format(totalPrice));
        priceResult.add(decimalFormat.format(totalDiscount));
        priceResult.add(decimalFormat.format(total));
        return priceResult;
    }
}
