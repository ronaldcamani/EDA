public class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    // Constructor for singly linked list
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    // Constructor for doubly linked list
    public Node(T data, Node<T> prev) {
        this.data = data;
        this.next = null;
        this.prev = prev;
    }
} 