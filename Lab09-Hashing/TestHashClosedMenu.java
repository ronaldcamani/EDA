import java.util.Scanner;

public class TestHashClosedMenu {
  private static Scanner scanner = new Scanner(System.in);
  private static HashClosed<String> hashTable;

  public static void main(String[] args) {
    System.out.println("╔══════════════════════════════════════════╗");
    System.out.println("║    SISTEMA INTERACTIVO HASH CERRADO      ║");
    System.out.println("║         (Sondeo Lineal)                  ║");
    System.out.println("╚══════════════════════════════════════════╝\n");

    inicializarTabla();
    mostrarMenu();
  }

  private static void inicializarTabla() {
    System.out.print("📏 Ingrese el tamaño de la tabla hash: ");
    int tamaño = scanner.nextInt();
    hashTable = new HashClosed<>(tamaño);
    System.out.println("✅ Tabla hash creada con tamaño: " + tamaño + "\n");
  }

  private static void mostrarMenu() {
    int opcion;
    do {
      System.out.println("\n┌────────────────────────────────────────┐");
      System.out.println("│           MENÚ HASH CERRADO            │");
      System.out.println("├────────────────────────────────────────┤");
      System.out.println("│ 1. 📥 Insertar elemento                │");
      System.out.println("│ 2. 🔍 Buscar elemento                  │");
      System.out.println("│ 3. 🗑️  Eliminar elemento               │");
      System.out.println("│ 4. 📊 Mostrar tabla                    │");
      System.out.println("│ 5. 📈 Estadísticas                     │");
      System.out.println("│ 6. 🔄 Reinicializar tabla              │");
      System.out.println("│ 7. 📋 Inserción en lote                │");
      System.out.println("│ 8. 🧪 Prueba de colisiones             │");
      System.out.println("│ 0. 🚪 Salir                            │");
      System.out.println("└────────────────────────────────────────┘");
      System.out.print("🎯 Seleccione una opción: ");

      opcion = scanner.nextInt();
      scanner.nextLine(); // Consumir newline

      switch (opcion) {
        case 1:
          insertarElemento();
          break;
        case 2:
          buscarElemento();
          break;
        case 3:
          eliminarElemento();
          break;
        case 4:
          mostrarTabla();
          break;
        case 5:
          mostrarEstadisticas();
          break;
        case 6:
          reinicializarTabla();
          break;
        case 7:
          insercionEnLote();
          break;
        case 8:
          pruebaColisiones();
          break;
        case 0:
          System.out.println("\n👋 ¡Gracias por usar el sistema Hash Cerrado!");
          break;
        default:
          System.out.println("❌ Opción inválida. Intente nuevamente.\n");
      }
    } while (opcion != 0);
  }

  private static void insertarElemento() {
    System.out.println("\n═══ INSERCIÓN DE ELEMENTO ═══");
    System.out.print("🔑 Ingrese la clave (número entero): ");
    int clave = scanner.nextInt();
    scanner.nextLine();
    System.out.print("💾 Ingrese el valor (texto): ");
    String valor = scanner.nextLine();

    System.out.println("\n🚀 Iniciando inserción...");
    boolean exito = hashTable.add(clave, valor);

    if (exito) {
      System.out.println("✅ Elemento insertado exitosamente!");
    } else {
      System.out.println("❌ Error al insertar el elemento.");
    }
  }

  private static void buscarElemento() {
    System.out.println("\n═══ BÚSQUEDA DE ELEMENTO ═══");
    System.out.print("🔍 Ingrese la clave a buscar: ");
    int clave = scanner.nextInt();

    System.out.println("\n🚀 Iniciando búsqueda...");
    Register<String> resultado = hashTable.search(clave);

    if (resultado != null) {
      System.out.println("✅ Elemento encontrado: " + resultado);
    } else {
      System.out.println("❌ Elemento no encontrado.");
    }
  }

  private static void eliminarElemento() {
    System.out.println("\n═══ ELIMINACIÓN DE ELEMENTO ═══");
    System.out.print("🗑️ Ingrese la clave a eliminar: ");
    int clave = scanner.nextInt();

    System.out.println("\n🚀 Iniciando eliminación...");
    boolean exito = hashTable.delete(clave);

    if (exito) {
      System.out.println("✅ Elemento eliminado exitosamente!");
    } else {
      System.out.println("❌ Error: Elemento no encontrado.");
    }
  }

  private static void mostrarTabla() {
    System.out.println("\n═══ ESTADO ACTUAL DE LA TABLA ═══");
    hashTable.display();
  }

  private static void mostrarEstadisticas() {
    System.out.println("\n═══ ESTADÍSTICAS DE LA TABLA ═══");
    int elementos = hashTable.getSize();
    int capacidad = hashTable.getCapacity();
    double factorCarga = (double) elementos / capacidad;
    int espaciosLibres = capacidad - elementos;

    System.out.println("📊 Elementos almacenados: " + elementos);
    System.out.println("📏 Capacidad total: " + capacidad);
    System.out.println("📈 Factor de carga: " + String.format("%.2f", factorCarga));
    System.out.println("🆓 Espacios libres: " + espaciosLibres);
    System.out.println("📊 Ocupación: " + String.format("%.1f%%", factorCarga * 100));

    if (factorCarga > 0.7) {
      System.out.println("⚠️  ADVERTENCIA: Factor de carga alto (>70%)");
    } else if (factorCarga > 0.5) {
      System.out.println("🟡 PRECAUCIÓN: Factor de carga moderado (>50%)");
    } else {
      System.out.println("✅ Factor de carga óptimo (<50%)");
    }
  }

  private static void reinicializarTabla() {
    System.out.println("\n═══ REINICIALIZACIÓN DE TABLA ═══");
    System.out.print("⚠️ ¿Está seguro? Perderá todos los datos (s/n): ");
    String confirmacion = scanner.nextLine();

    if (confirmacion.toLowerCase().equals("s") || confirmacion.toLowerCase().equals("si")) {
      inicializarTabla();
      System.out.println("🔄 Tabla reinicializada exitosamente!");
    } else {
      System.out.println("❌ Operación cancelada.");
    }
  }

  private static void insercionEnLote() {
    System.out.println("\n═══ INSERCIÓN EN LOTE ═══");
    System.out.print("📦 Ingrese las claves separadas por espacios: ");
    String linea = scanner.nextLine();
    String[] claves = linea.split(" ");

    System.out.println("\n🚀 Insertando " + claves.length + " elementos...");

    for (String claveStr : claves) {
      try {
        int clave = Integer.parseInt(claveStr.trim());
        hashTable.add(clave, "Valor" + clave);
      } catch (NumberFormatException e) {
        System.out.println("⚠️ Clave inválida ignorada: " + claveStr);
      }
    }
    System.out.println("✅ Inserción en lote completada!");
  }

  private static void pruebaColisiones() {
    System.out.println("\n═══ PRUEBA DE COLISIONES ═══");
    System.out.println("🧪 Insertando elementos que generan colisiones...");

    int capacidad = hashTable.getCapacity();
    System.out.println("📏 Capacidad de la tabla: " + capacidad);

    // Insertar múltiples elementos que mapean a la misma posición
    int baseKey = 0;
    for (int i = 0; i < 5; i++) {
      int clave = baseKey + (i * capacidad);
      System.out.println("\n🎯 Insertando clave " + clave + " (hash = " + (clave % capacidad) + ")");
      hashTable.add(clave, "TestColision" + i);
    }
    System.out.println("\n✅ Prueba de colisiones completada!");
  }
}
