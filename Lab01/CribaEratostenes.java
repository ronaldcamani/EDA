import java.util.*;

public class CribaEratostenes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el límite superior del rango: ");
        int limite = scanner.nextInt();
        
        List<Integer> primos = encontrarPrimos(limite);
        
        System.out.println("\nNúmeros primos encontrados:");
        for (int primo : primos) {
            System.out.print(primo + " ");
        }
        
        scanner.close();
    }
 
    public static List<Integer> encontrarPrimos(int limite) {
        boolean[] esPrimo = new boolean[limite + 1];
        Arrays.fill(esPrimo, true);
        
        esPrimo[0] = false;
        esPrimo[1] = false;

        for (int numero = 2; numero * numero <= limite; numero++) {
            if (esPrimo[numero]) {
                for (int multiplo = numero * numero; multiplo <= limite; multiplo += numero) {
                    esPrimo[multiplo] = false;
                }
            }
        }
        
        List<Integer> numerosPrimos = new ArrayList<>();
        for (int numero = 2; numero <= limite; numero++) {
            if (esPrimo[numero]) {
                numerosPrimos.add(numero);
            }
        }
        
        return numerosPrimos;
    }
} 