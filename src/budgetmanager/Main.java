package budgetmanager;

import sort.SortAll;
import sort.SortByType;
import sort.SortCertainTypes;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BudgetManager budgetManager = new BudgetManager();

        boolean exit = false;
        do {
            printMenuChooseAction();
            int actionType = scanner.nextInt();
            scanner.nextLine();
            System.out.println();


            boolean back;
            switch (actionType) {
                case 1:
                    System.out.println("Enter income: ");
                    double income = scanner.nextDouble();
                    scanner.nextLine();

                    budgetManager.addIncome(income);
                    break;
                case 2:
                    back = false;
                    do {
                        printMenuAddPurchase();
                        int purchaseType = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();


                        String name;
                        double price;
                        switch (purchaseType) {
                            // adding food
                            case 1:
                                System.out.println("Enter purchase name: ");
                                name = scanner.nextLine();

                                System.out.println("Enter its price: ");
                                price = scanner.nextDouble();
                                scanner.nextLine();
                                budgetManager.addPurchase("Food", new Purchase(name, price));
                                break;
                            // adding clothes
                            case 2:
                                System.out.println("Enter purchase name: ");
                                name = scanner.nextLine();

                                System.out.println("Enter its price: ");
                                price = scanner.nextDouble();
                                scanner.nextLine();
                                budgetManager.addPurchase("Clothes", new Purchase(name, price));
                                break;
                            // adding entertainment
                            case 3:
                                System.out.println("Enter purchase name: ");
                                name = scanner.nextLine();

                                System.out.println("Enter its price: ");
                                price = scanner.nextDouble();
                                scanner.nextLine();
                                budgetManager.addPurchase("Entertainment", new Purchase(name, price));
                                break;
                            // adding other
                            case 4:
                                System.out.println("Enter purchase name: ");
                                name = scanner.nextLine();

                                System.out.println("Enter its price: ");
                                price = scanner.nextDouble();
                                scanner.nextLine();
                                budgetManager.addPurchase("Other", new Purchase(name, price));
                                break;
                            // back
                            case 5:
                                back = true;
                                break;

                        }

                    } while (!back);

                    break;
                case 3:

                    if (budgetManager.getPurchaseMap().size() == 0) {
                        System.out.println("Purchase list is empty!\n");
                    } else {
                        back = false;
                        do {
                            printMenuShowListOfPurchases();
                            int purchasesType = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            switch (purchasesType) {
                                // showing food
                                case 1:
                                    budgetManager.showPurchaseList("Food");
                                    break;
                                // showing clothes
                                case 2:
                                    budgetManager.showPurchaseList("Clothes");
                                    break;
                                // showing entertainment
                                case 3:
                                    budgetManager.showPurchaseList("Entertainment");
                                    break;
                                // showing other
                                case 4:
                                    budgetManager.showPurchaseList("Other");
                                    break;
                                // showing all
                                case 5:
                                    budgetManager.showPurchaseList("All");
                                    break;
                                case 6:
                                    back = true;
                                    break;

                            }

                        } while (!back);
                    }
                    break;
                case 4:
                    budgetManager.showBalance();
                    break;
                case 5:
                    budgetManager.save(new File("purchases.txt"));
                    break;
                case 6:
                    budgetManager.load(new File("purchases.txt"));
                    break;
                case 7:

                    if (budgetManager.getPurchaseMap().size() == 0) {
                        System.out.println("Purchase list is empty!\n");
                    } else {
                        back = false;
                        do {
                            printMenuWhatToSort();
                            int sortType = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            switch (sortType) {
                                // sort all
                                case 1:
                                    budgetManager.setSortStrategy(new SortAll());
                                    budgetManager.sort();
                                    break;
                                // sort by type
                                case 2:
                                    budgetManager.setSortStrategy(new SortByType());
                                    budgetManager.sort();
                                    break;
                                // sort by certain types
                                case 3:
                                    printMenuWhatTypeOfPurchaseToSort();
                                    int purchasesType = scanner.nextInt();
                                    scanner.nextLine();

                                    String purchaseTypeToSort = null;
                                    switch (purchasesType) {
                                        case 1:
                                            purchaseTypeToSort = "Food";
                                            break;
                                        case 2:
                                            purchaseTypeToSort = "Clothes";
                                            break;
                                        case 3:
                                            purchaseTypeToSort = "Entertainment";
                                            break;
                                        case 4:
                                            purchaseTypeToSort = "Other";
                                            break;
                                    }
                                    budgetManager.setSortStrategy(new SortCertainTypes(purchaseTypeToSort));
                                    budgetManager.sort();
                                    break;
                                // showing other
                                case 4:
                                    back = true;
                                    break;

                            }

                        } while (!back);
                    }
                    break;

                case 0:
                    exit = true;
                    System.out.println("Bye!");
                    break;
            }

        } while (!exit);

        scanner.close();
    }

    public static void printMenuChooseAction() {
        System.out.println("Choose your action: " +
                 "\n1) Add income" +
                 "\n2) Add purchase" +
                 "\n3) Show list of purchases" +
                 "\n4) Balance" +
                 "\n5) Save" +
                 "\n6) Load" +
                 "\n7) Analyze (sort)" +
                 "\n0) Exit");
    }

    public static void printMenuAddPurchase() {
        System.out.println("Choose the type of purchase: " +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) Back");
    }

    public static void printMenuShowListOfPurchases() {
        System.out.println("Choose the type of purchases: " +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) All" +
                "\n6) Back");
    }

    public static void printMenuWhatToSort() {
        System.out.println("How do you want to sort? " +
                "\n1) Sort all purchases" +
                "\n2) Sort by type" +
                "\n3) Sort certain type" +
                "\n4) Back");
    }

    public static void printMenuWhatTypeOfPurchaseToSort() {
        System.out.println("Choose the type of purchase " +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other");
    }



}
