public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        
        System.out.println("=== EJERCICIO 1: INSERCIÓN DE NODOS ===");
        System.out.println("Insertando: 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190");
        System.out.println();
        
        int[] nodesToInsert = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        for (int value : nodesToInsert) {
            avl.insert(value);
        }
        
        System.out.println("=== ÁRBOL FINAL DESPUÉS DE TODAS LAS INSERCIONES ===");
        avl.printTree(avl.getRoot(), "", true);
        System.out.println();
        
        System.out.println("=== RECORRIDOS DEL ÁRBOL RESULTANTE ===");
        avl.inOrder();
        avl.preOrder();
        avl.postOrder();
        System.out.println();
        
        System.out.println("=== ELIMINACIÓN DE NODOS ===");
        System.out.println("Eliminando: 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190");
        System.out.println();
        
        int[] nodesToDelete = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        for (int value : nodesToDelete) {
            avl.delete(value);
        }
        
        System.out.println("=== ÁRBOL FINAL DESPUÉS DE TODAS LAS ELIMINACIONES ===");
        if (avl.getRoot() == null) {
            System.out.println("El árbol está vacío");
        } else {
            avl.printTree(avl.getRoot(), "", true);
        }
    }
}