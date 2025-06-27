import java.util.Scanner;

public class GraphicalTestAVL {
  public static void main(String[] args) {
    System.out.println("=== EJERCICIO 3: VISUALIZACIÓN GRÁFICA DEL ÁRBOL AVL ===");

    AVLTree<Integer> avl = new AVLTree<>();
    Scanner scanner = new Scanner(System.in);

    // Insertar algunos valores de ejemplo
    System.out.println("Insertando valores de ejemplo: 50, 30, 70, 20, 40, 60, 80");
    int[] valores = { 50, 30, 70, 20, 40, 60, 80 };

    for (int valor : valores) {
      avl.insert(valor);
    }

    System.out.println("\nÁrbol creado con valores de ejemplo.");
    System.out.println("Estructura del árbol:");
    avl.printTree(avl.getRoot(), "", true);

    System.out.println("\n=== MENÚ DE VISUALIZACIÓN ===");

    int opcion;
    do {
      mostrarMenuVisualizacion();
      System.out.print("Seleccione una opción: ");
      opcion = scanner.nextInt();

      switch (opcion) {
        case 1:
          insertarYVisualizar(avl, scanner);
          break;
        case 2:
          eliminarYVisualizar(avl, scanner);
          break;
        case 3:
          System.out.println("Mostrando visualización gráfica...");
          GraphStreamAVLVisualizer.showAVLTree(avl);
          break;
        case 4:
          mostrarRecorridos(avl);
          break;
        case 5:
          crearArbolPersonalizado(avl, scanner);
          break;
        case 6:
          demonstrarEjercicio1(avl);
          break;
        case 7:
          avl.destroy();
          System.out.println("Árbol limpiado.");
          break;
        case 8:
          System.out.println("¡Hasta luego!");
          break;
        default:
          System.out.println("Opción inválida.");
      }

      if (opcion != 8) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
      }

    } while (opcion != 8);

    scanner.close();
  }

  private static void mostrarMenuVisualizacion() {
    System.out.println("\n" + "=".repeat(50));
    System.out.println("        MENÚ VISUALIZACIÓN AVL");
    System.out.println("=".repeat(50));
    System.out.println("1. Insertar valor y visualizar");
    System.out.println("2. Eliminar valor y visualizar");
    System.out.println("3. Mostrar visualización gráfica");
    System.out.println("4. Mostrar recorridos");
    System.out.println("5. Crear árbol personalizado");
    System.out.println("6. Demostrar Ejercicio 1");
    System.out.println("7. Limpiar árbol");
    System.out.println("8. Salir");
    System.out.println("=".repeat(50));
  }

  private static void insertarYVisualizar(AVLTree<Integer> avl, Scanner scanner) {
    System.out.print("Ingrese el valor a insertar: ");
    int valor = scanner.nextInt();

    System.out.println("\n--- ANTES DE LA INSERCIÓN ---");
    if (!avl.isEmpty()) {
      avl.printTree(avl.getRoot(), "", true);
    } else {
      System.out.println("Árbol vacío");
    }

    System.out.println("\n--- PROCESO DE INSERCIÓN ---");
    avl.insert(valor);

    System.out.println("\n--- DESPUÉS DE LA INSERCIÓN ---");
    avl.printTree(avl.getRoot(), "", true);

    System.out.println("\nMostrando visualización gráfica...");
    GraphStreamAVLVisualizer.showAVLTree(avl);
  }

  private static void eliminarYVisualizar(AVLTree<Integer> avl, Scanner scanner) {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.print("Ingrese el valor a eliminar: ");
    int valor = scanner.nextInt();

    if (!avl.search(valor)) {
      System.out.println("El valor no existe en el árbol.");
      return;
    }

    System.out.println("\n--- ANTES DE LA ELIMINACIÓN ---");
    avl.printTree(avl.getRoot(), "", true);

    System.out.println("\n--- PROCESO DE ELIMINACIÓN ---");
    avl.delete(valor);

    System.out.println("\n--- DESPUÉS DE LA ELIMINACIÓN ---");
    if (!avl.isEmpty()) {
      avl.printTree(avl.getRoot(), "", true);
    } else {
      System.out.println("Árbol vacío");
    }

    System.out.println("\nMostrando visualización gráfica...");
    GraphStreamAVLVisualizer.showAVLTree(avl);
  }

  private static void mostrarRecorridos(AVLTree<Integer> avl) {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.println("\n=== RECORRIDOS DEL ÁRBOL ===");
    avl.inOrder();
    avl.preOrder();
    avl.postOrder();

    System.out.println("\nEstructura visual:");
    avl.printTree(avl.getRoot(), "", true);
  }

  private static void crearArbolPersonalizado(AVLTree<Integer> avl, Scanner scanner) {
    avl.destroy();

    System.out.println("Creando un nuevo árbol personalizado...");
    System.out.print("¿Cuántos valores desea insertar? ");
    int cantidad = scanner.nextInt();

    System.out.println("Ingrese los valores uno por uno:");
    for (int i = 0; i < cantidad; i++) {
      System.out.print("Valor " + (i + 1) + ": ");
      int valor = scanner.nextInt();
      avl.insert(valor);
    }

    System.out.println("\nÁrbol personalizado creado:");
    avl.printTree(avl.getRoot(), "", true);

    System.out.println("\nMostrando visualización gráfica...");
    GraphStreamAVLVisualizer.showAVLTree(avl);
  }

  private static void demonstrarEjercicio1(AVLTree<Integer> avl) {
    System.out.println("\n=== DEMOSTRACIÓN DEL EJERCICIO 1 ===");
    avl.destroy();

    System.out.println("Insertando valores del Ejercicio 1:");
    System.out.println("100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190");

    int[] valores = { 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190 };

    for (int valor : valores) {
      System.out.println("\nInsertando: " + valor);
      avl.insert(valor);

      System.out.println("Árbol resultante:");
      avl.printTree(avl.getRoot(), "", true);

      try {
        Thread.sleep(1000); // Pausa de 1 segundo
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    System.out.println("\n=== ÁRBOL FINAL DEL EJERCICIO 1 ===");
    avl.printTree(avl.getRoot(), "", true);

    System.out.println("\nRecorridos:");
    avl.inOrder();
    avl.preOrder();
    avl.postOrder();

    System.out.println("\nMostrando visualización gráfica final...");
    GraphStreamAVLVisualizer.showAVLTree(avl);
  }
}
