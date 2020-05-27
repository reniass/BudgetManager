package budgetmanager;

import sort.Sortable;

import java.io.*;
import java.util.*;

public class BudgetManager {

    private double income;
    private double expenses;
    private double balance;
    private Scanner scanner;

    private Map<String, List<Purchase>> purchaseMap;

    private Sortable<String, List<Purchase>> sortStrategy;

    public BudgetManager() {
        this.income = 0;
        this.expenses = 0;
        this.balance = 0;
        this.purchaseMap = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addIncome(double income) {
        if (income <= 0) {
            System.out.println("You can't add income less or equal zero!");
        } else {
            this.income += income;
            this.balance += income;
            System.out.println("Income was added!\n");
        }
    }

    public void addPurchase(String purchasesType, Purchase newPurchase) {
        if (newPurchase.getPrice() <= this.balance) {

            switch (purchasesType) {
                case "Food":
                case "Clothes":
                case "Entertainment":
                case "Other":
                    if (this.purchaseMap.get(purchasesType) == null) {
                        this.purchaseMap.put(purchasesType, new ArrayList<>());
                    }
                    this.purchaseMap.get(purchasesType).add(newPurchase);
                    System.out.println("Purchase was added!\n");
                    this.expenses += newPurchase.getPrice();
                    this.balance -= newPurchase.getPrice();
                    break;
            }

        } else {
            System.out.println("You can't buy this product, because you don't have enough money\n");
        }
    }

//    public void showPurchaseList() {
//        if (this.purchaseList.size() == 0) {
//            System.out.println("Purchase list is empty\n");
//        } else {
//            for (Purchase purchase : purchaseList) {
//                System.out.println(purchase.getName() + " $" + purchase.getPrice());
//            }
//            System.out.println("Total sum: $" + this.expenses + "\n");
//        }
//    }

    public void showPurchaseList(String purchasesType) {
        double totalExpenses = 0;
        if (purchasesType.equals("All")) {
            System.out.println("All: ");
            for (String key: this.purchaseMap.keySet()) {
                List<Purchase> listOfSpecificPurchase = this.purchaseMap.get(key);
                for(Purchase purchase : listOfSpecificPurchase) {
                    System.out.println(purchase.getName() + " $" + purchase.getPrice());
                    totalExpenses += purchase.getPrice();
                }
            }
            System.out.println("Total sum: $ " + totalExpenses + "\n");

        } else {
            if (this.getPurchaseMap().get(purchasesType) == null) {
                System.out.println("Purchase list is empty!\n");
            } else {
                switch (purchasesType) {
                    case "Food":
                        List<Purchase> listOfFood = this.purchaseMap.get("Food");
                        if (listOfFood.size() == 0) {
                            System.out.println("Purchase list is empty!");
                        } else {
                            System.out.println("Food: ");
                            for(Purchase food : listOfFood) {
                                System.out.println(food.getName() + " $" + food.getPrice());
                                totalExpenses += food.getPrice();
                            }
                            System.out.println("Total sum: $ " + totalExpenses + "\n");
                        }
                        break;
                    case "Clothes":
                        List<Purchase> listOfClothes = this.purchaseMap.get("Clothes");
                        if (listOfClothes.size() == 0) {
                            System.out.println("Purchase list is empty!");
                        } else {
                            System.out.println("Clothes: ");
                            for(Purchase clothes : listOfClothes) {
                                System.out.println(clothes.getName() + " $" + clothes.getPrice());
                                totalExpenses += clothes.getPrice();
                            }
                            System.out.println("Total sum: $ " + totalExpenses + "\n");
                        }
                        break;
                    case "Entertainment":
                        List<Purchase> listOfEntertainment = this.purchaseMap.get("Entertainment");
                        if (listOfEntertainment.size() == 0) {
                            System.out.println("Purchase list is empty!");
                        } else {
                            System.out.println("Entertainment: ");
                            for(Purchase entertainment : listOfEntertainment) {
                                System.out.println(entertainment.getName() + " $" + entertainment.getPrice());
                                totalExpenses += entertainment.getPrice();
                            }
                            System.out.println("Total sum: $ " + totalExpenses + "\n");
                        }
                        break;
                    case "Other":
                        List<Purchase> listOfOther = this.purchaseMap.get("Other");
                        if (listOfOther.size() == 0) {
                            System.out.println("Purchase list is empty!");
                        } else {
                            System.out.println("Other: ");
                            for(Purchase other : listOfOther) {
                                System.out.println(other.getName() + " $" + other.getPrice());
                                totalExpenses += other.getPrice();
                            }
                            System.out.println("Total sum: $ " + totalExpenses + "\n");
                        }
                        break;
                }
            }
        }
    }

    public void showBalance() {
        System.out.println("Balance: $" + this.balance + "\n");
    }

    public Map<String, List<Purchase>> getPurchaseMap() {
        return purchaseMap;
    }

    public void save(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // saving income, expenses and balance values as a string
            bufferedWriter.write("income: $" + String.valueOf(this.income) + "\n");
            bufferedWriter.write("expenses: $" + String.valueOf(this.expenses) + "\n");
            bufferedWriter.write("balance: $" + String.valueOf(this.balance) + "\n\n");


            // saving purchases

            for (String purchaseType: purchaseMap.keySet()) {
                bufferedWriter.write(purchaseType + ":\n");
                List<Purchase> listOfPurchases = this.purchaseMap.get(purchaseType);
                for (Purchase purchase : listOfPurchases) {
                    bufferedWriter.write(purchase.getName() + " $" + purchase.getPrice() + "\n");
                }
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();

            System.out.println("Purchases were saved!\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(File file) {
        if (file.length() != 0) {
            try {
                this.purchaseMap = new HashMap<>();

                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);

                // reading income and set
                String line1 = bufferedReader.readLine();
                String[] line1ToArray = line1.split(" ");
                double income = Double.valueOf(line1ToArray[1].substring(1));
                setIncome(income);

                // reading expenses and set
                String line2 = bufferedReader.readLine();
                String[] line2ToArray = line2.split(" ");
                double expenses = Double.valueOf(line2ToArray[1].substring(1));
                setExpenses(expenses);

                // reading balance and set
                String line3 = bufferedReader.readLine();
                String[] line3ToArray = line3.split(" ");
                double balance = Double.valueOf(line3ToArray[1].substring(1));
                setBalance(balance);

                bufferedReader.readLine();

                // reading purchases and adding them to purchaseMap
                String line;
                String purchaseType = null;
                while ((line = bufferedReader.readLine()) != null) {

                    if (line.equals("Food:") ||
                        line.equals("Clothes:") ||
                        line.equals("Entertainment:") ||
                        line.equals("Other:")) {
                        purchaseType = line.substring(0, line.length() - 1);

                    } else if (!line.equals("")) {
                        String[] lineToArray = line.split(" ");
                        String name = lineToArray[0];
                        double price = Double.valueOf(lineToArray[1].substring(1));
                        switch (purchaseType) {
                            case "Food":
                                if (this.purchaseMap.get("Food") == null) {
                                    this.purchaseMap.put(purchaseType, new ArrayList<>());
                                    this.purchaseMap.get("Food").add(new Purchase(name, price));
                                } else {
                                    this.purchaseMap.get("Food").add(new Purchase(name, price));
                                }
                                break;
                            case "Clothes":
                                if (this.purchaseMap.get("Clothes") == null) {
                                    this.purchaseMap.put(purchaseType, new ArrayList<>());
                                    this.purchaseMap.get("Clothes").add(new Purchase(name, price));
                                } else {
                                    this.purchaseMap.get("Clothes").add(new Purchase(name, price));
                                }
                                break;
                            case "Entertainment":
                                if (this.purchaseMap.get("Entertainment") == null) {
                                    this.purchaseMap.put(purchaseType, new ArrayList<>());
                                    this.purchaseMap.get("Entertainment").add(new Purchase(name, price));
                                } else {
                                    this.purchaseMap.get("Entertainment").add(new Purchase(name, price));
                                }
                                break;
                            case "Other":
                                if (this.purchaseMap.get("Other") == null) {
                                    this.purchaseMap.put(purchaseType, new ArrayList<>());
                                    this.purchaseMap.get("Other").add(new Purchase(name, price));
                                } else {
                                    this.purchaseMap.get("Other").add(new Purchase(name, price));
                                }
                                break;
                        }
                    }
                }

                bufferedReader.close();

                System.out.println("Purchases were loaded!\n");



            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File is empty!");
        }
    }

    public void sort() {
        this.sortStrategy.sort(this.purchaseMap);
    }

    private void setIncome(double income) {
        this.income = income;
    }

    private void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public void setSortStrategy(Sortable sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
}
