import java.util.ArrayList;

public class BTree<E extends Comparable<E>> {
  private BNode<E> root;
  private int orden;
  private boolean up;
  private BNode<E> nDes;
  public BTree(int orden) {
  this.orden = orden;
  this.root = null;
  }
  public boolean isEmpty() {
  return this.root == null;
  }
  public void insert(E cl) {
  up = false;
  E mediana;
  BNode<E> pnew;
  mediana = push(this.root, cl);
  if (up) {
  pnew = new BNode<E>(this.orden);
  pnew.count = 1;
  pnew.keys.set(0, mediana);
  pnew.childs.set(0, this.root);
  pnew.childs.set(1, nDes);
  this.root = pnew;
  }
  }
  private E push(BNode<E> current, E cl) {
    int pos[] = new int[1];
    E mediana;
    if (current == null) {
    up = true;
    nDes = null;
    return cl;
    } else {
    boolean fl;
    fl = current.searchNode(cl, pos);
    if (fl) {
    System.out.println("Item duplicado\n");
    up = false;
    return null;
    }
    mediana = push(current.childs.get(pos[0]), cl);
    if (up) {
    if (current.nodeFull(this.orden - 1))
    mediana = dividedNode(current, mediana, pos[0]);
    else {
    }
  }
  up = false;
  putNode(current, mediana, nDes, pos[0]);
  return mediana;
  }
  }
  private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
  int i;
  for (i = current.count - 1; i >= k; i--) {
  if (i + 1 < current.keys.size()) {
  current.keys.set(i + 1, current.keys.get(i));
  }
  if (i + 2 < current.childs.size()) {
  current.childs.set(i + 2, current.childs.get(i + 1));
  }
  }
  if (k < current.keys.size()) {
  current.keys.set(k, cl);
  }
  if (k + 1 < current.childs.size()) {
  current.childs.set(k + 1, rd);
  }
  current.count++;
  }
  private E dividedNode(BNode<E> current, E cl, int k) {
    BNode<E> rd = nDes;
    int i, posMdna;
    posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
    nDes = new BNode<E>(this.orden);
    for (i = posMdna; i < this.orden - 1; i++) {
    nDes.keys.set(i - posMdna, current.keys.get(i));
    nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
    }
    nDes.count = (this.orden - 1) - posMdna;
    current.count = posMdna;
    if (k <= this.orden / 2)
    putNode(current, cl, rd, k);
    else
    putNode(nDes, cl, rd, k - posMdna);
    E median = current.keys.get(current.count - 1);
    nDes.childs.set(0, current.childs.get(current.count));
    current.count--;
    return median;
  }

    public String toString() {
        String s = "";
        if (isEmpty())
            s += "BTree is empty...";
        else
            s = writeTree(this.root, 0);
        return s;
    }

    private String writeTree(BNode<E> current, int level) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("Level ").append(level).append(" [");
        for (int i = 0; i < current.count; i++) {
            sb.append(current.keys.get(i));
            if (i < current.count - 1) sb.append(", ");
        }
        sb.append("]\n");
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                sb.append(writeTree(current.childs.get(i), level + 1));
            }
        }
        return sb.toString();
    }

    // Destruye el árbol
    public void destroy() {
        root = null;
    }

    // Busca un elemento en el árbol
    public boolean search(E key) {
        return search(root, key);
    }
    private boolean search(BNode<E> node, E key) {
        if (node == null) return false;
        int[] pos = new int[1];
        boolean found = node.searchNode(key, pos);
        if (found) return true;
        return search(node.childs.get(pos[0]), key);
    }

    // Devuelve el valor mínimo del árbol
    public E Min() {
        if (isEmpty()) return null;
        BNode<E> node = root;
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node.keys.get(0);
    }

    // Devuelve el valor máximo del árbol
    public E Max() {
        if (isEmpty()) return null;
        BNode<E> node = root;
        while (node.childs.get(node.count) != null) {
            node = node.childs.get(node.count);
        }
        return node.keys.get(node.count - 1);
    }

    // Predecesor de una clave
    public E Predecesor(E key) {
        // Implementación pendiente
        return null;
    }

    // Sucesor de una clave
    public E Sucesor(E key) {
        // Implementación pendiente
        return null;
    }

    // Elimina un elemento del árbol
    public void remove(E key) {
        remove(root, key);
        if (root != null && root.count == 0) {
            root = root.childs.get(0);
        }
    }

    private void remove(BNode<E> node, E key) {
        if (node == null) return;
        int[] pos = new int[1];
        boolean found = node.searchNode(key, pos);
        if (found) {
            // Caso 1: La clave está en un nodo hoja
            if (node.childs.get(0) == null) {
                for (int i = pos[0]; i < node.count - 1; i++) {
                    node.keys.set(i, node.keys.get(i + 1));
                }
                node.keys.set(node.count - 1, null);
                node.count--;
            } else {
                // Caso 2: La clave está en un nodo interno
                BNode<E> predNode = node.childs.get(pos[0]);
                while (predNode.childs.get(predNode.count) != null) {
                    predNode = predNode.childs.get(predNode.count);
                }
                E pred = predNode.keys.get(predNode.count - 1);
                node.keys.set(pos[0], pred);
                remove(node.childs.get(pos[0]), pred);
            }
        } else {
            // Descender al hijo correspondiente
            BNode<E> child = node.childs.get(pos[0]);
            if (child == null) return;
            remove(child, key);
            // Verificar si el hijo necesita ser fusionado o redistribuido
            if (child.count < (orden - 1) / 2) {
                fixChild(node, pos[0]);
            }
        }
    }

    private void fixChild(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> leftSibling = idx > 0 ? parent.childs.get(idx - 1) : null;
        BNode<E> rightSibling = idx < parent.count ? parent.childs.get(idx + 1) : null;
        int minKeys = (orden - 1) / 2;
        // Intentar redistribuir con el hermano izquierdo
        if (leftSibling != null && leftSibling.count > minKeys) {
            for (int i = child.count; i > 0; i--) {
                child.keys.set(i, child.keys.get(i - 1));
            }
            child.keys.set(0, parent.keys.get(idx - 1));
            if (child.childs.get(0) != null) {
                for (int i = child.count + 1; i > 0; i--) {
                    child.childs.set(i, child.childs.get(i - 1));
                }
                child.childs.set(0, leftSibling.childs.get(leftSibling.count));
            }
            child.count++;
            parent.keys.set(idx - 1, leftSibling.keys.get(leftSibling.count - 1));
            leftSibling.keys.set(leftSibling.count - 1, null);
            if (leftSibling.childs.get(0) != null)
                leftSibling.childs.set(leftSibling.count, null);
            leftSibling.count--;
        } else if (rightSibling != null && rightSibling.count > minKeys) {
            child.keys.set(child.count, parent.keys.get(idx));
            if (child.childs.get(0) != null)
                child.childs.set(child.count + 1, rightSibling.childs.get(0));
            child.count++;
            parent.keys.set(idx, rightSibling.keys.get(0));
            for (int i = 0; i < rightSibling.count - 1; i++) {
                rightSibling.keys.set(i, rightSibling.keys.get(i + 1));
            }
            rightSibling.keys.set(rightSibling.count - 1, null);
            if (rightSibling.childs.get(0) != null) {
                for (int i = 0; i < rightSibling.count; i++) {
                    rightSibling.childs.set(i, rightSibling.childs.get(i + 1));
                }
                rightSibling.childs.set(rightSibling.count, null);
            }
            rightSibling.count--;
        } else {
            // Fusionar con un hermano
            if (leftSibling != null) {
                FuzeNode(leftSibling, child, parent.keys.get(idx - 1));
                for (int i = idx - 1; i < parent.count - 1; i++) {
                    parent.keys.set(i, parent.keys.get(i + 1));
                    parent.childs.set(i + 1, parent.childs.get(i + 2));
                }
                parent.keys.set(parent.count - 1, null);
                parent.childs.set(parent.count, null);
                parent.count--;
            } else if (rightSibling != null) {
                FuzeNode(child, rightSibling, parent.keys.get(idx));
                for (int i = idx; i < parent.count - 1; i++) {
                    parent.keys.set(i, parent.keys.get(i + 1));
                    parent.childs.set(i + 1, parent.childs.get(i + 2));
                }
                parent.keys.set(parent.count - 1, null);
                parent.childs.set(parent.count, null);
                parent.count--;
            }
        }
    }

    private void FuzeNode(BNode<E> left, BNode<E> right, E parentKey) {
        left.keys.set(left.count, parentKey);
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + 1 + i, right.keys.get(i));
        }
        for (int i = 0; i <= right.count; i++) {
            left.childs.set(left.count + 1 + i, right.childs.get(i));
        }
        left.count += 1 + right.count;
    }
}

class TestBTree {
    public static void main(String[] args) {
        // Ejercicio 1: BTree de grado 5
        System.out.println("\n--- Ejercicio 1: BTree grado 5 ---");
        BTree<Integer> btree5 = new BTree<>(5);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        for (int x : datos) {
            System.out.println("Insertando: " + x);
            btree5.insert(x);
            System.out.println(btree5);
        }
        // Eliminación (no implementada, solo muestra el paso)
        for (int x : datos) {
            System.out.println("Eliminando: " + x);
            btree5.remove(x);
            System.out.println(btree5);
        }
        // Ejercicio 2: BTree de grado 4
        System.out.println("\n--- Ejercicio 2: BTree grado 4 ---");
        BTree<Integer> btree4 = new BTree<>(4);
        for (int x : datos) {
            System.out.println("Insertando: " + x);
            btree4.insert(x);
            System.out.println(btree4);
        }
        for (int x : datos) {
            System.out.println("Eliminando: " + x);
            btree4.remove(x);
            System.out.println(btree4);
        }
    }
}

class BPlusTree<E extends Comparable<E>> {
    private BPlusNode<E> root;
    private int orden;

    public BPlusTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        if (root == null) {
            root = new BPlusNode<>(orden, true);
            root.keys.set(0, cl);
            root.count = 1;
        } else {
            BPlusNode<E> newChild = null;
            E newKey = insert(root, cl, newChild);
            if (newKey != null) {
                BPlusNode<E> newRoot = new BPlusNode<>(orden, false);
                newRoot.keys.set(0, newKey);
                newRoot.childs.set(0, root);
                newRoot.childs.set(1, newChild);
                newRoot.count = 1;
                root = newRoot;
            }
        }
    }

    // Inserción recursiva, retorna la clave promovida si hay split
    private E insert(BPlusNode<E> node, E cl, BPlusNode<E> newChild) {
        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);
        if (found) return null; // No duplicados
        if (node.isLeaf) {
            for (int i = node.count; i > pos[0]; i--) {
                node.keys.set(i, node.keys.get(i - 1));
            }
            node.keys.set(pos[0], cl);
            node.count++;
            if (node.count < orden) {
                return null;
            } else {
                // Split hoja
                int mid = orden / 2;
                BPlusNode<E> sibling = new BPlusNode<>(orden, true);
                for (int i = mid; i < orden; i++) {
                    sibling.keys.set(i - mid, node.keys.get(i));
                    sibling.count++;
                    node.keys.set(i, null);
                }
                node.count = mid;
                sibling.next = node.next;
                node.next = sibling;
                if (pos[0] < mid) {
                    // La nueva clave queda en la hoja original
                } else {
                    // La nueva clave queda en la hoja nueva
                }
                newChild = sibling;
                return sibling.keys.get(0);
            }
        } else {
            BPlusNode<E> child = node.childs.get(pos[0]);
            E newKey = insert(child, cl, newChild);
            if (newKey == null) return null;
            for (int i = node.count; i > pos[0]; i--) {
                node.keys.set(i, node.keys.get(i - 1));
                node.childs.set(i + 1, node.childs.get(i));
            }
            node.keys.set(pos[0], newKey);
            node.childs.set(pos[0] + 1, newChild);
            node.count++;
            if (node.count < orden) {
                return null;
            } else {
                // Split interno
                int mid = orden / 2;
                BPlusNode<E> sibling = new BPlusNode<>(orden, false);
                for (int i = mid + 1; i < orden; i++) {
                    sibling.keys.set(i - mid - 1, node.keys.get(i));
                    sibling.childs.set(i - mid - 1, node.childs.get(i));
                    sibling.count++;
                    node.keys.set(i, null);
                    node.childs.set(i, null);
                }
                sibling.childs.set(sibling.count, node.childs.get(orden));
                node.childs.set(orden, null);
                node.count = mid;
                newChild = sibling;
                return node.keys.get(mid);
            }
        }
    }

    public boolean search(E key) {
        return search(root, key);
    }
    private boolean search(BPlusNode<E> node, E key) {
        if (node == null) return false;
        int[] pos = new int[1];
        boolean found = node.searchNode(key, pos);
        if (found) return true;
        if (node.isLeaf) return false;
        return search(node.childs.get(pos[0]), key);
    }

    public String toString() {
        String s = "";
        if (isEmpty())
            s += "BPlusTree is empty...";
        else
            s = writeTree(this.root, 0);
        return s;
    }

    private String writeTree(BPlusNode<E> current, int level) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("Level ").append(level).append(" [");
        for (int i = 0; i < current.count; i++) {
            sb.append(current.keys.get(i));
            if (i < current.count - 1) sb.append(", ");
        }
        sb.append("]\n");
        if (!current.isLeaf) {
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) {
                    sb.append(writeTree(current.childs.get(i), level + 1));
                }
            }
        }
        return sb.toString();
    }
}

class TestBPlusTree {
    public static void main(String[] args) {
        // Ejercicio 5: B+Tree de grado 5
        System.out.println("\n--- Ejercicio 5: B+Tree grado 5 ---");
        BPlusTree<Integer> bptree5 = new BPlusTree<>(5);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        for (int x : datos) {
            System.out.println("Insertando: " + x);
            bptree5.insert(x);
            System.out.println(bptree5);
        }
        // Eliminación (no implementada, solo muestra el paso)
        for (int x : datos) {
            System.out.println("Eliminando: " + x);
            // bptree5.remove(x); // Implementar
            System.out.println(bptree5);
        }
        // Ejercicio 6: B+Tree de grado 4
        System.out.println("\n--- Ejercicio 6: B+Tree grado 4 ---");
        BPlusTree<Integer> bptree4 = new BPlusTree<>(4);
        for (int x : datos) {
            System.out.println("Insertando: " + x);
            bptree4.insert(x);
            System.out.println(bptree4);
        }
        for (int x : datos) {
            System.out.println("Eliminando: " + x);
            // bptree4.remove(x); // Implementar
            System.out.println(bptree4);
        }
    }
}