import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n + 1);
        this.count = 0;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
        }
        for (int i = 0; i < n + 1; i++) {
            this.childs.add(null);
        }
    }

    // Check if the current node is full
    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    // Check if the current node is empty
    public boolean nodeEmpty() {
        return count == 0;
    }

    /**
     * Busca la clave cl en el nodo actual. Si la encuentra, retorna true y la posición en pos[0].
     * Si no la encuentra, retorna false y la posición del hijo donde debe descender en pos[0].
     */
    public boolean searchNode(E cl, int[] pos) {
        int i = 0;
        while (i < count && i < keys.size() && keys.get(i) != null && cl.compareTo(keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        if (i < count && i < keys.size() && keys.get(i) != null && cl.compareTo(keys.get(i)) == 0) {
            return true; // Encontrado
        } else {
            return false; // No encontrado, pos[0] indica el hijo
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

class BPlusNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BPlusNode<E>> childs;
    protected int count;
    protected boolean isLeaf;
    protected BPlusNode<E> next; // Para hojas enlazadas

    public BPlusNode(int n, boolean isLeaf) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BPlusNode<E>>(n + 1);
        this.count = 0;
        this.isLeaf = isLeaf;
        this.next = null;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
        }
        for (int i = 0; i < n + 1; i++) {
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean searchNode(E cl, int[] pos) {
        int i = 0;
        while (i < count && cl.compareTo(keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        if (i < count && cl.compareTo(keys.get(i)) == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}