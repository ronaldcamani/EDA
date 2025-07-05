import java.util.Scanner;

public class Ejercicio7 {
    private static BPlusTree<Integer> tree;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        System.out.println("Ejercicio 7: Implementación completa de Árbol B+ con menú interactivo");
        System.out.println("===============================================================\n");
        
        scanner = new Scanner(System.in);
        tree = null;
        
        while (true) {
            showMenu();
            int choice = getIntInput("Seleccione una opción: ");
            
            switch (choice) {
                case 1:
                    createTree();
                    break;
                case 2:
                    insertElement();
                    break;
                case 3:
                    removeElement();
                    break;
                case 4:
                    searchElement();
                    break;
                case 5:
                    showMinMax();
                    break;
                case 6:
                    showTree();
                    break;
                case 7:
                    showTreeInfo();
                    break;
                case 8:
                    testExercise5();
                    break;
                case 9:
                    testExercise6();
                    break;
                case 10:
                    destroyTree();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.\n");
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("\n=== MENÚ ÁRBOL B+ ===");
        System.out.println("1. Crear árbol B+");
        System.out.println("2. Insertar elemento");
        System.out.println("3. Eliminar elemento");
        System.out.println("4. Buscar elemento");
        System.out.println("5. Mostrar Min/Max");
        System.out.println("6. Mostrar árbol");
        System.out.println("7. Información del árbol");
        System.out.println("8. Probar Ejercicio 5 (Grado 5)");
        System.out.println("9. Probar Ejercicio 6 (Grado 4)");
        System.out.println("10. Destruir árbol");
        System.out.println("0. Salir");
        System.out.println("=====================");
    }
    
    private static void createTree() {
        if (tree != null) {
            System.out.println("Ya existe un árbol. ¿Desea destruirlo y crear uno nuevo? (s/n): ");
            String response = scanner.next();
            if (!response.toLowerCase().equals("s")) {
                return;
            }
        }
        
        int orden = getIntInput("Ingrese el grado del árbol B+: ");
        tree = new BPlusTree<>(orden);
        System.out.println("Árbol B+ de grado " + orden + " creado exitosamente.\n");
    }
    
    private static void insertElement() {
        if (tree == null) {
            System.out.println("Primero debe crear un árbol B+.\n");
            return;
        }
        
        int element = getIntInput("Ingrese el elemento a insertar: ");
        tree.insert(element);
        System.out.println("Elemento " + element + " insertado exitosamente.\n");
    }
    
    private static void removeElement() {
        if (tree == null) {
            System.out.println("Primero debe crear un árbol B+.\n");
            return;
        }
        
        int element = getIntInput("Ingrese el elemento a eliminar: ");
        tree.remove(element);
        System.out.println("Elemento " + element + " eliminado exitosamente.\n");
    }
    
    private static void searchElement() {
        if (tree == null) {
            System.out.println("Primero debe crear un árbol B+.\n");
            return;
        }
        
        int element = getIntInput("Ingrese el elemento a buscar: ");
        boolean found = tree.search(element);
        if (found) {
            System.out.println("Elemento " + element + " encontrado en el árbol.\n");
        } else {
            System.out.println("Elemento " + element + " no encontrado en el árbol.\n");
        }
    }
    
    private static void showMinMax() {
        if (tree == null) {
            System.out.println("Primero debe crear un árbol B+.\n");
            return;
        }
        
        if (tree.isEmpty()) {
            System.out.println("El árbol está vacío.\n");
            return;
        }
        
        System.out.println("Mínimo: " + tree.Min());
        System.out.println("Máximo: " + tree.Max());
        System.out.println();
    }
    
    private static void showTree() {
        if (tree == null) {
            System.out.println("Primero debe crear un árbol B+.\n");
            return;
        }
        
        if (tree.isEmpty()) {
            System.out.println("El árbol está vacío.\n");
            return;
        }
        
        System.out.println("Árbol B+:");
        System.out.println(tree.toString());
    }
    
    private static void showTreeInfo() {
        if (tree == null) {
            System.out.println("Primero debe crear un árbol B+.\n");
            return;
        }
        
        System.out.println("Información del árbol B+:");
        System.out.println("Grado: " + tree.getOrden());
        System.out.println("Vacío: " + tree.isEmpty());
        if (!tree.isEmpty()) {
            System.out.println("Altura: " + tree.getHeight());
            System.out.println("Número de nodos: " + tree.countNodes());
            System.out.println("Mínimo: " + tree.Min());
            System.out.println("Máximo: " + tree.Max());
        }
        System.out.println();
    }
    
    private static void testExercise5() {
        System.out.println("\n=== PRUEBA EJERCICIO 5 (Grado 5) ===");
        tree = new BPlusTree<>(5);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        System.out.println("Insertando elementos...");
        for (int dato : datos) {
            tree.insert(dato);
        }
        
        System.out.println("Árbol B+ después de inserción:");
        System.out.println(tree.toString());
        System.out.printf("Min: %d | Max: %d | Altura: %d | Nodos: %d\n", 
                         tree.Min(), tree.Max(), tree.getHeight(), tree.countNodes());
        
        System.out.println("\nEliminando elementos...");
        for (int dato : datos) {
            tree.remove(dato);
        }
        
        System.out.println("Árbol B+ después de eliminación:");
        if (tree.isEmpty()) {
            System.out.println("Árbol vacío");
        } else {
            System.out.println(tree.toString());
        }
        System.out.println("=== FIN PRUEBA EJERCICIO 5 ===\n");
    }
    
    private static void testExercise6() {
        System.out.println("\n=== PRUEBA EJERCICIO 6 (Grado 4) ===");
        tree = new BPlusTree<>(4);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        System.out.println("Insertando elementos...");
        for (int dato : datos) {
            tree.insert(dato);
        }
        
        System.out.println("Árbol B+ después de inserción:");
        System.out.println(tree.toString());
        System.out.printf("Min: %d | Max: %d | Altura: %d | Nodos: %d\n", 
                         tree.Min(), tree.Max(), tree.getHeight(), tree.countNodes());
        
        System.out.println("\nEliminando elementos...");
        for (int dato : datos) {
            tree.remove(dato);
        }
        
        System.out.println("Árbol B+ después de eliminación:");
        if (tree.isEmpty()) {
            System.out.println("Árbol vacío");
        } else {
            System.out.println(tree.toString());
        }
        System.out.println("=== FIN PRUEBA EJERCICIO 6 ===\n");
    }
    
    private static void destroyTree() {
        if (tree == null) {
            System.out.println("No hay árbol para destruir.\n");
            return;
        }
        
        tree.destroy();
        tree = null;
        System.out.println("Árbol destruido exitosamente.\n");
    }
    
    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }
} 