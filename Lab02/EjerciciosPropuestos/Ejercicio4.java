public class Ejercicio4 {
    public void trianguloRecursivo2(int base) {
        if (base > 0) {
            trianguloRecursivo2(base - 1);
            for (int i = 0; i < base; i++) {
                System.out.print("*");
            }
            System.out.println();
            
        }
    }
    
    public static void main(String[] args) {
        Ejercicio4 ejercicio = new Ejercicio4();
        ejercicio.trianguloRecursivo2(5);
    }
} 