public class Ejercicio1 { 
    public static void main(String[] args) {
        System.out.println("Ejercicio 1: Árbol B grado 5");
        System.out.println("Datos: 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190\n");
        
        BTree<Integer> btree = new BTree<>(5);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        System.out.println("INSERCIÓN:");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%d. Insertar %d:\n", i + 1, datos[i]);
            btree.insert(datos[i]);
            System.out.println(btree.toString());
        }
        
        System.out.println("\nÁRBOL FINAL:");
        System.out.println(btree.toString());
        System.out.printf("Min: %d | Max: %d | Altura: %d | Nodos: %d\n", 
                            btree.Min(), btree.Max(), btree.getHeight(), btree.countNodes());
        
        System.out.println("\nELIMINACIÓN:");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%d. Eliminar %d:\n", i + 1, datos[i]);
            btree.remove(datos[i]);
            if (btree.isEmpty()) {
                System.out.println("  VACÍO\n");
            } else {
                System.out.println(btree.toString());
            }
        }
    }
}