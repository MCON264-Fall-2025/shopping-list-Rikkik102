package exercises.shopping_app;

import edu.touro.mcon264.apps.collections.ArrayBasedList;
import edu.touro.mcon264.apps.collections.ListInterface;

import java.util.List;
import java.util.Scanner;

/**
 * Creates a ListInterface<ShoppingItem> instance.
 * Has a main method that runs a console application.
 * Provides a simple text-based menu to:
 * Add items (in sorted order).
 * View the current list.
 * “Shop” the next item on the list.
 * Exit the program.
 */
public class ShoppingListApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Choose implementation:
        ListInterface<ShoppingItem> shoppingList = new ArrayBasedList<>();
        // or:
        // ListInterface<ShoppingItem> shoppingList = new LinkedBasedList<>();

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter aisle: ");
                    String aisle = scanner.nextLine().trim();
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine().trim();
                    ShoppingItem newItem = new ShoppingItem(aisle, name);
                    insertSorted(shoppingList, newItem);
                    System.out.println("Added: " + newItem);
                    break;
                case "2":
                    System.out.println("Current shopping list:");
                    printList(shoppingList);
                    break;
                case "3":
                    ShoppingItem next = shopNext(shoppingList);
                    if (next == null) {
                        System.out.println("All done! No items left to shop.");
                    } else {
                        System.out.println("Next item to buy: " + next);
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
            }

            System.out.println();
        }
    }
    /**
     * Inserts the given item into the list so that the list remains sorted
     * by aisle and then item name.
     */
    public static void insertSorted(ListInterface<ShoppingItem> list, ShoppingItem item) {
        // TODO: implement using list.get(i), list.add(i, item), list.size()
        boolean put = false;
            for (int i = 0; i < list.size(); i++) {
                if (item.compareTo(list.get(i)) < 0) {
                    list.add(i, item);
                    put = true;
                    break;
                }
            }
            if (!put) {
                list.add(list.size(), item);
            }
    }
        /**
         * Returns the "next" item to shop and removes it from the list.
         * If the list is empty, returns null.
         */
        public static ShoppingItem shopNext (ListInterface < ShoppingItem > list) {
            // TODO: implement using ListInterface methods
            if (list.isEmpty()) {
                return null;
            } else {
                return list.remove(0);
            }
        }

        public static void printMenu() {
            System.out.println("=== Shopping List Menu ===");
            System.out.println("1. Add item");
            System.out.println("2. Show current shopping list");
            System.out.println("3. Shop next item");
            System.out.println("0. Exit");
        }

        public static void printList(ListInterface<ShoppingItem> shoppingList) {
            for (int i = 0; i < shoppingList.size(); i++) {
                System.out.println(shoppingList.get(i).toString()+"\n");
            }
        }
}
