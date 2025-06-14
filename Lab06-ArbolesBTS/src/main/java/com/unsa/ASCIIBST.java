package com.unsa;

import java.util.Scanner;

public class ASCIIBST {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== ÁRBOL BINARIO DE BÚSQUEDA CON VALORES ASCII ===");
    // Solicitar palabra al usuario
    System.out.print("Ingrese una palabra: ");
    String palabra = scanner.nextLine();

    // Crear BST que almacenará valores ASCII
    BST<Integer> asciiBST = new BST<>();

    // Mostrar cada carácter y su valor ASCII
    System.out.println("Caracteres y sus valores ASCII:");
    for (int i = 0; i < palabra.length(); i++) {
      char caracter = palabra.charAt(i);
      int valorASCII = (int) caracter;
      System.out.println("'" + caracter + "' -> " + valorASCII);
    }

    System.out.println();

    // Insertar valores ASCII en el BST
    System.out.println("Insertando valores ASCII en el BST...");
    for (int i = 0; i < palabra.length(); i++) {
      char caracter = palabra.charAt(i);
      int valorASCII = (int) caracter;
      asciiBST.insert(valorASCII);
      System.out.println("Insertado: " + valorASCII + " (carácter '" + caracter + "')");
    }

    System.out.println();
    System.out.println("=== ESTRUCTURA DEL ÁRBOL ===");

    // Mostrar si el árbol está vacío
    System.out.println("¿Árbol vacío? " + asciiBST.isEmpty());
    System.out.println();

    // Mostrar recorridos del árbol
    System.out.println("RECORRIDOS DEL ÁRBOL:");
    asciiBST.inOrder();
    asciiBST.preOrder();
    asciiBST.postOrder();
    System.out.println();

    // Mostrar información adicional del árbol
    System.out.println("INFORMACIÓN DEL ÁRBOL:");
    System.out.println("Valor mínimo: " + asciiBST.min() + " (carácter: '" + (char) (int) asciiBST.min() + "')");
    System.out.println("Valor máximo: " + asciiBST.max() + " (carácter: '" + (char) (int) asciiBST.max() + "')");
    System.out.println();

    // Demostrar búsquedas
    System.out.println("PRUEBAS DE BÚSQUEDA:");
    // Buscar algunos caracteres específicos
    char[] caracteresABuscar = { 'a', 'e', 'i', 'o', 'u', 'z' };
    for (char c : caracteresABuscar) {
      int asciiValue = (int) c;
      boolean encontrado = asciiBST.search(asciiValue);
      System.out.println("Buscar '" + c + "' (" + asciiValue + "): " +
          (encontrado ? "Encontrado" : "No encontrado"));
    }

    System.out.println();

    // Mostrar predecesores y sucesores de algunos valores
    System.out.println("PREDECESORES Y SUCESORES:");
    // Tomar algunos valores del árbol para mostrar predecesores y sucesores
    for (int i = 0; i < Math.min(3, palabra.length()); i++) {
      int valorASCII = (int) palabra.charAt(i);
      Integer predecesor = asciiBST.predecessor(valorASCII);
      Integer sucesor = asciiBST.successor(valorASCII);

      System.out.print("Valor: " + valorASCII + " ('" + (char) valorASCII + "')");
      System.out.print(
          ", Predecesor: " + (predecesor != null ? predecesor + " ('" + (char) (int) predecesor + "')" : "null"));
      System.out.println(", Sucesor: " + (sucesor != null ? sucesor + " ('" + (char) (int) sucesor + "')" : "null"));
    }

    scanner.close();
  }
}
