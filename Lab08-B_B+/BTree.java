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
    
    public BNode<E> getRoot() {
        return this.root;
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
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
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
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    public String toString() {
        String s = "";
        if (isEmpty()) {
            s += "BTree is empty...";
        } else {
            s = writeTree(this.root, 0);
        }
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

    public void destroy() {
        root = null;
    }

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

    public E Min() {
        if (isEmpty()) return null;
        BNode<E> node = root;
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node.keys.get(0);
    }

    public E Max() {
        if (isEmpty()) return null;
        BNode<E> node = root;
        while (node.childs.get(node.count) != null) {
            node = node.childs.get(node.count);
        }
        return node.keys.get(node.count - 1);
    }

    public E Predecesor(E key) {
        return null;
    }

    public E Sucesor(E key) {
        return null;
    }

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
            if (node.childs.get(0) == null) {
                for (int i = pos[0]; i < node.count - 1; i++) {
                    node.keys.set(i, node.keys.get(i + 1));
                }
                node.keys.set(node.count - 1, null);
                node.count--;
            } else {
                // Buscar el sucesor (menor del hijo derecho)
                BNode<E> succNode = node.childs.get(pos[0] + 1);
                while (succNode.childs.get(0) != null) {
                    succNode = succNode.childs.get(0);
                }
                if (succNode.count > 0) {
                    E succ = succNode.keys.get(0);
                    node.keys.set(pos[0], succ);
                    remove(node.childs.get(pos[0] + 1), succ);
                } else {
                    // Handle empty node case
                    for (int i = pos[0]; i < node.count - 1; i++) {
                        node.keys.set(i, node.keys.get(i + 1));
                    }
                    node.keys.set(node.count - 1, null);
                    node.count--;
                }
            }
        } else {
            if (pos[0] >= 0 && pos[0] < node.childs.size()) {
                BNode<E> child = node.childs.get(pos[0]);
                if (child == null) return;
                remove(child, key);
                if (child.count < (orden - 1) / 2) {
                    fixChild(node, pos[0]);
                }
            }
        }
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BNode<E> node) {
        if (node == null) return 0;
        return 1 + getHeight(node.childs.get(0));
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(BNode<E> node) {
        if (node == null) return 0;
        int count = 1;
        for (int i = 0; i <= node.count; i++) {
            count += countNodes(node.childs.get(i));
        }
        return count;
    }

    private void fixChild(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> leftSibling = idx > 0 ? parent.childs.get(idx - 1) : null;
        BNode<E> rightSibling = idx < parent.count ? parent.childs.get(idx + 1) : null;
        int minKeys = (orden - 1) / 2;
        
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
            if (leftSibling.childs.get(0) != null) {
                leftSibling.childs.set(leftSibling.count, null);
            }
            leftSibling.count--;
        } else if (rightSibling != null && rightSibling.count > minKeys) {
            child.keys.set(child.count, parent.keys.get(idx));
            if (child.childs.get(0) != null) {
                child.childs.set(child.count + 1, rightSibling.childs.get(0));
            }
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