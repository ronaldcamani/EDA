public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data, tail);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        
        // Insert numbers 1 to 10
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }
        
        System.out.println("Doubly Linked List with numbers 1-10:");
        list.printList();
    }
} 