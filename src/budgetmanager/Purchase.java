package budgetmanager;

public class Purchase implements Comparable{

    private final String name;
    private final double price;

    public Purchase(String purchaseName, double purchasePrice) {
        this.name = purchaseName;
        this.price = purchasePrice;
    }

    @Override
    public int compareTo(Object o) {
        Purchase purchase = (Purchase) o;
        if (this.getPrice() == purchase.getPrice()) {
            return 0;
        } else if (this.getPrice() > purchase.getPrice()){
            return 1;
        } else {
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
