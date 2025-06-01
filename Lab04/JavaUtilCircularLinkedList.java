import java.util.LinkedList;

public class JavaUtilCircularLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        // Insert numbers 1 to 12
        for (int i = 1; i <= 12; i++) {
            list.add(i);
        }
        
        System.out.println("Circular Linked List using java.util.LinkedList with numbers 1-12:");
        System.out.println(list);
        
        // Demonstrate circular behavior by iterating multiple times
        System.out.println("\nDemonstrating circular behavior:");
        for (int i = 0; i < 15; i++) {
            System.out.print(list.get(i % list.size()) + " ");
        }
        System.out.println();
    }
} 