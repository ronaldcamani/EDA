public class TestHashOpened {
  public static void main(String[] args) {
    System.out.println("=== PRUEBA DE HASH ABIERTO (ENCADENAMIENTO) ===\n");

    HashOpened<String> hashTable = new HashOpened<>(8);

    System.out.println("1. AGREGANDO ELEMENTOS:");
    System.out.println("Tamaño de la tabla: 8");

    hashTable.insert(new RegisterOpen<>(5, "Pepe"));
    hashTable.insert(new RegisterOpen<>(21, "Jesús"));
    hashTable.insert(new RegisterOpen<>(19, "Juan"));
    hashTable.insert(new RegisterOpen<>(16, "María"));
    hashTable.insert(new RegisterOpen<>(21, "DUPLICADO"));

    hashTable.showTable();

    System.out.println("2. BÚSQUEDA DE ELEMENTOS:");
    System.out.println("Buscando elemento con clave 5:");
    RegisterOpen<String> found5 = hashTable.search(5);
    if (found5 != null) {
      System.out.println("Encontrado: " + found5);
    } else {
      System.out.println("Elemento con clave 5 no encontrado");
    }

    System.out.println("\nBuscando elemento con clave 21:");
    RegisterOpen<String> found21 = hashTable.search(21);
    if (found21 != null) {
      System.out.println("Encontrado: " + found21);
    } else {
      System.out.println("Elemento con clave 21 no encontrado");
    }

    System.out.println("\n3. ELIMINANDO ELEMENTOS:");
    System.out.println("Eliminando elemento con clave 21:");
    hashTable.delete(21);

    System.out.println("\nEliminando elemento con clave 100:");
    hashTable.delete(100);

    hashTable.showTable();

    System.out.println("4. PRUEBAS ADICIONALES:");

    HashOpened<Integer> hashTableInt = new HashOpened<>(5);
    System.out.println("\nProbando con tabla de enteros (tamaño 5):");

    hashTableInt.insert(new RegisterOpen<>(10, 100));
    hashTableInt.insert(new RegisterOpen<>(15, 150));
    hashTableInt.insert(new RegisterOpen<>(20, 200));
    hashTableInt.insert(new RegisterOpen<>(25, 250));

    hashTableInt.showTable();

    HashOpened<Double> hashTableDouble = new HashOpened<>(6);
    System.out.println("Probando con tabla de doubles (tamaño 6):");

    hashTableDouble.insert(new RegisterOpen<>(7, 7.7));
    hashTableDouble.insert(new RegisterOpen<>(13, 13.13));
    hashTableDouble.insert(new RegisterOpen<>(19, 19.19));

    hashTableDouble.showTable();

    System.out.println("=== FIN DE PRUEBAS ===");
  }
}
