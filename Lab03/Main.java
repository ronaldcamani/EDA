import java.util.Scanner;

public class Main {

    static <T extends Number> double suma(T valor1, T valor2) {
        return (valor1.doubleValue() + valor2.doubleValue());
    }

    static <T extends Number> double resta(T valor1, T valor2) {
        return (valor1.doubleValue() - valor2.doubleValue());
    }

    static <T extends Number> double producto(T valor1, T valor2) {
        return (valor1.doubleValue() * valor2.doubleValue());
    }

    static <T extends Number> double division(T valor1, T valor2) {
        if (valor2.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (valor1.doubleValue() / valor2.doubleValue());
    }

    static <T extends Number> double potencia(T base, T exponente) {
        return Math.pow(base.doubleValue(), exponente.doubleValue());
    }

    static <T extends Number> double raizCuadrada(T valor) {
        if (valor.doubleValue() < 0) {
            throw new IllegalArgumentException("Cannot take the square root of a negative number");
        }
        return Math.sqrt(valor.doubleValue());
    }

    static <T extends Number> double raizCubica(T valor) {
        return Math.cbrt(valor.doubleValue());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma.");
            System.out.println("2. Resta.");
            System.out.println("3. Producto.");
            System.out.println("4. División.");
            System.out.println("5. Potencia.");
            System.out.println("6. Raíz Cuadrada.");
            System.out.println("7. Raíz Cubica.");
            System.out.println("8. Salir del Programa.");
            System.out.print("Ingrese su opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                if (opcion >= 1 && opcion <= 7) {
                    Number num1 = null;
                    Number num2 = null;

                    if (opcion <= 5) { // Operations with two numbers
                        System.out.print("Ingrese el primer número: ");
                        String input1 = scanner.nextLine();
                        num1 = parseNumber(input1);

                        System.out.print("Ingrese el segundo número: ");
                        String input2 = scanner.nextLine();
                        num2 = parseNumber(input2);
                    } else { // Operations with one number
                         System.out.print("Ingrese el número: ");
                         String input1 = scanner.nextLine();
                         num1 = parseNumber(input1);
                    }
                    // Check if parsing was successful for required numbers
                    if (num1 != null && (opcion > 5 || num2 != null)) { 
                        double result = 0;
                        try {
                            switch (opcion) {
                                case 1:
                                    result = suma(num1, num2);
                                    System.out.println("Resultado de la Suma: " + result);
                                    break;
                                case 2:
                                    result = resta(num1, num2);
                                    System.out.println("Resultado de la Resta: " + result);
                                    break;
                                case 3:
                                    result = producto(num1, num2);
                                    System.out.println("Resultado del Producto: " + result);
                                    break;
                                case 4:
                                    result = division(num1, num2);
                                    System.out.println("Resultado de la División: " + result);
                                    break;
                                case 5:
                                    result = potencia(num1, num2);
                                    System.out.println("Resultado de la Potencia: " + result);
                                    break;
                                case 6:
                                    result = raizCuadrada(num1);
                                    System.out.println("Resultado de la Raíz Cuadrada: " + result);
                                    break;
                                case 7:
                                    result = raizCubica(num1);
                                    System.out.println("Resultado de la Raíz Cubica: " + result);
                                    break;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }

                } else if (opcion != 8) {
                    System.out.println("Opción inválida. Por favor ingrese un número entre 1 y 8.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número para la opción.");
                opcion = 0; // Reset option to continue loop
            }

        } while (opcion != 8);

        System.out.println("Saliendo del programa.");
        scanner.close();
    }

    // Helper method to parse input string as Integer or Double
    private static Number parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e2) {
                System.out.println("Entrada inválida para el número: " + input);
                return null;
            }
        }
    }
} 