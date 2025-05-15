import java.util.Scanner;

public class Ejercicio1 {
    public int[] invertirArray(int[] A) {
        int[] Asalida = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            Asalida[i] = A[A.length - 1 - i];
        }
        return Asalida;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = scanner.nextInt();
        
        int[] A = new int[n];
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        
        Ejercicio1 ejercicio = new Ejercicio1();
        int[] resultado = ejercicio.invertirArray(A);
        
        System.out.print("Arreglo invertido: ");
        for (int num : resultado) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        scanner.close();
    }
} 