import java.util.Scanner;

public class TestHashOpenedMenu {
  private static Scanner scanner = new Scanner(System.in);
  private static HashOpened<String> hashTable;

  public static void main(String[] args) {
    System.out.println("╔══════════════════════════════════════════╗");
    System.out.println("║    SISTEMA INTERACTIVO HASH ABIERTO      ║");
    System.out.println("║         (Encadenamiento)                 ║");
    System.out.println("╚══════════════════════════════════════════╝\n");

    inicializarTabla();
    mostrarMenu();
  }

  private static void inicializarTabla() {
    System.out.print("📏 Ingrese el tamaño de la tabla hash: ");
    int tamaño = scanner.nextInt();
    hashTable = new HashOpened<>(tamaño);
    System.out.println("✅ Tabla hash creada con tamaño: " + tamaño + "\n");
  }

  private static void mostrarMenu() {
    int opcion;
    do {
      System.out.println("\n┌───────────────────────────────────────┐");
      System.out.println("│           MENÚ HASH ABIERTO           │");
      System.out.println("├───────────────────────────────────────┤");
      System.out.println("│ 1. 📥 Insertar elemento               │");
      System.out.println("│ 2. 🔍 Buscar elemento                 │");
      System.out.println("│ 3. 🗑️  Eliminar elemento              │");
      System.out.println("│ 4. 📊 Mostrar tabla                   │");
      System.out.println("│ 5. 📈 Estadísticas y métricas         │");
      System.out.println("│ 6. 🔄 Reinicializar tabla             │");
      System.out.println("│ 7. 📋 Inserción en lote               │");
      System.out.println("│ 8. 🧪 Prueba de colisiones extremas   │");
      System.out.println("│ 0. 🚪 Salir                           │");
      System.out.println("└───────────────────────────────────────┘");
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
          pruebaColisionesExtremas();
          break;
        case 0:
          System.out.println("\n👋 ¡Gracias por usar el sistema Hash Abierto!");
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
    RegisterOpen<String> registro = new RegisterOpen<>(clave, valor);
    hashTable.insert(registro);

    System.out.println("✅ Proceso de inserción completado!");
  }

  private static void buscarElemento() {
    System.out.println("\n═══ BÚSQUEDA DE ELEMENTO ═══");
    System.out.print("🔍 Ingrese la clave a buscar: ");
    int clave = scanner.nextInt();

    System.out.println("\n🚀 Iniciando búsqueda...");
    RegisterOpen<String> resultado = hashTable.search(clave);

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
    hashTable.delete(clave);

    System.out.println("✅ Proceso de eliminación completado!");
  }

  private static void mostrarTabla() {
    System.out.println("\n═══ ESTADO ACTUAL DE LA TABLA ═══");
    hashTable.showTable();
  }

  private static void mostrarEstadisticas() {
    System.out.println("\n═══ ESTADÍSTICAS Y MÉTRICAS AVANZADAS ═══");

    int capacidad = hashTable.getCapacity();
    int[] estadisticas = hashTable.getStatistics();

    int elementosActivos = estadisticas[0];
    int elementosEliminados = estadisticas[1];
    int posicionesOcupadas = estadisticas[2];
    int cadenaMaxima = estadisticas[3];
    double promedioLongitud = estadisticas[4] / 100.0; // Se guardó multiplicado por 100

    System.out.println("📊 ESTADÍSTICAS GENERALES:");
    System.out.println("   📏 Capacidad total: " + capacidad);
    System.out.println("   ✅ Elementos activos: " + elementosActivos);
    System.out.println("   🗑️ Elementos eliminados: " + elementosEliminados);
    System.out.println("   📍 Posiciones ocupadas: " + posicionesOcupadas);
    System.out.println("   🆓 Posiciones vacías: " + (capacidad - posicionesOcupadas));

    System.out.println("\n🔗 ANÁLISIS DE CADENAS:");
    System.out.println("   📏 Longitud máxima de cadena: " + cadenaMaxima);
    System.out.println("   📊 Longitud promedio: " + String.format("%.2f", promedioLongitud));

    double factorCarga = (double) elementosActivos / capacidad;
    System.out.println("\n📈 FACTORES DE RENDIMIENTO:");
    System.out.println("   📊 Factor de carga: " + String.format("%.2f", factorCarga));
    System.out.println("   📈 Ocupación: " + String.format("%.1f%%", factorCarga * 100));

    // Evaluación del rendimiento
    if (cadenaMaxima > 5) {
      System.out.println("   ⚠️  ADVERTENCIA: Cadena muy larga detectada (>" + cadenaMaxima + ")");
    } else if (cadenaMaxima > 3) {
      System.out.println("   🟡 PRECAUCIÓN: Cadenas moderadamente largas");
    } else {
      System.out.println("   ✅ Distribución óptima de elementos");
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
        RegisterOpen<String> registro = new RegisterOpen<>(clave, "Valor" + clave);
        hashTable.insert(registro);
      } catch (NumberFormatException e) {
        System.out.println("⚠️ Clave inválida ignorada: " + claveStr);
      }
    }
    System.out.println("✅ Inserción en lote completada!");
  }

  private static void pruebaColisionesExtremas() {
    System.out.println("\n═══ PRUEBA DE COLISIONES EXTREMAS ═══");
    System.out.println("🧪 Insertando elementos que fuerzan colisiones máximas...");

    int capacidad = hashTable.getCapacity();
    System.out.println("📏 Capacidad de la tabla: " + capacidad);
    System.out.println("🎯 Todos los elementos mapearán a la posición 0");

    // Insertar múltiples elementos que mapean a la misma posición
    for (int i = 0; i < 5; i++) {
      int clave = i * capacidad; // Todos mapean a posición 0
      System.out.println("\n🔗 Insertando clave " + clave + " (hash = " + (clave % capacidad) + ")");
      RegisterOpen<String> registro = new RegisterOpen<>(clave, "Colision" + i);
      hashTable.insert(registro);
    }

    System.out.println("\n✅ Prueba de colisiones extremas completada!");
    System.out.println("📊 Observe cómo se forma una cadena larga en la posición 0");
  }
}
