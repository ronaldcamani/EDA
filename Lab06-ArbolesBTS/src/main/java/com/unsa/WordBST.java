package com.unsa;

import java.util.Scanner;

public class WordBST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST<Integer> bst = new BST<>();
        BSTVisualizer<Integer> visualizer = new BSTVisualizer<>(bst);

        System.out.println("Enter a word (type 'exit' to quit):");
        String word;
        
        while (!(word = scanner.nextLine()).equalsIgnoreCase("exit")) {
            // Clear the tree for each new word
            bst.destroy();
            
            // Insert each character's ASCII value into the BST
            for (char c : word.toCharArray()) {
                bst.insert((int) c);
            }

            System.out.println("\nTree traversals for word: " + word);
            System.out.print("InOrder traversal (sorted ASCII values): ");
            bst.inOrder();
            System.out.print("PreOrder traversal: ");
            bst.preOrder();
            System.out.print("PostOrder traversal: ");
            bst.postOrder();

            System.out.println("\nMinimum ASCII value: " + (char) bst.min().intValue());
            System.out.println("Maximum ASCII value: " + (char) bst.max().intValue());

            // Visualize the tree
            visualizer.visualize();

            System.out.println("\nEnter another word (type 'exit' to quit):");
        }
        
        scanner.close();
    }
} 