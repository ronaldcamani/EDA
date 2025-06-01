import java.util.Scanner;

public class Ejercicio2 {
    public int[] rotarIzquierdaArray(int[] A) {
        int[] Ainvertido = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            Ainvertido[i] = A[(i + 2) % A.length];
        }
        return Ainvertido;
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

        Ejercicio2 ejercicio = new Ejercicio2();
        int[] resultado = ejercicio.rotarIzquierdaArray(A);

        System.out.print("Arreglo rotado: ");
        for (int num : resultado) {
            System.out.print(num + " ");
        }
        System.out.println();

        scanner.close();
    }
}