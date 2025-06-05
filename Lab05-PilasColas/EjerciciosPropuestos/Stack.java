package EjerciciosPropuestos;

public class Stack<E> {
    private Node<E> top;
    private int size;
    private int capacity;

    public Stack() {
        top = null;
        size = 0;
        capacity = Integer.MAX_VALUE; // Default to unlimited capacity
    }

    public Stack(int capacity) {
        top = null;
        size = 0;
        this.capacity = capacity;
    }

    public void push(E element) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E element = top.data;
        top = top.next;
        size--;
        return element;
    }

    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    public void destroyStack() {
        while (!isEmpty()) {
            pop();
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public boolean isFull() {
        return size >= capacity;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack: ");
        Node<E> current = top;
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