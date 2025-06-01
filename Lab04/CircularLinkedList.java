public class CircularLinkedList<T> {
    private Node<T> head;

    public CircularLinkedList() {
        head = null;
    }
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (head == null) {
            head = newNode;
            head.next = head; // Point to itself
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head;
        }
    }
    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node<T> current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        // Insert numbers 1 to 12
        for (int i = 1; i <= 12; i++) {
            list.insert(i);
        }
        System.out.println("Circular Linked List with numbers 1-12:");
        list.printList();
    }
} 