public class DoublyLinkedListWithMenu<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedListWithMenu() {
        head = null;
        tail = null;
        size = 0;
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
        size++;
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void deleteByKey(T key) {
        if (head == null) return;

        if (head.data.equals(key)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return;
        }

        Node<T> current = head;
        while (current != null && !current.data.equals(key)) {
            current = current.next;
        }

        if (current != null) {
            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev;
            }
            current.prev.next = current.next;
            size--;
        }
    }

    public void deleteAtPosition(int position) {
        if (position < 0 || position >= size) return;

        if (position == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        current.prev.next = current.next;
        size--;
    }

    public int size() {
        return size;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        }
    }

    public void removeLast() {
        if (tail != null) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
        }
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    public void addLast(T data) {
        insert(data);
    }
} 