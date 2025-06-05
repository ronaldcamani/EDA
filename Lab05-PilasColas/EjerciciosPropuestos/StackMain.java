package EjerciciosPropuestos;

import java.util.Scanner;

public class StackMain {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Stack<Integer> stack = new Stack<>(10); // Stack with capacity 10
    boolean running = true;

    while (running) {
      System.out.println("\n=== Stack Operations Menu ===");
      System.out.println("1. Push element");
      System.out.println("2. Pop element");
      System.out.println("3. View top element");
      System.out.println("4. Check if stack is empty");
      System.out.println("5. Check if stack is full");
      System.out.println("6. Print stack");
      System.out.println("7. Destroy stack");
      System.out.println("8. Exit");
      System.out.print("Enter your choice (1-8): ");

      int choice = scanner.nextInt();

      try {
        switch (choice) {
          case 1:
            if (stack.isFull()) {
              System.out.println("Stack is full!");
            } else {
              System.out.print("Enter element to push: ");
              int element = scanner.nextInt();
              stack.push(element);
              System.out.println("Element pushed successfully!");
            }
            break;

          case 2:
            int popped = stack.pop();
            System.out.println("Popped element: " + popped);
            break;

          case 3:
            int top = stack.top();
            System.out.println("Top element: " + top);
            break;

          case 4:
            System.out.println("Stack is " + (stack.isEmpty() ? "empty" : "not empty"));
            break;

          case 5:
            System.out.println("Stack is " + (stack.isFull() ? "full" : "not full"));
            break;

          case 6:
            stack.printStack();
            break;

          case 7:
            stack.destroyStack();
            System.out.println("Stack destroyed!");
            break;

          case 8:
            running = false;
            System.out.println("Exiting program...");
            break;

          default:
            System.out.println("Invalid choice! Please try again.");
        }
      } catch (IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
    scanner.close();
  }
} 