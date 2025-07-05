public class BPlusTree<E extends Comparable<E>> {
    private BPlusNode<E> root;
    private int orden;
    
    public BPlusTree(int orden) {
        this.orden = orden;
        this.root = null;
    }
    
    public void destroy() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public void insert(E x) {
        if (root == null) {
            root = new BPlusNode<>(orden, true);
            root.keys.set(0, x);
            root.count = 1;
        } else {
            @SuppressWarnings("unchecked")
            BPlusNode<E>[] newChildRef = (BPlusNode<E>[]) new BPlusNode[1];
            E newKey = insertHelper(root, x, newChildRef);
            if (newKey != null) {
                BPlusNode<E> newRoot = new BPlusNode<>(orden, false);
                newRoot.keys.set(0, newKey);
                newRoot.childs.set(0, root);
                newRoot.childs.set(1, newChildRef[0]);
                newRoot.count = 1;
                root = newRoot;
            }
        }
    }
    
    private E insertHelper(BPlusNode<E> node, E x, BPlusNode<E>[] newChildRef) {
        if (node == null) return null;
        
        int[] pos = new int[1];
        boolean found = node.searchNode(x, pos);
        if (found && node.isLeaf) return null;
        
        if (node.isLeaf) {
            for (int i = node.count; i > pos[0]; i--) {
                if (i < node.keys.size()) {
                    node.keys.set(i, node.keys.get(i - 1));
                }
            }
            if (pos[0] < node.keys.size()) {
                node.keys.set(pos[0], x);
            }
            node.count++;
            
            if (node.count < orden) {
                return null;
            } else {
                return splitLeaf(node, newChildRef);
            }
        } else {
            @SuppressWarnings("unchecked")
            BPlusNode<E>[] childPtr = (BPlusNode<E>[]) new BPlusNode[1];
            E promotedKey = insertHelper(node.childs.get(pos[0]), x, childPtr);
            if (promotedKey == null) return null;
            
            for (int i = node.count; i > pos[0]; i--) {
                if (i < node.keys.size()) {
                    node.keys.set(i, node.keys.get(i - 1));
                }
                if (i + 1 < node.childs.size()) {
                    node.childs.set(i + 1, node.childs.get(i));
                }
            }
            if (pos[0] < node.keys.size()) {
                node.keys.set(pos[0], promotedKey);
            }
            if (pos[0] + 1 < node.childs.size()) {
                node.childs.set(pos[0] + 1, childPtr[0]);
            }
            node.count++;
            
            if (node.count < orden) {
                return null;
            } else {
                return splitInternal(node, newChildRef);
            }
        }
    }
    
    private E splitLeaf(BPlusNode<E> node, BPlusNode<E>[] newChildRef) {
        int mid = orden / 2;
        BPlusNode<E> newNode = new BPlusNode<>(orden, true);
        
        for (int i = mid; i < orden; i++) {
            newNode.keys.set(i - mid, node.keys.get(i));
            node.keys.set(i, null);
            newNode.count++;
        }
        node.count = mid;
        
        newNode.next = node.next;
        node.next = newNode;
        
        newChildRef[0] = newNode;
        return newNode.keys.get(0);
    }
    
    private E splitInternal(BPlusNode<E> node, BPlusNode<E>[] newChildRef) {
        int mid = orden / 2;
        BPlusNode<E> newNode = new BPlusNode<>(orden, false);
        
        E promotedKey = node.keys.get(mid);
        
        for (int i = mid + 1; i < orden; i++) {
            newNode.keys.set(i - mid - 1, node.keys.get(i));
            newNode.childs.set(i - mid - 1, node.childs.get(i));
            node.keys.set(i, null);
            node.childs.set(i, null);
            newNode.count++;
        }
        newNode.childs.set(newNode.count, node.childs.get(orden));
        node.childs.set(orden, null);
        
        node.keys.set(mid, null);
        node.count = mid;
        
        newChildRef[0] = newNode;
        return promotedKey;
    }
    
    public void remove(E x) {
        if (root == null) return;
        
        removeHelper(root, x);
        
        // Si el root no es hoja y no tiene claves, actualizar root
        if (root.count == 0 && !root.isLeaf && root.childs.get(0) != null) {
            root = root.childs.get(0);
        }
        
        // Si el árbol quedó completamente vacío
        if (root != null && root.count == 0 && root.isLeaf) {
            root = null;
        }
    }
    
    private boolean removeHelper(BPlusNode<E> node, E x) {
        if (node == null) return false;
        
        int[] pos = new int[1];
        boolean found = node.searchNode(x, pos);
        
        if (node.isLeaf) {
            if (found) {
                // Eliminar de la hoja
                for (int i = pos[0]; i < node.count - 1; i++) {
                    node.keys.set(i, node.keys.get(i + 1));
                }
                node.keys.set(node.count - 1, null);
                node.count--;
                return true;
            }
            return false;
        } else {
            // Para nodos internos, buscar en el hijo apropiado
            boolean removed = removeHelper(node.childs.get(pos[0]), x);
            
            if (removed) {
                // Si se eliminó un elemento, verificar si necesitamos actualizar claves internas
                // En B+ Trees, las claves internas son guías, no datos reales
                // Solo actualizamos si la clave eliminada era una clave guía
                if (found && pos[0] < node.count) {
                    // Buscar el nuevo valor mínimo en el subárbol derecho
                    BPlusNode<E> rightChild = node.childs.get(pos[0] + 1);
                    if (rightChild != null) {
                        E newKey = findMinInSubtree(rightChild);
                        if (newKey != null) {
                            node.keys.set(pos[0], newKey);
                        }
                    }
                }
                
                // Verificar si algún hijo necesita rebalanceo
                BPlusNode<E> child = node.childs.get(pos[0]);
                if (child != null && child.count == 0) {
                    // El hijo está vacío, removerlo
                    for (int i = pos[0]; i < node.count; i++) {
                        if (i + 1 < node.childs.size()) {
                            node.childs.set(i, node.childs.get(i + 1));
                        }
                    }
                    if (node.count < node.childs.size()) {
                        node.childs.set(node.count, null);
                    }
                    
                    // También remover la clave correspondiente si existe
                    if (pos[0] < node.count) {
                        for (int i = pos[0]; i < node.count - 1; i++) {
                            node.keys.set(i, node.keys.get(i + 1));
                        }
                        node.keys.set(node.count - 1, null);
                        node.count--;
                    }
                }
            }
            
            return removed;
        }
    }
    
    private E findMinInSubtree(BPlusNode<E> node) {
        if (node == null) return null;
        
        // Ir al nodo hoja más a la izquierda
        while (!node.isLeaf && node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        
        // Retornar la primera clave no nula
        if (node.count > 0) {
            return node.keys.get(0);
        }
        
        return null;
    }
    
    public boolean search(E x) {
        return search(root, x);
    }
    
    private boolean search(BPlusNode<E> node, E x) {
        if (node == null) return false;
        
        int[] pos = new int[1];
        boolean found = node.searchNode(x, pos);
        
        if (node.isLeaf) {
            return found;
        } else {
            return search(node.childs.get(pos[0]), x);
        }
    }
    
    public E Min() {
        if (isEmpty()) return null;
        
        BPlusNode<E> current = root;
        while (!current.isLeaf) {
            current = current.childs.get(0);
        }
        return current.keys.get(0);
    }
    
    public E Max() {
        if (isEmpty()) return null;
        
        BPlusNode<E> current = root;
        while (!current.isLeaf) {
            current = current.childs.get(current.count);
        }
        return current.keys.get(current.count - 1);
    }
    
    public E Predecesor(E x) {
        return null; // Implementación pendiente
    }
    
    public E Sucesor(E x) {
        return null; // Implementación pendiente
    }
    
    public String toString() {
        if (isEmpty()) {
            return "B+ Tree is empty...";
        }
        return writeTree(root, 0);
    }
    
    public String writeTree() {
        return toString();
    }
    
    private String writeTree(BPlusNode<E> node, int level) {
        if (node == null) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append("Level ").append(level).append(" [");
        for (int i = 0; i < node.count; i++) {
            sb.append(node.keys.get(i));
            if (i < node.count - 1) sb.append(", ");
        }
        sb.append(node.isLeaf ? "] (LEAF)" : "] (INTERNAL)");
        sb.append("\n");
        
        if (!node.isLeaf) {
            for (int i = 0; i <= node.count; i++) {
                if (node.childs.get(i) != null) {
                    sb.append(writeTree(node.childs.get(i), level + 1));
                }
            }
        }
        
        return sb.toString();
    }
    
    public BPlusNode<E> getRoot() {
        return root;
    }
    
    public int getOrden() {
        return orden;
    }
    
    public int getHeight() {
        return getHeight(root);
    }
    
    private int getHeight(BPlusNode<E> node) {
        if (node == null) return 0;
        return 1 + getHeight(node.childs.get(0));
    }
    
    public int countNodes() {
        return countNodes(root);
    }
    
    private int countNodes(BPlusNode<E> node) {
        if (node == null) return 0;
        int count = 1;
        if (!node.isLeaf) {
            for (int i = 0; i <= node.count; i++) {
                count += countNodes(node.childs.get(i));
            }
        }
        return count;
    }
} 