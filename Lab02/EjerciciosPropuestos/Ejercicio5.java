public class Ejercicio5 {
    public void trianguloRecursivo3(int base) {
        if (base > 0) {
            for (int i = 0; i < base; i++) {
                System.out.print("*");
            }
            System.out.println();
            trianguloRecursivo3(base - 1);
            for (int i = 0; i < base; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Ejercicio5 ejercicio = new Ejercicio5();
        System.out.println("Triángulo recursivo 3 con base 5:");
        ejercicio.trianguloRecursivo3(5);
    }
} 