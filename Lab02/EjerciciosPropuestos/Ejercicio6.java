public class Ejercicio6 {
    public void cuadradoRecursivo(int base) {
        if (base > 0) {
            // Primera línea (arriba)
            for (int i = 0; i < base; i++) {
                System.out.print("*");
            }
            System.out.println();
            // Líneas del medio
            if (base > 2) {
                for (int i = 0; i < base - 2; i++) {
                    System.out.print("*");
                    for (int j = 0; j < base - 2; j++) {
                        System.out.print(" ");
                    }
                    System.out.println("*");
                }
            }
            // Última línea (abajo)
            if (base > 1) {
                for (int i = 0; i < base; i++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) {
        Ejercicio6 ejercicio = new Ejercicio6();
        System.out.println("Cuadrado 5x5:");
        ejercicio.cuadradoRecursivo(5);
    }
}