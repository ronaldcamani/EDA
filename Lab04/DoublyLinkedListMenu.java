import java.util.Scanner;

public class DoublyLinkedListMenu {
    public static void main(String[] args) {
        DoublyLinkedListWithMenu<Integer> list = new DoublyLinkedListWithMenu<>();
        Scanner scanner = new Scanner(System.in);
        
        // Initialize list with numbers 1-10
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }

        while (true) {
            System.out.println("\nDoubly Linked List Operations:");
            System.out.println("1. Print List");
            System.out.println("2. Insert Element");
            System.out.println("3. Delete by Key");
            System.out.println("4. Delete at Position");
            System.out.println("5. Get Size");
            System.out.println("6. Remove First");
            System.out.println("7. Remove Last");
            System.out.println("8. Add First");
            System.out.println("9. Add Last");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                case 1:
                    System.out.print("List: ");
                    list.printList();
                    break;
                case 2:
                    System.out.print("Enter element to insert: ");
                    list.insert(scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Enter key to delete: ");
                    list.deleteByKey(scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Enter position to delete: ");
                    list.deleteAtPosition(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Size: " + list.size());
                    break;
                case 6:
                    list.removeFirst();
                    System.out.println("First element removed");
                    break;
                case 7:
                    list.removeLast();
                    System.out.println("Last element removed");
                    break;
                case 8:
                    System.out.print("Enter element to add at first: ");
                    list.addFirst(scanner.nextInt());
                    break;
                case 9:
                    System.out.print("Enter element to add at last: ");
                    list.addLast(scanner.nextInt());
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
} 