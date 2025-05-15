public class Ejercicio4 {
    public void trianguloRecursivo2(int base) {
        if (base > 0) {
            for (int i = 0; i < base; i++) {
                System.out.print("*");
            }
            System.out.println();
            trianguloRecursivo2(base - 1);
        }
    }
    
    public static void main(String[] args) {
        Ejercicio4 ejercicio = new Ejercicio4();
        System.out.println("Triángulo recursivo 2 con base 5:");
        ejercicio.trianguloRecursivo2(5);
    }
} 