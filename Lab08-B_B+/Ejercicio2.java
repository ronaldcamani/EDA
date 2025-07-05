public class Ejercicio2 {
    public static void main(String[] args) {
        int grado = 4;
        BTree<Integer> btree = new BTree<>(grado);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        System.out.println("--- Inserción paso a paso (grado 4) ---");
        for (int d : datos) {
            btree.insert(d);
            System.out.println("Insertado: " + d);
            System.out.println(btree);
        }
        System.out.println("\n--- Eliminación paso a paso (grado 4) ---");
        for (int d : datos) {
            btree.remove(d);
            System.out.println("Eliminado: " + d);
            System.out.println(btree);
        }
        System.out.println("\nÁrbol final:");
        System.out.println(btree);
    }
} 