import java.util.Scanner;

public class MenuTestAVL {
  private static AVLTree<Integer> avl = new AVLTree<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("=== EJERCICIO 2: MENÚ DE OPERACIONES AVL ===");

    int opcion;
    do {
      mostrarMenu();
      System.out.print("Seleccione una opción: ");
      opcion = scanner.nextInt();

      switch (opcion) {
        case 1:
          insertarNodo();
          break;
        case 2:
          eliminarNodo();
          break;
        case 3:
          buscarNodo();
          break;
        case 4:
          mostrarMinimo();
          break;
        case 5:
          mostrarMaximo();
          break;
        case 6:
          buscarPredecesor();
          break;
        case 7:
          buscarSucesor();
          break;
        case 8:
          recorridoInOrder();
          break;
        case 9:
          recorridoPreOrder();
          break;
        case 10:
          recorridoPostOrder();
          break;
        case 11:
          mostrarArbol();
          break;
        case 12:
          visualizarArbol();
          break;
        case 13:
          verificarVacio();
          break;
        case 14:
          destruirArbol();
          break;
        case 15:
          System.out.println("¡Hasta luego!");
          break;
        default:
          System.out.println("Opción inválida. Intente de nuevo.");
      }

      if (opcion != 15) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine(); // Consumir el salto de línea
        scanner.nextLine(); // Esperar Enter
      }

    } while (opcion != 15);

    scanner.close();
  }

  private static void mostrarMenu() {
    System.out.println("\n" + "=".repeat(50));
    System.out.println("           MENÚ ÁRBOL AVL");
    System.out.println("=".repeat(50));
    System.out.println("1.  Insertar nodo");
    System.out.println("2.  Eliminar nodo");
    System.out.println("3.  Buscar nodo");
    System.out.println("4.  Mostrar mínimo");
    System.out.println("5.  Mostrar máximo");
    System.out.println("6.  Buscar predecesor");
    System.out.println("7.  Buscar sucesor");
    System.out.println("8.  Recorrido InOrder");
    System.out.println("9.  Recorrido PreOrder");
    System.out.println("10. Recorrido PostOrder");
    System.out.println("11. Mostrar árbol completo");
    System.out.println("12. Visualizar árbol gráficamente");
    System.out.println("13. Verificar si está vacío");
    System.out.println("14. Destruir árbol");
    System.out.println("15. Salir");
    System.out.println("=".repeat(50));
  }

  private static void insertarNodo() {
    System.out.print("Ingrese el valor a insertar: ");
    int valor = scanner.nextInt();
    avl.insert(valor);
    System.out.println("Nodo " + valor + " insertado correctamente.");
  }

  private static void eliminarNodo() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.print("Ingrese el valor a eliminar: ");
    int valor = scanner.nextInt();

    if (avl.search(valor)) {
      avl.delete(valor);
      System.out.println("Nodo " + valor + " eliminado correctamente.");
    } else {
      System.out.println("El nodo " + valor + " no existe en el árbol.");
    }
  }

  private static void buscarNodo() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.print("Ingrese el valor a buscar: ");
    int valor = scanner.nextInt();

    if (avl.search(valor)) {
      System.out.println("El nodo " + valor + " SÍ existe en el árbol.");
    } else {
      System.out.println("El nodo " + valor + " NO existe en el árbol.");
    }
  }

  private static void mostrarMinimo() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    Integer min = avl.min();
    System.out.println("El valor mínimo del árbol es: " + min);
  }

  private static void mostrarMaximo() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    Integer max = avl.max();
    System.out.println("El valor máximo del árbol es: " + max);
  }

  private static void buscarPredecesor() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.print("Ingrese el valor para buscar su predecesor: ");
    int valor = scanner.nextInt();

    if (!avl.search(valor)) {
      System.out.println("El nodo " + valor + " no existe en el árbol.");
      return;
    }

    Integer predecesor = avl.predecessor(valor);
    if (predecesor != null) {
      System.out.println("El predecesor de " + valor + " es: " + predecesor);
    } else {
      System.out.println("El nodo " + valor + " no tiene predecesor.");
    }
  }

  private static void buscarSucesor() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.print("Ingrese el valor para buscar su sucesor: ");
    int valor = scanner.nextInt();

    if (!avl.search(valor)) {
      System.out.println("El nodo " + valor + " no existe en el árbol.");
      return;
    }

    Integer sucesor = avl.successor(valor);
    if (sucesor != null) {
      System.out.println("El sucesor de " + valor + " es: " + sucesor);
    } else {
      System.out.println("El nodo " + valor + " no tiene sucesor.");
    }
  }

  private static void recorridoInOrder() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.println("Recorrido InOrder (izquierda-raíz-derecha):");
    avl.inOrder();
  }

  private static void recorridoPreOrder() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.println("Recorrido PreOrder (raíz-izquierda-derecha):");
    avl.preOrder();
  }

  private static void recorridoPostOrder() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.println("Recorrido PostOrder (izquierda-derecha-raíz):");
    avl.postOrder();
  }

  private static void mostrarArbol() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.println("Estructura del árbol AVL:");
    avl.printTree(avl.getRoot(), "", true);
  }

  private static void verificarVacio() {
    if (avl.isEmpty()) {
      System.out.println("El árbol SÍ está vacío.");
    } else {
      System.out.println("El árbol NO está vacío.");
    }
  }

  private static void visualizarArbol() {
    if (avl.isEmpty()) {
      System.out.println("El árbol está vacío.");
      return;
    }

    System.out.println("Abriendo visualización gráfica del árbol...");
    GraphStreamAVLVisualizer.showAVLTree(avl);
  }

  private static void destruirArbol() {
    if (avl.isEmpty()) {
      System.out.println("El árbol ya está vacío.");
      return;
    }

    System.out.print("¿Está seguro de que desea destruir el árbol? (s/n): ");
    String confirmacion = scanner.next();

    if (confirmacion.toLowerCase().equals("s") || confirmacion.toLowerCase().equals("si")) {
      avl.destroy();
      System.out.println("El árbol ha sido destruido correctamente.");
    } else {
      System.out.println("Operación cancelada.");
    }
  }
}
