import java.util.*;

public class Ejercicio1 {
    
    // Implementación mejorada de B-Tree para visualización clara
    static class VisualBTree<E extends Comparable<E>> {
        private Node root;
        private int degree;
        
        public VisualBTree(int degree) {
            this.degree = degree;
            this.root = null;
        }
        
        class Node {
            List<E> keys;
            List<Node> children;
            boolean isLeaf;
            
            Node(boolean isLeaf) {
                this.isLeaf = isLeaf;
                this.keys = new ArrayList<>();
                this.children = new ArrayList<>();
            }
            
            @Override
            public String toString() {
                return keys.toString();
            }
        }
        
        public void insert(E key) {
            if (root == null) {
                root = new Node(true);
                root.keys.add(key);
            } else {
                if (root.keys.size() == degree - 1) {
                    Node newRoot = new Node(false);
                    newRoot.children.add(root);
                    splitChild(newRoot, 0);
                    root = newRoot;
                }
                insertNonFull(root, key);
            }
        }
        
        private void insertNonFull(Node node, E key) {
            int i = node.keys.size() - 1;
            
            if (node.isLeaf) {
                node.keys.add(null);
                while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                    node.keys.set(i + 1, node.keys.get(i));
                    i--;
                }
                node.keys.set(i + 1, key);
            } else {
                while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                    i--;
                }
                i++;
                
                if (node.children.get(i).keys.size() == degree - 1) {
                    splitChild(node, i);
                    if (key.compareTo(node.keys.get(i)) > 0) {
                        i++;
                    }
                }
                insertNonFull(node.children.get(i), key);
            }
        }
        
        private void splitChild(Node parent, int index) {
            Node fullChild = parent.children.get(index);
            Node newChild = new Node(fullChild.isLeaf);
            
            int mid = degree / 2;
            
            for (int i = mid; i < degree - 1; i++) {
                newChild.keys.add(fullChild.keys.get(i));
            }
            
            if (!fullChild.isLeaf) {
                for (int i = mid; i < degree; i++) {
                    newChild.children.add(fullChild.children.get(i));
                }
                for (int i = degree - 1; i >= mid; i--) {
                    fullChild.children.remove(i);
                }
            }
            
            for (int i = degree - 2; i >= mid; i--) {
                fullChild.keys.remove(i);
            }
            
            parent.children.add(index + 1, newChild);
            parent.keys.add(index, fullChild.keys.get(mid - 1));
            fullChild.keys.remove(mid - 1);
        }
        
        public boolean isEmpty() {
            return root == null;
        }
        
        public boolean search(E key) {
            return search(root, key);
        }
        
        private boolean search(Node node, E key) {
            if (node == null) return false;
            
            int i = 0;
            while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
                i++;
            }
            
            if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
                return true;
            }
            
            if (node.isLeaf) return false;
            
            return search(node.children.get(i), key);
        }
        
        public E Min() {
            if (isEmpty()) return null;
            Node current = root;
            while (!current.isLeaf) {
                current = current.children.get(0);
            }
            return current.keys.get(0);
        }
        
        public E Max() {
            if (isEmpty()) return null;
            Node current = root;
            while (!current.isLeaf) {
                current = current.children.get(current.children.size() - 1);
            }
            return current.keys.get(current.keys.size() - 1);
        }
        
        public void remove(E key) {
            if (root != null) {
                removeFromTree(root, key);
                if (root.keys.isEmpty() && !root.isLeaf) {
                    root = root.children.get(0);
                }
            }
        }
        
        private void removeFromTree(Node node, E key) {
            int i = 0;
            while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
                i++;
            }
            
            if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
                if (node.isLeaf) {
                    node.keys.remove(i);
                } else {
                    // Reemplazar con predecesor
                    Node predNode = node.children.get(i);
                    while (!predNode.isLeaf) {
                        predNode = predNode.children.get(predNode.children.size() - 1);
                    }
                    if (!predNode.keys.isEmpty()) {
                        E pred = predNode.keys.get(predNode.keys.size() - 1);
                        node.keys.set(i, pred);
                        removeFromTree(node.children.get(i), pred);
                    }
                }
            } else if (!node.isLeaf) {
                removeFromTree(node.children.get(i), key);
            }
        }
        
        @Override
        public String toString() {
            if (isEmpty()) return "Árbol B vacío";
            return printTree(root, "", true, 0);
        }
        
        private String printTree(Node node, String prefix, boolean isLast, int level) {
            if (node == null) return "";
            
            StringBuilder result = new StringBuilder();
            
            // Imprimir el nodo actual
            result.append(prefix);
            result.append(isLast ? "└── " : "├── ");
            result.append("Nivel ").append(level).append(": ");
            result.append(node.keys);
            if (node.isLeaf) result.append(" (HOJA)");
            result.append("\n");
            
            // Imprimir los hijos
            if (!node.isLeaf) {
                for (int i = 0; i < node.children.size(); i++) {
                    boolean isLastChild = (i == node.children.size() - 1);
                    String newPrefix = prefix + (isLast ? "    " : "│   ");
                    result.append(printTree(node.children.get(i), newPrefix, isLastChild, level + 1));
                }
            }
            
            return result.toString();
        }
        
        public int getHeight() {
            return getHeight(root);
        }
        
        private int getHeight(Node node) {
            if (node == null || node.isLeaf) return 1;
            return 1 + getHeight(node.children.get(0));
        }
        
        public int countNodes() {
            return countNodes(root);
        }
        
        private int countNodes(Node node) {
            if (node == null) return 0;
            int count = 1;
            if (!node.isLeaf) {
                for (Node child : node.children) {
                    count += countNodes(child);
                }
            }
            return count;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Ejercicio 1: Árbol B grado 5");
        System.out.println("Datos: 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190\n");
        
        VisualBTree<Integer> btree = new VisualBTree<>(5);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        System.out.println("INSERCIÓN:");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%d. Insertar %d:\n", i + 1, datos[i]);
            btree.insert(datos[i]);
            System.out.println(indentLines(btree.toString(), "  "));
        }
        
        System.out.println("\nÁRBOL FINAL:");
        System.out.println(btree.toString());
        System.out.printf("Min: %d | Max: %d | Altura: %d | Nodos: %d\n", 
                         btree.Min(), btree.Max(), btree.getHeight(), btree.countNodes());
        
        System.out.println("\nELIMINACIÓN:");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%d. Eliminar %d:\n", i + 1, datos[i]);
            btree.remove(datos[i]);
            if (btree.isEmpty()) {
                System.out.println("  VACÍO\n");
            } else {
                System.out.println(indentLines(btree.toString(), "  "));
            }
        }
    }
    
    // Método auxiliar para indentar líneas
    private static String indentLines(String text, String indent) {
        if (text == null || text.isEmpty()) return text;
        return text.replaceAll("(?m)^", indent);
    }
}