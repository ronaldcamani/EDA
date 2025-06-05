public class QueueList<T> {
  private Node<T> front;
  private Node<T> rear;
  private int size;

  public QueueList() {
    front = null;
    rear = null;
    size = 0;
  }

  public void enqueue(T element) {
    Node<T> newNode = new Node<>(element);
    if (isEmpty()) {
      front = newNode;
      rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
    size++;
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T element = front.data;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    size--;
    return element;
  }
  //muestra el elemento al frente de la cola
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return front.data;
  }

  public boolean isEmpty() {
      return front == null;
  }

  public int size() {
      return size;
  }

  public void display() {
      Node<T> current = front;
      System.out.print("Queue: ");
      while (current != null) {
          System.out.print(current.data + " ");
          current = current.next;
      }
      System.out.println();
  }
} 