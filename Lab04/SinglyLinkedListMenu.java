import java.util.Scanner;

public class SinglyLinkedListMenu {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }
        while (true) {
            System.out.println("\nSingly Linked List Operations:");
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
                case 0 -> { // Usamos un bloque {} porque hay múltiples sentencias
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;}
                case 1 -> { System.out.print("Lista: ");
                            list.printList();}
                case 2 -> {
                    System.out.print("Introduce el elemento a insertar: ");
                    list.insert(scanner.nextInt());}
                case 3 -> {
                    System.out.print("Introduce la clave a eliminar: ");
                    list.deleteByKey(scanner.nextInt());}
                case 4 -> {
                    System.out.print("Introduce la posición a eliminar: ");
                    list.deleteAtPosition(scanner.nextInt());}
                case 5 -> System.out.println("Tamaño: " + list.size()); // Una sola sentencia, no necesita {}
                case 6 -> {
                    list.removeFirst();
                    System.out.println("Primer elemento eliminado");}
                case 7 -> {
                    list.removeLast();
                    System.out.println("Último elemento eliminado");}
                case 8 -> {
                    System.out.print("Introduce el elemento a añadir al principio: ");
                    list.addFirst(scanner.nextInt());}
                case 9 -> {
                    System.out.print("Introduce el elemento a añadir al final: ");
                    list.addLast(scanner.nextInt());}
                default -> System.out.println("¡Opción inválida!");
            }
        }
    }
} 