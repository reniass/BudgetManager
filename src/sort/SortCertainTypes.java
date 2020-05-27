package sort;

import budgetmanager.Purchase;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortCertainTypes implements Sortable<String, List<Purchase>> {


    private String typeToSort;

    public SortCertainTypes(String typeToSort) {
        this.typeToSort = typeToSort;
    }


    @Override
    public void sort(Map<String, List<Purchase>> map) {

        List<Purchase> concreteTypePurchases = map.get(typeToSort);
        if (concreteTypePurchases.size() > 0) {

            Collections.sort(concreteTypePurchases);

            System.out.printf("%s: %n", this.typeToSort);
            double total = 0;
            for (Purchase purchase : concreteTypePurchases) {
                System.out.printf("%s $%f%n", purchase.getName(), purchase.getPrice());
                total += purchase.getPrice();
            }
            System.out.printf("Total: %f", total);
        } else {
            System.out.println("Purchase list is empty!");
        }


    }
}
