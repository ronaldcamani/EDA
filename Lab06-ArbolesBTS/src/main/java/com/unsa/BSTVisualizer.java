package com.unsa;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

public class BSTVisualizer<T extends Comparable<T>> {
    private Graph graph;
    private BST<T> bst;

    public BSTVisualizer(BST<T> bst) {
        this.bst = bst;
        this.graph = new SingleGraph("Binary Search Tree");
        System.setProperty("org.graphstream.ui", "swing");
    }

    public void visualize() {
        // Clear the graph
        graph.clear();
        
        // Add nodes and edges recursively
        addNodeAndEdges(null, bst.getRoot(), 0);
        
        // Style the graph
        graph.setAttribute("ui.stylesheet", 
            "node { fill-color: #3CB371; size: 30px; text-size: 14px; }" +
            "edge { size: 2px; }");
        
        // Display the graph
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }

    private void addNodeAndEdges(Node<T> parent, Node<T> current, int id) {
        if (current == null) return;

        // Usar el valor del nodo como ID (debe ser único en el árbol)
        String nodeId = current.getData().toString();
        if (graph.getNode(nodeId) == null) {
            org.graphstream.graph.Node node = graph.addNode(nodeId);
            node.setAttribute("ui.label", current.getData().toString());
        }

        // Agregar arista desde el padre si existe
        if (parent != null) {
            String parentId = parent.getData().toString();
            String edgeId = parentId + "-" + nodeId;
            if (graph.getEdge(edgeId) == null && graph.getNode(parentId) != null) {
                graph.addEdge(edgeId, parentId, nodeId);
            }
        }

        // Recursivamente agregar hijos izquierdo y derecho
        if (current.getLeft() != null) {
            addNodeAndEdges(current, current.getLeft(), id * 2 + 1);
        }
        if (current.getRight() != null) {
            addNodeAndEdges(current, current.getRight(), id * 2 + 2);
        }
    }
} 