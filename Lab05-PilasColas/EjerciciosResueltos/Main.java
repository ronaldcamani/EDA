public class Main {
  public static void main(String[] args) {
    System.out.println("=== Testing Stack Implementation ===");
    StackList<Integer> stack = new StackList<>();
      
    // Push elements 1-8
    for (int i = 1; i <= 8; i++) {
      stack.push(i);
      System.out.println("Pushed: " + i);
      stack.display();
    }

    // Pop all elements
    System.out.println("\nPopping all elements:");
    while (!stack.isEmpty()) {
      System.out.println("Popped: " + stack.pop());
      stack.display();
    }

    // Test Queue Implementation
    System.out.println("\n=== Testing Queue Implementation ===");
    QueueList<Integer> queue = new QueueList<>();
    
    // Enqueue elements 1-8
    for (int i = 1; i <= 8; i++) {
      queue.enqueue(i);
      System.out.println("Enqueued: " + i);
      queue.display();
    }

    // Dequeue all elements
    System.out.println("\nDequeuing all elements:");
    while (!queue.isEmpty()) {
      System.out.println("Dequeued: " + queue.dequeue());
      queue.display();
    }
  }
}