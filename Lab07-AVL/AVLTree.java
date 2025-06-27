public class AVLTree<T extends Comparable<T>> {
    private NodeAVL<T> root;
    
    public AVLTree() {
        this.root = null;
    }
    
    public NodeAVL<T> getRoot() {
        return root;
    }
    
    public void insert(T data) {
        System.out.println("Insertando: " + data);
        root = insertRec(root, data);
        System.out.println("Árbol después de insertar " + data + ":");
        printTree(root, "", true);
        System.out.println();
    }
    
    private NodeAVL<T> insertRec(NodeAVL<T> node, T data) {
        if (node == null) {
            return new NodeAVL<>(data);
        }
        
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insertRec(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insertRec(node.getRight(), data));
        } else {
            return node;
        }
        
        updateHeight(node);
        
        int balance = getBalance(node);
        
        if (balance > 1 && data.compareTo(node.getLeft().getData()) < 0) {
            System.out.println("Rotación simple a la derecha en nodo: " + node.getData());
            return rotateRight(node);
        }
        
        if (balance < -1 && data.compareTo(node.getRight().getData()) > 0) {
            System.out.println("Rotación simple a la izquierda en nodo: " + node.getData());
            return rotateLeft(node);
        }
        
        if (balance > 1 && data.compareTo(node.getLeft().getData()) > 0) {
            System.out.println("Rotación izquierda-derecha en nodo: " + node.getData());
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }
        
        if (balance < -1 && data.compareTo(node.getRight().getData()) < 0) {
            System.out.println("Rotación derecha-izquierda en nodo: " + node.getData());
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }
        
        return node;
    }
    
    public NodeAVL<T> rotateLeft(NodeAVL<T> y) {
        NodeAVL<T> x = y.getRight();
        NodeAVL<T> T2 = x.getLeft();
        
        x.setLeft(y);
        y.setRight(T2);
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    public NodeAVL<T> rotateRight(NodeAVL<T> x) {
        NodeAVL<T> y = x.getLeft();
        NodeAVL<T> T2 = y.getRight();
        
        y.setRight(x);
        x.setLeft(T2);
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    private void updateHeight(NodeAVL<T> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        }
    }
    
    private int height(NodeAVL<T> node) {
        return node == null ? 0 : node.getHeight();
    }
    
    private int getBalance(NodeAVL<T> node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }
    
    public void inOrder() {
        System.out.print("InOrder: ");
        inOrderRec(root);
        System.out.println();
    }
    
    private void inOrderRec(NodeAVL<T> node) {
        if (node != null) {
            inOrderRec(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrderRec(node.getRight());
        }
    }
    
    public void preOrder() {
        System.out.print("PreOrder: ");
        preOrderRec(root);
        System.out.println();
    }
    
    private void preOrderRec(NodeAVL<T> node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrderRec(node.getLeft());
            preOrderRec(node.getRight());
        }
    }
    
    public void postOrder() {
        System.out.print("PostOrder: ");
        postOrderRec(root);
        System.out.println();
    }
    
    private void postOrderRec(NodeAVL<T> node) {
        if (node != null) {
            postOrderRec(node.getLeft());
            postOrderRec(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }
    
    public void delete(T data) {
        System.out.println("Eliminando: " + data);
        root = deleteRec(root, data);
        System.out.println("Árbol después de eliminar " + data + ":");
        printTree(root, "", true);
        System.out.println();
    }
    
    private NodeAVL<T> deleteRec(NodeAVL<T> node, T data) {
        if (node == null) {
            return node;
        }
        
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(deleteRec(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(deleteRec(node.getRight(), data));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                NodeAVL<T> temp = null;
                if (temp == node.getLeft()) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }
                
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                NodeAVL<T> temp = minValueNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(deleteRec(node.getRight(), temp.getData()));
            }
        }
        
        if (node == null) {
            return node;
        }
        
        updateHeight(node);
        
        int balance = getBalance(node);
        
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            System.out.println("Rotación simple a la derecha en nodo: " + node.getData());
            return rotateRight(node);
        }
        
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            System.out.println("Rotación izquierda-derecha en nodo: " + node.getData());
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }
        
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            System.out.println("Rotación simple a la izquierda en nodo: " + node.getData());
            return rotateLeft(node);
        }
        
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            System.out.println("Rotación derecha-izquierda en nodo: " + node.getData());
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }
        
        return node;
    }
    
    private NodeAVL<T> minValueNode(NodeAVL<T> node) {
        NodeAVL<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
    
    public void printTree(NodeAVL<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.getData() + " (h:" + node.getHeight() + ")");
            if (node.getLeft() != null || node.getRight() != null) {
                if (node.getLeft() != null) {
                    printTree(node.getLeft(), prefix + (isLast ? "    " : "│   "), node.getRight() == null);
                }
                if (node.getRight() != null) {
                    printTree(node.getRight(), prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    public void destroy() {
        root = null;
        System.out.println("Árbol destruido");
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public boolean search(T data) {
        return searchRec(root, data) != null;
    }
    
    private NodeAVL<T> searchRec(NodeAVL<T> node, T data) {
        if (node == null || data.compareTo(node.getData()) == 0) {
            return node;
        }
        
        if (data.compareTo(node.getData()) < 0) {
            return searchRec(node.getLeft(), data);
        }
        
        return searchRec(node.getRight(), data);
    }
    
    public T min() {
        if (isEmpty()) {
            return null;
        }
        return minValueNode(root).getData();
    }
    
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return maxValueNode(root).getData();
    }
    
    private NodeAVL<T> maxValueNode(NodeAVL<T> node) {
        NodeAVL<T> current = node;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }
    
    public T predecessor(T data) {
        NodeAVL<T> node = searchRec(root, data);
        if (node == null) {
            return null;
        }
        
        if (node.getLeft() != null) {
            return maxValueNode(node.getLeft()).getData();
        }
        
        NodeAVL<T> predecessor = null;
        NodeAVL<T> ancestor = root;
        
        while (ancestor != node) {
            if (data.compareTo(ancestor.getData()) > 0) {
                predecessor = ancestor;
                ancestor = ancestor.getRight();
            } else {
                ancestor = ancestor.getLeft();
            }
        }
        
        return predecessor != null ? predecessor.getData() : null;
    }
    
    public T successor(T data) {
        NodeAVL<T> node = searchRec(root, data);
        if (node == null) {
            return null;
        }
        
        if (node.getRight() != null) {
            return minValueNode(node.getRight()).getData();
        }
        
        NodeAVL<T> successor = null;
        NodeAVL<T> ancestor = root;
        
        while (ancestor != node) {
            if (data.compareTo(ancestor.getData()) < 0) {
                successor = ancestor;
                ancestor = ancestor.getLeft();
            } else {
                ancestor = ancestor.getRight();
            }
        }
        
        return successor != null ? successor.getData() : null;
    }
    
    public NodeAVL<T> balancearIzquierda(NodeAVL<T> node) {
        if (node == null) return node;
        
        int leftBalance = getBalance(node.getLeft());
        
        if (leftBalance >= 0) {
            System.out.println("Balanceando izquierda - Rotación simple derecha en: " + node.getData());
            return rotacionSimpleDerecha(node);
        } else {
            System.out.println("Balanceando izquierda - Rotación doble izquierda-derecha en: " + node.getData());
            node.setLeft(rotacionSimpleIzquierda(node.getLeft()));
            return rotacionSimpleDerecha(node);
        }
    }
    
    public NodeAVL<T> balancearDerecha(NodeAVL<T> node) {
        if (node == null) return node;
        
        int rightBalance = getBalance(node.getRight());
        
        if (rightBalance <= 0) {
            System.out.println("Balanceando derecha - Rotación simple izquierda en: " + node.getData());
            return rotacionSimpleIzquierda(node);
        } else {
            System.out.println("Balanceando derecha - Rotación doble derecha-izquierda en: " + node.getData());
            node.setRight(rotacionSimpleDerecha(node.getRight()));
            return rotacionSimpleIzquierda(node);
        }
    }
    
    public NodeAVL<T> rotacionSimpleIzquierda(NodeAVL<T> y) {
        NodeAVL<T> x = y.getRight();
        NodeAVL<T> T2 = x.getLeft();
        
        x.setLeft(y);
        y.setRight(T2);
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    public NodeAVL<T> rotacionSimpleDerecha(NodeAVL<T> x) {
        NodeAVL<T> y = x.getLeft();
        NodeAVL<T> T2 = y.getRight();
        
        y.setRight(x);
        x.setLeft(T2);
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
}