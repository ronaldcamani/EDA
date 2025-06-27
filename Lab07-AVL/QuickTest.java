public class QuickTest {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA RÁPIDA DE FUNCIONALIDADES ===");
        
        AVLTree<Integer> avl = new AVLTree<>();
        
        // Probar si está vacío
        System.out.println("¿Árbol vacío? " + avl.isEmpty());
        
        // Insertar algunos valores
        System.out.println("\nInsertando valores: 50, 30, 70, 20, 40, 60, 80");
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);
        
        System.out.println("¿Árbol vacío? " + avl.isEmpty());
        
        // Probar búsqueda
        System.out.println("\n=== PRUEBAS DE BÚSQUEDA ===");
        System.out.println("¿Existe 50? " + avl.search(50));
        System.out.println("¿Existe 25? " + avl.search(25));
        System.out.println("¿Existe 80? " + avl.search(80));
        
        // Probar min y max
        System.out.println("\n=== MIN Y MAX ===");
        System.out.println("Mínimo: " + avl.min());
        System.out.println("Máximo: " + avl.max());
        
        // Probar predecesor y sucesor
        System.out.println("\n=== PREDECESOR Y SUCESOR ===");
        System.out.println("Predecesor de 50: " + avl.predecessor(50));
        System.out.println("Sucesor de 50: " + avl.successor(50));
        System.out.println("Predecesor de 20: " + avl.predecessor(20));
        System.out.println("Sucesor de 80: " + avl.successor(80));
        
        // Mostrar recorridos
        System.out.println("\n=== RECORRIDOS ===");
        avl.inOrder();
        avl.preOrder();
        avl.postOrder();
        
        // Mostrar estructura
        System.out.println("\n=== ESTRUCTURA DEL ÁRBOL ===");
        avl.printTree(avl.getRoot(), "", true);
        
        System.out.println("\n=== PRUEBA DE VISUALIZACIÓN GRÁFICA ===");
        System.out.println("Iniciando visualización...");
        GraphStreamAVLVisualizer.showAVLTree(avl);
        
        System.out.println("\n¡Prueba completada exitosamente!");
    }
}