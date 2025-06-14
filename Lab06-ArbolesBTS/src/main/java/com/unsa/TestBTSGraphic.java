package com.unsa;
public class TestBTSGraphic {
  public static void main(String[] args) {
      // Crear un BST de enteros
      BST<Integer> bst = new BST<>();
      
      // Insertar algunos valores
      int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 65};
      
      System.out.println("Insertando valores: ");
      for (int value : values) {
          System.out.print(value + " ");
          bst.insert(value);
      }
      System.out.println("\n");
      
      // Visualizar el árbol
      System.out.println("Visualizando el BST...");
      bst.visualizeTreeWithInfo();
      
      // Esperar un poco antes de hacer modificaciones
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      
      // Demostrar algunas operaciones
      System.out.println("\n=== OPERACIONES ===");
      System.out.println("Buscando 40: " + bst.search(40));
      System.out.println("Buscando 90: " + bst.search(90));
      System.out.println("Predecesor de 50: " + bst.predecessor(50));
      System.out.println("Sucesor de 50: " + bst.successor(50));
      
      // Eliminar un nodo y visualizar nuevamente
      System.out.println("\nEliminando el nodo 30...");
      bst.remove(30);
      bst.visualizeTree();
      
  }
}