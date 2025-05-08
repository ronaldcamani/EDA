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
        esPrimo[0] = esPrimo[1] = false;
        
        for (int i = 2; i * i <= limite; i++) {
            if (esPrimo[i]) {
                for (int j = i * i; j <= limite; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
        
        List<Integer> primos = new ArrayList<>();
        for (int i = 2; i <= limite; i++) {
            if (esPrimo[i]) {
                primos.add(i);
            }
        }
        
        return primos;
    }
} 