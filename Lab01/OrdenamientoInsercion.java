import java.util.*;

public class OrdenamientoInsercion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = scanner.nextInt();
        
        int[] arr = new int[n];
        
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }
        
        System.out.println("\nArreglo original:");
        imprimirArreglo(arr);
        
        ordenarPorInsercion(arr);
        
        System.out.println("\nArreglo ordenado:");
        imprimirArreglo(arr);
        
        scanner.close();
    }
    
    public static void ordenarPorInsercion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int valorActual = arr[i];
            int j = i - 1;
            
            // Mover elementos mayores que valorActual una posición adelante
            while (j >= 0 && arr[j] > valorActual) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = valorActual;
            
            // Mostrar el estado del arreglo después de cada iteración
            System.out.println("\nPaso " + i + ":");
            imprimirArreglo(arr);
        }
    }
    
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
} 