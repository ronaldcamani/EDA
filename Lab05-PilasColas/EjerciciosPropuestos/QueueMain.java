package EjerciciosPropuestos;

import java.util.Scanner;

public class QueueMain {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Queue<Integer> queue = new Queue<>(10); // Queue with capacity 10
    boolean running = true;

    while (running) {
      System.out.println("\n=== Queue Operations Menu ===");
      System.out.println("1. Enqueue element");
      System.out.println("2. Dequeue element");
      System.out.println("3. View front element");
      System.out.println("4. View back element");
      System.out.println("5. Check if queue is empty");
      System.out.println("6. Check if queue is full");
      System.out.println("7. Print queue");
      System.out.println("8. Destroy queue");
      System.out.println("9. Exit");
      System.out.print("Enter your choice (1-9): ");

      int choice = scanner.nextInt();

      try {
        switch (choice) {
          case 1:
            if (queue.isFull()) {
              System.out.println("Queue is full!");
            } else {
              System.out.print("Enter element to enqueue: ");
              int element = scanner.nextInt();
              queue.enqueue(element);
              System.out.println("Element enqueued successfully!");
            }
            break;

          case 2:
            int dequeued = queue.dequeue();
            System.out.println("Dequeued element: " + dequeued);
            break;

          case 3:
            int front = queue.front();
            System.out.println("Front element: " + front);
            break;

          case 4:
            int back = queue.back();
            System.out.println("Back element: " + back);
            break;

          case 5:
            System.out.println("Queue is " + (queue.isEmpty() ? "empty" : "not empty"));
            break;

          case 6:
            System.out.println("Queue is " + (queue.isFull() ? "full" : "not full"));
            break;

          case 7:
            queue.printQueue();
            break;

          case 8:
            queue.destroyQueue();
            System.out.println("Queue destroyed!");
            break;

          case 9:
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