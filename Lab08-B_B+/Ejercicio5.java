public class Ejercicio5 {
    public static void main(String[] args) {
        System.out.println("Ejercicio 5: Árbol B+ grado 5");
        System.out.println("Datos: 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190\n");
        
        BPlusTree<Integer> bptree = new BPlusTree<>(5);
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        System.out.println("INSERCIÓN PASO A PASO:");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%d. Insertar %d:\n", i + 1, datos[i]);
            bptree.insert(datos[i]);
            System.out.println(bptree.toString());
        }
        
        System.out.println("\nÁRBOL B+ FINAL DESPUÉS DE INSERCIÓN:");
        System.out.println(bptree.toString());
        System.out.printf("Min: %d | Max: %d\n", bptree.Min(), bptree.Max());
        
        System.out.println("\nELIMINACIÓN PASO A PASO:");
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%d. Eliminar %d:\n", i + 1, datos[i]);
            bptree.remove(datos[i]);
            if (bptree.isEmpty()) {
                System.out.println("  VACÍO\n");
            } else {
                System.out.println(bptree.toString());
            }
        }
        
        System.out.println("\nÁRBOL B+ FINAL DESPUÉS DE ELIMINACIÓN:");
        if (bptree.isEmpty()) {
            System.out.println("Árbol B+ vacío");
        } else {
            System.out.println(bptree.toString());
        }
    }
} 