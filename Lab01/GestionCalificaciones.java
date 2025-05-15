import java.util.*;

public class GestionCalificaciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de estudiantes: ");
        int n = scanner.nextInt();
        
        double[] calificaciones = new double[n];
        
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese la calificación del estudiante " + (i + 1) + ": ");
            calificaciones[i] = scanner.nextDouble();
        }

        System.out.println("\nResultados:");
        System.out.println("Mediana: " + calcularMediana(calificaciones));
        System.out.println("Moda: " + calcularModa(calificaciones));
        System.out.println("Desviación Estándar: " + calcularDesviacionEstandar(calificaciones));
        
        scanner.close();
    }
    
    public static double calcularMediana(double[] arr) {
        double[] copia = arr.clone();
        Arrays.sort(copia);
        
        if (copia.length % 2 == 0) {
            return (copia[copia.length/2 - 1] + copia[copia.length/2]) / 2.0;
        } else {
            return copia[copia.length/2];
        }
    }
    
    public static double calcularModa(double[] arr) {
        double[] copia = arr.clone();
        Arrays.sort(copia);
        
        double moda = copia[0];
        int contadorActual = 1;
        int contadorMaximo = 1;
        
        for (int i = 1; i < copia.length; i++) {
            if (copia[i] == copia[i-1]) {
                contadorActual++;
            } else {
                contadorActual = 1;
            }
            
            if (contadorActual > contadorMaximo) {
                contadorMaximo = contadorActual;
                moda = copia[i];
            }
        }
        
        return moda;
    }
    
    public static double calcularDesviacionEstandar(double[] arr) {
        double suma = 0;
        
        for (double num : arr) {
            suma += num;
        }
        
        double media = suma / arr.length;
        double sumaDiferenciasCuadrado = 0;
        
        for (double num : arr) {
            double diferencia = num - media;
            sumaDiferenciasCuadrado += diferencia * diferencia;
        }
        
        double varianza = sumaDiferenciasCuadrado / arr.length;
        return Math.sqrt(varianza);//La desviación estándar es la raíz cuadrada de la varianza
    }
} 