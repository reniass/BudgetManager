package sort;

import budgetmanager.Purchase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortByType implements Sortable<String, List<Purchase>> {

    @Override
    public void sort(Map<String, List<Purchase>> map) {
        // purchase meaning is different, purchase.getName() return type of purchase(food, clothes ...), purchase.getPrice() return price of all that products
        List<Purchase> list = new ArrayList<>();


        for (String key : map.keySet()) {
            // price of all products that type
            double price = 0;
            List<Purchase> concreteTypePurchases = map.get(key);
            for (Purchase purchase : concreteTypePurchases) {
                price += purchase.getPrice();
            }

            list.add(new Purchase(key, price));
        }

        Collections.sort(list);

        double total = 0;
        System.out.println("Types: ");
        for (Purchase purchase : list) {
            System.out.printf("%s - $%f%n", purchase.getName(), purchase.getPrice());
            total += purchase.getPrice();
        }
        System.out.printf("Total sum: $%f", total);
    }
}
