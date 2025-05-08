import java.util.Scanner;

public class Edades {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese el número de personas: ");
    int n = scanner.nextInt();
    int[] edades = new int[n];

    System.out.println("Ingrese las edades:");
    for (int i = 0; i < n; i++) {
      edades[i] = scanner.nextInt();
    }

    int suma = 0, mayor = edades[0], menor = edades[0];
    for (int edad : edades) {
      suma += edad;
      if (edad > mayor)
        mayor = edad;
      if (edad < menor)
        menor = edad;
    }

    double promedio = (double) suma / n;
    System.out.println("Edad promedio: " + promedio);
    System.out.println("Edad mayor: " + mayor);
    System.out.println("Edad menor: " + menor);
    scanner.close();
  }
}
