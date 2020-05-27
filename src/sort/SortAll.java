package sort;

import budgetmanager.Purchase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortAll implements Sortable<String, List<Purchase>> {

    @Override
    public void sort(Map<String, List<Purchase>> map) {
        List<Purchase> allPurchases = new ArrayList<>();


        for (String key : map.keySet()) {
            List<Purchase> concreteTypePurchases = map.get(key);
            for (Purchase purchase : concreteTypePurchases) {
                allPurchases.add(purchase);
            }
        }

        Collections.sort(allPurchases);

        System.out.println("All:");
        double total = 0;
        for (Purchase purchase: allPurchases) {
            System.out.printf("%s $%f%n", purchase.getName(), purchase.getPrice());
            total += purchase.getPrice();
        }
        System.out.printf("Total: %f", total);

    }
}
