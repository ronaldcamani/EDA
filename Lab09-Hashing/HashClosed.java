public class HashClosed<E> {
  private Element<E>[] table;
  private int size;
  private int capacity;

  @SuppressWarnings("unchecked")
  public HashClosed(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.table = new Element[capacity];
    for (int i = 0; i < capacity; i++) {
      table[i] = new Element<E>();
    }
  }

  private int hash(int key) {
    return key % capacity;
  }

  public boolean add(int key, E value) {
    if (size >= capacity) {
      System.out.println("Error: Tabla hash llena");
      return false;
    }

    int index = hash(key);
    int originalIndex = index;

    do {
      if (table[index].isAvailable()) {
        table[index].setData(new Register<E>(key, value));
        size++;
        System.out.println("Elemento agregado: " + key + " en posición " + index);
        return true;
      } else if (!table[index].isEmpty() && table[index].getData().getKey() == key) {
        System.out.println("Error: Clave " + key + " ya existe en la tabla");
        return false;
      }

      index = (index + 1) % capacity;
    } while (index != originalIndex);

    System.out.println("Error: No se pudo insertar la clave " + key);
    return false;
  }

  public Register<E> search(int key) {
    int index = hash(key);
    int originalIndex = index;

    do {
      if (table[index].isEmpty()) {
        return null;
      }
      if (!table[index].isDeleted() && table[index].getData().getKey() == key) {
        return table[index].getData();
      }
      index = (index + 1) % capacity;
    } while (index != originalIndex);

    return null;
  }

  public boolean delete(int key) {
    int index = hash(key);
    int originalIndex = index;

    do {
      if (table[index].isEmpty()) {
        System.out.println("Elemento " + key + " no encontrado");
        return false;
      }
      if (!table[index].isDeleted() && table[index].getData().getKey() == key) {
        table[index].setDeleted(true);
        size--;
        System.out.println("Elemento " + key + " eliminado de posición " + index);
        return true;
      }
      index = (index + 1) % capacity;
    } while (index != originalIndex);

    System.out.println("Elemento " + key + " no encontrado");
    return false;
  }

  public void display() {
    System.out.println("\n=== Tabla Hash ===");
    for (int i = 0; i < capacity; i++) {
      System.out.println("Posición " + i + ": " + table[i]);
    }
    System.out.println("Elementos en tabla: " + size + "/" + capacity);
    System.out.println("==================\n");
  }

  public int getSize() {
    return size;
  }

  public int getCapacity() {
    return capacity;
  }
}
