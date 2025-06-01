import java.util.LinkedList;

public class JavaUtilDoublyLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        // Insert numbers 1 to 10
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        
        System.out.println("Doubly Linked List using java.util.LinkedList with numbers 1-10:");
        System.out.println(list);
        
        // Demonstrate some LinkedList methods
        System.out.println("\nFirst element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        System.out.println("Size: " + list.size());
    }
} 