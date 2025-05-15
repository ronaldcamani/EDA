public class Ejercicio3 {
    public void trianguloRecursivo1(int base) {
        if (base > 0) {
            trianguloRecursivo1(base - 1);
            for (int i = 0; i < base; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Ejercicio3 ejercicio = new Ejercicio3();
        System.out.println("Triángulo recursivo 1 con base 5:");
        ejercicio.trianguloRecursivo1(5);
    }
} 