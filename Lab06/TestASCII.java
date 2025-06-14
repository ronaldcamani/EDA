import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestASCII extends Application {
    private static BSTVisualizer<Integer> bst;
    private static Scanner scanner;

    @Override
    public void start(Stage primaryStage) {
        // No necesitamos hacer nada aquí, la ventana se creará en BSTVisualizer
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        bst = new BSTVisualizer<>();
        
        System.out.println("=== Árbol Binario de Búsqueda con Valores ASCII ===");
        System.out.println("Ingrese una palabra (o 'salir' para terminar):");
        
        // Iniciar JavaFX en un hilo separado
        new Thread(() -> {
            launch(args);
        }).start();
        
        while (true) {
            System.out.print("\nPalabra: ");
            String palabra = scanner.nextLine().trim();
            
            if (palabra.equalsIgnoreCase("salir")) {
                break;
            }
            
            // Limpiar el árbol anterior
            bst.destroy();
            
            // Insertar cada carácter de la palabra en el BST
            System.out.println("\nInsertando caracteres con sus valores ASCII:");
            for (char c : palabra.toCharArray()) {
                int asciiValue = (int) c;
                bst.insert(asciiValue);
                System.out.printf("Carácter '%c' (ASCII: %d)\n", c, asciiValue);
            }
            
            // Mostrar el árbol en diferentes recorridos
            System.out.println("\nRecorridos del árbol:");
            System.out.println("-------------------");
            
            System.out.println("\nRecorrido InOrder (orden ascendente):");
            bst.inOrder();
            
            System.out.println("\nRecorrido PreOrder (raíz-izquierda-derecha):");
            bst.preOrder();
            
            System.out.println("\nRecorrido PostOrder (izquierda-derecha-raíz):");
            bst.postOrder();
            
            // Mostrar valores mínimo y máximo
            System.out.println("\nValores extremos:");
            System.out.println("Mínimo (ASCII): " + bst.min() + " -> Carácter: '" + (char)(int)bst.min() + "'");
            System.out.println("Máximo (ASCII): " + bst.max() + " -> Carácter: '" + (char)(int)bst.max() + "'");
            
            // Mostrar predecesor y sucesor para cada carácter
            System.out.println("\nPredecesores y Sucesores:");
            for (char c : palabra.toCharArray()) {
                int asciiValue = (int) c;
                Integer pred = bst.predecessor(asciiValue);
                Integer succ = bst.successor(asciiValue);
                
                System.out.printf("Carácter '%c' (ASCII: %d):\n", c, asciiValue);
                System.out.printf("  Predecesor: %s\n", 
                    pred != null ? String.format("'%c' (ASCII: %d)", (char)(int)pred, pred) : "No tiene");
                System.out.printf("  Sucesor: %s\n", 
                    succ != null ? String.format("'%c' (ASCII: %d)", (char)(int)succ, succ) : "No tiene");
            }

            // Mostrar visualización del árbol
            System.out.println("\nMostrando visualización del árbol...");
            bst.display();
            
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
            bst.close();
        }
        
        scanner.close();
        System.out.println("\n¡Hasta luego!");
        System.exit(0);
    }
} 