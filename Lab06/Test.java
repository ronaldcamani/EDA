public class Test {
  public static void main(String[] args) {
    // Crear un BST de enteros
    BST<Integer> bst = new BST<>();

    // Probar isEmpty() con árbol vacío
    System.out.println("1. PRUEBA isEmpty() - Árbol vacío:");
    System.out.println("¿Está vacío? " + bst.isEmpty());
    System.out.println();

    // Insertar elementos
    System.out.println("2. PRUEBA insert() - Insertando elementos:");
    int[] valores = { 50, 30, 70, 20, 40, 60, 80 };
    System.out.print("Insertando: ");
    for (int valor : valores) {
      bst.insert(valor);
      System.out.print(valor + " ");
    }
    System.out.println();
    System.out.println();

    // Probar isEmpty() con árbol con elementos
    System.out.println("3. PRUEBA isEmpty() - Árbol con elementos:");
    System.out.println("¿Está vacío? " + bst.isEmpty());
    System.out.println();

    // Mostrar recorridos
    System.out.println("4. PRUEBA DE RECORRIDOS:");
    bst.inOrder();
    bst.preOrder();
    bst.postOrder();
    System.out.println();

    // Probar búsqueda
    System.out.println("5. PRUEBA search():");
    int[] buscar = { 40, 25, 60, 100 };
    for (int valor : buscar) {
      boolean encontrado = bst.search(valor);
      System.out.println("Buscar " + valor + ": " + (encontrado ? "Encontrado" : "No encontrado"));
    }
    System.out.println();

    // Probar min y max
    System.out.println("6. PRUEBA min() y max():");
    System.out.println("Mínimo: " + bst.min());
    System.out.println("Máximo: " + bst.max());
    System.out.println();

    // Probar predecesor y sucesor
    System.out.println("7. PRUEBA predecessor() y successor():");
    int[] testValues = { 30, 50, 70 };
    for (int valor : testValues) {
      Integer pred = bst.predecessor(valor);
      Integer succ = bst.successor(valor);
      System.out.println("Valor: " + valor + ", Predecesor: " + pred + ", Sucesor: " + succ);
    }
    System.out.println();

    // Probar eliminación
    System.out.println("8. PRUEBA remove():");
    System.out.println("Antes de eliminar 20:");
    bst.inOrder();

    bst.remove(20);
    System.out.println("Después de eliminar 20:");
    bst.inOrder();

    System.out.println("Antes de eliminar 30 (nodo con dos hijos):");
    bst.inOrder();

    bst.remove(30);
    System.out.println("Después de eliminar 30:");
    bst.inOrder();
    System.out.println();

    // Probar con Strings
    System.out.println("9. PRUEBA CON STRINGS:");
    BST<String> bstString = new BST<>();
    String[] palabras = { "manzana", "banana", "naranja", "kiwi", "uva" };

    System.out.print("Insertando palabras: ");
    for (String palabra : palabras) {
      bstString.insert(palabra);
      System.out.print(palabra + " ");
    }
    System.out.println();

    System.out.println("Recorrido InOrder (orden alfabético):");
    bstString.inOrder();

    System.out.println("Mínimo: " + bstString.min());
    System.out.println("Máximo: " + bstString.max());
    System.out.println();

    // Probar destroy()
    System.out.println("10. PRUEBA destroy():");
    System.out.println("Antes de destroy - ¿Está vacío? " + bst.isEmpty());
    bst.destroy();
    System.out.println("Después de destroy - ¿Está vacío? " + bst.isEmpty());
  }
} 