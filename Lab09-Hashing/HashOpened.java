import java.util.LinkedList;

public class HashOpened<E> {
  private LinkedList<RegisterOpen<E>>[] table;
  private int capacity;

  @SuppressWarnings("unchecked")
  public HashOpened(int capacity) {
    this.capacity = capacity;
    table = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      table[i] = new LinkedList<>();
    }
  }

  private int hash(int key) {
    return key % table.length;
  }

  public void insert(RegisterOpen<E> reg) {
    int index = hash(reg.getKey());
    int traversalSteps = 0;

    System.out.println("Insertando clave " + reg.getKey() + " - Hash: " + index);
    System.out.println("  Lista en posición " + index + ":");

    for (RegisterOpen<E> r : table[index]) {
      System.out.println("    Paso " + traversalSteps + ": Verificando " + r);
      if (r.getKey() == reg.getKey() && !r.isDeleted()) {
        System.out.println("    ✗ Clave duplicada encontrada: " + reg.getKey());
        return;
      }
      traversalSteps++;
    }

    table[index].add(reg);
    System.out.println("  ✓ Elemento agregado al final de la lista (posición " + traversalSteps + ")");
    System.out.println("  Insertado: " + reg);
  }

  public void delete(int key) {
    int index = hash(key);
    int traversalSteps = 0;

    System.out.println("Eliminando clave " + key + " - Hash: " + index);
    System.out.println("  Recorriendo lista en posición " + index + ":");

    for (RegisterOpen<E> r : table[index]) {
      System.out.println("    Paso " + traversalSteps + ": Verificando " + r);
      if (r.getKey() == key && !r.isDeleted()) {
        r.delete();
        System.out.println("    ✓ Elemento encontrado y eliminado lógicamente en posición " + traversalSteps);
        return;
      }
      traversalSteps++;
    }

    System.out.println("  ✗ Clave no encontrada después de recorrer " + traversalSteps + " elementos");
  }

  public RegisterOpen<E> search(int key) {
    int index = hash(key);
    int traversalSteps = 0;

    System.out.println("Buscando clave " + key + " - Hash: " + index);
    System.out.println("  Recorriendo lista en posición " + index + ":");

    for (RegisterOpen<E> r : table[index]) {
      System.out.println("    Paso " + traversalSteps + ": Verificando " + r);
      if (r.getKey() == key && !r.isDeleted()) {
        System.out.println("    ✓ Elemento encontrado en posición " + traversalSteps + " de la lista");
        return r;
      }
      traversalSteps++;
    }

    System.out.println("  ✗ Elemento no encontrado después de recorrer " + traversalSteps + " elementos");
    return null;
  }

  public void showTable() {
    System.out.println("\n--- Estado de la Tabla Hash (Abierto) ---");
    for (int i = 0; i < table.length; i++) {
      System.out.print(i + ": ");
      if (table[i].isEmpty()) {
        System.out.println("[VACÍO]");
      } else {
        for (RegisterOpen<E> r : table[i]) {
          System.out.print(r + " -> ");
        }
        System.out.println("null");
      }
    }
    System.out.println("--------------------------------------------\n");
  }

  public int getCapacity() {
    return capacity;
  }

  public int[] getStatistics() {
    int elementosActivos = 0;
    int elementosEliminados = 0;
    int posicionesOcupadas = 0;
    int cadenaMaxima = 0;
    int sumaLongitudes = 0;

    for (int i = 0; i < table.length; i++) {
      if (!table[i].isEmpty()) {
        posicionesOcupadas++;
        int longitudCadena = 0;
        
        for (RegisterOpen<E> r : table[i]) {
          longitudCadena++;
          if (r.isDeleted()) {
            elementosEliminados++;
          } else {
            elementosActivos++;
          }
        }
        
        if (longitudCadena > cadenaMaxima) {
          cadenaMaxima = longitudCadena;
        }
        sumaLongitudes += longitudCadena;
      }
    }

    int promedioLongitud = (posicionesOcupadas > 0) ? 
        (int)((double)sumaLongitudes / posicionesOcupadas * 100) : 0;

    return new int[]{elementosActivos, elementosEliminados, posicionesOcupadas, cadenaMaxima, promedioLongitud};
  }
}
