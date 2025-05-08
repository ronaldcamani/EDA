import java.util.*;

public class GestionCalificaciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de estudiantes: ");
        int n = scanner.nextInt();
        
        double[] calificaciones = new double[n];
        
        // Ingreso de calificaciones
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese la calificación del estudiante " + (i + 1) + ": ");
            calificaciones[i] = scanner.nextDouble();
        }
        
        // Calcular y mostrar resultados
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
        Map<Double, Integer> frecuencia = new HashMap<>();
        
        for (double num : arr) {
            frecuencia.put(num, frecuencia.getOrDefault(num, 0) + 1);
        }
        
        double moda = arr[0];
        int maxFrecuencia = 1;
        
        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                moda = entry.getKey();
                maxFrecuencia = entry.getValue();
            }
        }
        
        return moda;
    }
    
    public static double calcularDesviacionEstandar(double[] arr) {
        double media = Arrays.stream(arr).average().getAsDouble();
        double sumaCuadrados = 0;
        
        for (double num : arr) {
            sumaCuadrados += Math.pow(num - media, 2);
        }
        
        return Math.sqrt(sumaCuadrados / arr.length);
    }
} 