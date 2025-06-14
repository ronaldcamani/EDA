package com.unsa;

public class BST<T extends Comparable<T>> {
  private Node<T> root;

  // Constructor
  public BST() {
    this.root = null;
  }

  // destroy() - Destruye el árbol
  public void destroy() {
    root = null;
    System.gc(); // Sugiere al garbage collector que libere memoria
  }

  // isEmpty() - Verifica si el árbol está vacío
  public boolean isEmpty() {
    return root == null;
  }

  // insert(x) - Inserta un elemento en el árbol
  public void insert(T data) {
    root = insertRec(root, data);
  }

  private Node<T> insertRec(Node<T> root, T data) {
    if (root == null) {
      root = new Node<>(data);
      return root;
    }

    if (data.compareTo(root.getData()) < 0) {
      root.setLeft(insertRec(root.getLeft(), data));
    } else if (data.compareTo(root.getData()) > 0) {
      root.setRight(insertRec(root.getRight(), data));
    }

    return root;
  }

  // remove(x) - Elimina un elemento del árbol
  public void remove(T data) {
    root = removeRec(root, data);
  }

  private Node<T> removeRec(Node<T> root, T data) {
    if (root == null) {
      return root;
    }

    if (data.compareTo(root.getData()) < 0) {
      root.setLeft(removeRec(root.getLeft(), data));
    } else if (data.compareTo(root.getData()) > 0) {
      root.setRight(removeRec(root.getRight(), data));
    } else {
      // Nodo con un solo hijo o sin hijos
      if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      }

      // Nodo con dos hijos: obtener el sucesor inorder
      root.setData(minValue(root.getRight()));

      // Eliminar el sucesor inorder
      root.setRight(removeRec(root.getRight(), root.getData()));
    }

    return root;
  }

  // search(x) - Busca un elemento en el árbol
  public boolean search(T data) {
    return searchRec(root, data);
  }

  private boolean searchRec(Node<T> root, T data) {
    if (root == null) {
      return false;
    }

    if (data.compareTo(root.getData()) == 0) {
      return true;
    }

    if (data.compareTo(root.getData()) < 0) {
      return searchRec(root.getLeft(), data);
    }

    return searchRec(root.getRight(), data);
  }

  // Min() - Encuentra el valor mínimo
  public T min() {
    if (isEmpty()) {
      return null;
    }
    return minValue(root);
  }

  private T minValue(Node<T> root) {
    T minval = root.getData();
    while (root.getLeft() != null) {
      minval = root.getLeft().getData();
      root = root.getLeft();
    }
    return minval;
  }

  // Max() - Encuentra el valor máximo
  public T max() {
    if (isEmpty()) {
      return null;
    }
    return maxValue(root);
  }

  private T maxValue(Node<T> root) {
    T maxval = root.getData();
    while (root.getRight() != null) {
      maxval = root.getRight().getData();
      root = root.getRight();
    }
    return maxval;
  }

  // Predecesor() - Encuentra el predecesor de un valor
  public T predecessor(T data) {
    Node<T> node = findNode(root, data);
    if (node == null) {
      return null;
    }
    return predecessorRec(node);
  }

  private T predecessorRec(Node<T> node) {
    if (node.getLeft() != null) {
      return maxValue(node.getLeft());
    }
    return null;
  }

  // Sucesor() - Encuentra el sucesor de un valor
  public T successor(T data) {
    Node<T> node = findNode(root, data);
    if (node == null) {
      return null;
    }
    return successorRec(node);
  }

  private T successorRec(Node<T> node) {
    if (node.getRight() != null) {
      return minValue(node.getRight());
    }
    return null;
  }

  private Node<T> findNode(Node<T> root, T data) {
    if (root == null || data.compareTo(root.getData()) == 0) {
      return root;
    }

    if (data.compareTo(root.getData()) < 0) {
      return findNode(root.getLeft(), data);
    }

    return findNode(root.getRight(), data);
  }

  // InOrder() - Recorrido inorden
  public void inOrder() {
    System.out.print("InOrder: ");
    inOrderRec(root);
    System.out.println();
  }

  private void inOrderRec(Node<T> root) {
    if (root != null) {
      inOrderRec(root.getLeft());
      System.out.print(root.getData() + " ");
      inOrderRec(root.getRight());
    }
  }

  // PreOrder() - Recorrido preorden
  public void preOrder() {
    System.out.print("PreOrder: ");
    preOrderRec(root);
    System.out.println();
  }

  private void preOrderRec(Node<T> root) {
    if (root != null) {
      System.out.print(root.getData() + " ");
      preOrderRec(root.getLeft());
      preOrderRec(root.getRight());
    }
  }

  // PostOrder() - Recorrido postorden
  public void postOrder() {
    System.out.print("PostOrder: ");
    postOrderRec(root);
    System.out.println();
  }

  private void postOrderRec(Node<T> root) {
    if (root != null) {
      postOrderRec(root.getLeft());
      postOrderRec(root.getRight());
      System.out.print(root.getData() + " ");
    }
  }

  // Método para obtener la raíz (usado por el visualizador)
  public Node<T> getRoot() {
    return root;
  }

  // Métodos de visualización
  public void visualizeTree() {
    BSTVisualizer<T> visualizer = new BSTVisualizer<>(this);
    visualizer.visualize();
  }

  public void visualizeTreeWithInfo() {
    System.out.println("\nInformación del árbol:");
    System.out.println("Recorrido InOrder:");
    inOrder();
    System.out.println("Valor mínimo: " + min());
    System.out.println("Valor máximo: " + max());
    visualizeTree();
  }
}
