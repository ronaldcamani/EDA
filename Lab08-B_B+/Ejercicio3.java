import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BTree<Integer> btree = null;
        int opcion;
        
        do {
            System.out.println("\nMenú Árbol B:");
            System.out.println("1. Crear árbol | 2. Insertar | 3. Eliminar | 4. Buscar");
            System.out.println("5. Mostrar | 6. Test inserción | 7. Test eliminación | 8. Destruir | 0. Salir");
            System.out.print("Opción: ");
            
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.print("Grado: ");
                    btree = new BTree<>(sc.nextInt());
                    System.out.println("Creado");
                    break;
                case 2:
                    if (btree != null) {
                        System.out.print("Insertar: ");
                        btree.insert(sc.nextInt());
                        System.out.println(btree);
                    }
                    break;
                case 3:
                    if (btree != null) {
                        System.out.print("Eliminar: ");
                        btree.remove(sc.nextInt());
                        System.out.println(btree);
                    }
                    break;
                case 4:
                    if (btree != null) {
                        System.out.print("Buscar: ");
                        System.out.println(btree.search(sc.nextInt()) ? "Encontrado" : "No encontrado");
                    }
                    break;
                case 5:
                    if (btree != null) System.out.println(btree);
                    break;
                case 6:
                    if (btree != null) {
                        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
                        for (int d : datos) {
                            btree.insert(d);
                            System.out.printf("+ %d: %s\n", d, btree.isEmpty() ? "vacío" : "insertado");
                        }
                        System.out.println(btree);
                    }
                    break;
                case 7:
                    if (btree != null) {
                        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
                        for (int d : datos) {
                            btree.remove(d);
                            System.out.printf("- %d: %s\n", d, btree.isEmpty() ? "vacío" : "eliminado");
                        }
                        System.out.println(btree);
                    }
                    break;
                case 8:
                    if (btree != null) {
                        btree.destroy();
                        System.out.println("Destruido");
                    }
                    break;
            }
        } while (opcion != 0);
        sc.close();
    }
} 