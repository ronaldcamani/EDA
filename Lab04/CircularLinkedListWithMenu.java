public class CircularLinkedListWithMenu<T> {
    private Node<T> head;
    private int size;

    public CircularLinkedListWithMenu() {
        head = null;
        size = 0;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head;
        }
        size++;
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

    public void deleteByKey(T key) {
        if (head == null) return;

        if (head.data.equals(key)) {
            if (head.next == head) {
                head = null;
            } else {
                Node<T> current = head;
                while (current.next != head) {
                    current = current.next;
                }
                current.next = head.next;
                head = head.next;
            }
            size--;
            return;
        }

        Node<T> current = head;
        while (current.next != head && !current.next.data.equals(key)) {
            current = current.next;
        }

        if (current.next != head) {
            current.next = current.next.next;
            size--;
        }
    }

    public void deleteAtPosition(int position) {
        if (position < 0 || position >= size) return;

        if (position == 0) {
            if (head.next == head) {
                head = null;
            } else {
                Node<T> current = head;
                while (current.next != head) {
                    current = current.next;
                }
                current.next = head.next;
                head = head.next;
            }
            size--;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public int size() {
        return size;
    }

    public void removeFirst() {
        if (head != null) {
            if (head.next == head) {
                head = null;
            } else {
                Node<T> current = head;
                while (current.next != head) {
                    current = current.next;
                }
                current.next = head.next;
                head = head.next;
            }
            size--;
        }
    }

    public void removeLast() {
        if (head == null) return;
        if (head.next == head) {
            head = null;
            size--;
            return;
        }

        Node<T> current = head;
        while (current.next.next != head) {
            current = current.next;
        }
        current.next = head;
        size--;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        insert(data);
    }
} 