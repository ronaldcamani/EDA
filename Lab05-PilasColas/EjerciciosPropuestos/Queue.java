package EjerciciosPropuestos;

public class Queue<E> {
  private Node<E> front;
  private Node<E> rear;
  private int size;
  private int capacity;

  public Queue() {
    front = null;
    rear = null;
    size = 0;
    capacity = Integer.MAX_VALUE; // Default to unlimited capacity
  }

  public Queue(int capacity) {
    front = null;
    rear = null;
    size = 0;
    this.capacity = capacity;
  }

  public void enqueue(E element) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    Node<E> newNode = new Node<>(element);
    if (isEmpty()) {
      front = newNode;
      rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
    size++;
  }

  public E dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    E element = front.data;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    size--;
    return element;
  }

  public E front() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return front.data;
  }

  public E back() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return rear.data;
  }

  public void destroyQueue() {
    while (!isEmpty()) {
      dequeue();
    }
  }

  public boolean isEmpty() {
    return front == null;
  }

  public boolean isFull() {
    return size >= capacity;
  }

  public void printQueue() {
    if (isEmpty()) {
      System.out.println("Queue is empty");
      return;
    }
    System.out.print("Queue: ");
    Node<E> current = front;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }

  public int size() {
    return size;
  }
} 