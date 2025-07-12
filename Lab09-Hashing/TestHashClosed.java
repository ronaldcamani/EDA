public class TestHashClosed {
  public static void main(String[] args) {
    System.out.println("=== PRUEBA DE HASH CERRADO (SONDEO LINEAL) ===\n");

    HashClosed<String> hashTable = new HashClosed<>(13);

    System.out.println("1. AGREGANDO ELEMENTOS:");
    System.out.println("Elementos: 100, 5, 14, 15, 22, 16, 17, 32, 13, 32, 100");

    int[] keys = { 100, 5, 14, 15, 22, 16, 17, 32, 13, 32, 100 };

    for (int key : keys) {
      hashTable.add(key, "Valor" + key);
    }

    hashTable.display();

    System.out.println("2. BÚSQUEDA DE ELEMENTOS:");
    System.out.println("Buscando elemento 32:");
    Register<String> found32 = hashTable.search(32);
    if (found32 != null) {
      System.out.println("Encontrado: " + found32);
    } else {
      System.out.println("Elemento 32 no encontrado");
    }

    System.out.println("\nBuscando elemento 200:");
    Register<String> found200 = hashTable.search(200);
    if (found200 != null) {
      System.out.println("Encontrado: " + found200);
    } else {
      System.out.println("Elemento 200 no encontrado");
    }

    System.out.println("\n3. ELIMINANDO ELEMENTOS:");
    System.out.println("Eliminando elemento 17:");
    hashTable.delete(17);

    System.out.println("\nEliminando elemento 100:");
    hashTable.delete(100);

    hashTable.display();

    System.out.println("4. PRUEBAS ADICIONALES:");

    hashTable.add(1, "String" + 10);
    hashTable.add(2, "String" + 20);
    hashTable.add(3, "String" + 30);
    hashTable.add(4, "String" + 40);
    hashTable.add(5, "String" + 50);
    hashTable.add(6, "String" + 60);
    hashTable.add(7, "String" + 70);
    hashTable.add(8, "String" + 80);

    hashTable.display();
  }
}
