import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Ejercicio8 {
    
    // Panel de visualización para árbol B+
    static class BPlusTreeVisualizationPanel<E extends Comparable<E>> extends JPanel {
        private BPlusTree<E> tree;
        private final int NODE_WIDTH = 120;
        private final int NODE_HEIGHT = 50;
        private final int LEVEL_SPACING = 100;
        private Map<BPlusNode<E>, Point> nodePositions;
        
        public BPlusTreeVisualizationPanel(BPlusTree<E> tree) {
            this.tree = tree;
            this.nodePositions = new HashMap<>();
            setPreferredSize(new Dimension(1200, 600));
            setBackground(Color.WHITE);
            calculatePositions();
        }
        
        private void calculatePositions() {
            if (tree.isEmpty()) return;
            calculateNodePositions(tree.getRoot(), 0, 0, getWidth());
        }
        
        private int calculateNodePositions(BPlusNode<E> node, int level, int minX, int maxX) {
            if (node == null) return minX;
            
            int y = 50 + level * LEVEL_SPACING;
            
            if (node.isLeaf) {
                int x = minX + 40;
                nodePositions.put(node, new Point(x, y));
                return x + NODE_WIDTH + 40;
            } else {
                int currentX = minX;
                for (int i = 0; i <= node.count; i++) {
                    if (node.childs.get(i) != null) {
                        currentX = calculateNodePositions(node.childs.get(i), level + 1, currentX, maxX);
                    }
                }
                
                int nodeX = (minX + currentX - NODE_WIDTH) / 2;
                nodePositions.put(node, new Point(nodeX, y));
                return currentX;
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (tree.isEmpty()) {
                g2d.drawString("Árbol B+ vacío", 50, 50);
                return;
            }
            
            // Dibujar conexiones
            drawConnections(g2d, tree.getRoot());
            
            // Dibujar nodos
            drawNodes(g2d, tree.getRoot());
            
            // Dibujar conexiones entre hojas
            drawLeafConnections(g2d);
            
            // Dibujar leyenda
            drawLegend(g2d);
        }
        
        private void drawConnections(Graphics2D g2d, BPlusNode<E> node) {
            if (node == null || node.isLeaf) return;
            
            Point parentPos = nodePositions.get(node);
            if (parentPos == null) return;
            
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            
            for (int i = 0; i <= node.count; i++) {
                BPlusNode<E> child = node.childs.get(i);
                if (child != null) {
                    Point childPos = nodePositions.get(child);
                    if (childPos != null) {
                        g2d.drawLine(
                            parentPos.x + NODE_WIDTH / 2,
                            parentPos.y + NODE_HEIGHT,
                            childPos.x + NODE_WIDTH / 2,
                            childPos.y
                        );
                        
                        // Flecha
                        drawArrow(g2d, 
                            parentPos.x + NODE_WIDTH / 2,
                            parentPos.y + NODE_HEIGHT,
                            childPos.x + NODE_WIDTH / 2,
                            childPos.y
                        );
                    }
                    drawConnections(g2d, child);
                }
            }
        }
        
        private void drawLeafConnections(Graphics2D g2d) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{5}, 0));
            
            BPlusNode<E> current = tree.getRoot();
            if (current == null) return;
            
            // Encontrar la primera hoja
            while (!current.isLeaf) {
                current = current.childs.get(0);
            }
            
            // Dibujar conexiones entre hojas
            while (current != null && current.next != null) {
                Point currentPos = nodePositions.get(current);
                Point nextPos = nodePositions.get(current.next);
                
                if (currentPos != null && nextPos != null) {
                    g2d.drawLine(
                        currentPos.x + NODE_WIDTH,
                        currentPos.y + NODE_HEIGHT / 2,
                        nextPos.x,
                        nextPos.y + NODE_HEIGHT / 2
                    );
                }
                current = current.next;
            }
        }
        
        private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
            int arrowLength = 8;
            
            double dx = x2 - x1;
            double dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            
            int[] xPoints = {
                x2,
                (int) (x2 - arrowLength * Math.cos(angle - Math.PI / 6)),
                (int) (x2 - arrowLength * Math.cos(angle + Math.PI / 6))
            };
            int[] yPoints = {
                y2,
                (int) (y2 - arrowLength * Math.sin(angle - Math.PI / 6)),
                (int) (y2 - arrowLength * Math.sin(angle + Math.PI / 6))
            };
            
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        
        private void drawNodes(Graphics2D g2d, BPlusNode<E> node) {
            if (node == null) return;
            
            Point pos = nodePositions.get(node);
            if (pos == null) return;
            
            // Color según tipo de nodo
            if (node.isLeaf) {
                g2d.setColor(new Color(144, 238, 144)); // Verde claro
            } else {
                g2d.setColor(new Color(173, 216, 230)); // Azul claro
            }
            
            // Dibujar rectángulo del nodo
            g2d.fillRoundRect(pos.x, pos.y, NODE_WIDTH, NODE_HEIGHT, 10, 10);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(pos.x, pos.y, NODE_WIDTH, NODE_HEIGHT, 10, 10);
            
            // Dibujar separadores entre claves
            if (node.count > 1) {
                int keyWidth = NODE_WIDTH / node.count;
                for (int i = 1; i < node.count; i++) {
                    int x = pos.x + i * keyWidth;
                    g2d.drawLine(x, pos.y, x, pos.y + NODE_HEIGHT);
                }
            }
            
            // Dibujar claves
            g2d.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 11);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            
            int keyWidth = NODE_WIDTH / Math.max(1, node.count);
            for (int i = 0; i < node.count; i++) {
                String keyStr = node.keys.get(i).toString();
                int textWidth = fm.stringWidth(keyStr);
                int textHeight = fm.getHeight();
                
                int textX = pos.x + i * keyWidth + (keyWidth - textWidth) / 2;
                int textY = pos.y + (NODE_HEIGHT + textHeight) / 2 - 2;
                
                g2d.drawString(keyStr, textX, textY);
            }
            
            // Etiqueta del tipo de nodo
            if (node.isLeaf) {
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("Arial", Font.ITALIC, 10));
                g2d.drawString("HOJA", pos.x + NODE_WIDTH + 5, pos.y + NODE_HEIGHT / 2);
            } else {
                g2d.setColor(Color.RED);
                g2d.setFont(new Font("Arial", Font.ITALIC, 10));
                g2d.drawString("INTERNO", pos.x + NODE_WIDTH + 5, pos.y + NODE_HEIGHT / 2);
            }
            
            // Dibujar hijos recursivamente
            if (!node.isLeaf) {
                for (int i = 0; i <= node.count; i++) {
                    if (node.childs.get(i) != null) {
                        drawNodes(g2d, node.childs.get(i));
                    }
                }
            }
        }
        
        private void drawLegend(Graphics2D g2d) {
            int legendX = 20;
            int legendY = getHeight() - 120;
            
            g2d.setColor(Color.WHITE);
            g2d.fillRect(legendX - 10, legendY - 10, 250, 100);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(legendX - 10, legendY - 10, 250, 100);
            
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("LEYENDA:", legendX, legendY);
            
            g2d.setFont(new Font("Arial", Font.PLAIN, 11));
            
            // Nodo interno
            g2d.setColor(new Color(173, 216, 230));
            g2d.fillRect(legendX, legendY + 10, 15, 15);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(legendX, legendY + 10, 15, 15);
            g2d.drawString("Nodos internos", legendX + 20, legendY + 22);
            
            // Nodo hoja
            g2d.setColor(new Color(144, 238, 144));
            g2d.fillRect(legendX, legendY + 30, 15, 15);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(legendX, legendY + 30, 15, 15);
            g2d.drawString("Nodos hoja", legendX + 20, legendY + 42);
            
            // Conexiones padre-hijo
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(legendX, legendY + 55, legendX + 15, legendY + 55);
            g2d.drawString("Padre → Hijo", legendX + 20, legendY + 58);
            
            // Conexiones entre hojas
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{5}, 0));
            g2d.drawLine(legendX, legendY + 75, legendX + 15, legendY + 75);
            g2d.drawString("Hojas enlazadas", legendX + 20, legendY + 78);
        }
    }
    
    public static <E extends Comparable<E>> void visualizeBPlusTree(BPlusTree<E> tree, String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            BPlusTreeVisualizationPanel<E> panel = new BPlusTreeVisualizationPanel<>(tree);
            
            frame.add(new JScrollPane(panel));
            frame.setSize(1400, 800);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    public static void main(String[] args) {
        System.out.println("Ejercicio 8: Visualización Gráfica de Árbol B+");
        System.out.println("=============================================\n");
        
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        // Árbol B+ grado 5
        BPlusTree<Integer> bptree5 = new BPlusTree<>(5);
        for (int x : datos) bptree5.insert(x);
        
        System.out.println("Árbol B+ grado 5:");
        System.out.println(bptree5);
        System.out.printf("Min: %d | Max: %d | Altura: %d | Nodos: %d\n", 
                         bptree5.Min(), bptree5.Max(), bptree5.getHeight(), bptree5.countNodes());
        visualizeBPlusTree(bptree5, "Árbol B+ Grado 5");
        
        // Árbol B+ grado 4
        BPlusTree<Integer> bptree4 = new BPlusTree<>(4);
        for (int x : datos) bptree4.insert(x);
        
        System.out.println("\nÁrbol B+ grado 4:");
        System.out.println(bptree4);
        System.out.printf("Min: %d | Max: %d | Altura: %d | Nodos: %d\n", 
                         bptree4.Min(), bptree4.Max(), bptree4.getHeight(), bptree4.countNodes());
        visualizeBPlusTree(bptree4, "Árbol B+ Grado 4");
        
        System.out.println("\nVisualizaciones gráficas abiertas en ventanas separadas.");
        System.out.println("Características especiales del árbol B+ mostradas:");
        System.out.println("- Nodos internos (azul) y hojas (verde)");
        System.out.println("- Conexiones padre-hijo (líneas negras)");
        System.out.println("- Enlaces entre hojas (líneas rojas punteadas)");
    }
} 