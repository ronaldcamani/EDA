import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Ejercicio4 {
    
    // Panel de visualización
    static class BTreeVisualizationPanel<E extends Comparable<E>> extends JPanel {
        private BTree<E> tree;
        private final int NODE_WIDTH = 120;
        private final int NODE_HEIGHT = 50;
        private final int LEVEL_SPACING = 100;
        private Map<BNode<E>, Point> nodePositions;
        
        public BTreeVisualizationPanel(BTree<E> tree) {
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
        
        private int calculateNodePositions(BNode<E> node, int level, int minX, int maxX) {
            if (node == null) return minX;
            
            int y = 50 + level * LEVEL_SPACING;
            
            if (node.childs.get(0) == null) { // Es hoja
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
                g2d.drawString("Árbol B vacío", 50, 50);
                return;
            }
            
            // Dibujar conexiones
            drawConnections(g2d, tree.getRoot());
            
            // Dibujar nodos
            drawNodes(g2d, tree.getRoot());
            
            // Dibujar leyenda
            drawLegend(g2d);
        }
        
        private void drawConnections(Graphics2D g2d, BNode<E> node) {
            if (node == null || node.childs.get(0) == null) return; // Es hoja
            
            Point parentPos = nodePositions.get(node);
            if (parentPos == null) return;
            
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            
            for (int i = 0; i <= node.count; i++) {
                BNode<E> child = node.childs.get(i);
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
        
        private void drawNodes(Graphics2D g2d, BNode<E> node) {
            if (node == null) return;
            
            Point pos = nodePositions.get(node);
            if (pos == null) return;
            
            // Color según tipo de nodo
            if (node.childs.get(0) == null) { // Es hoja
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
            if (node.childs.get(0) == null) { // Es hoja
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("Arial", Font.ITALIC, 10));
                g2d.drawString("HOJA", pos.x + NODE_WIDTH + 5, pos.y + NODE_HEIGHT / 2);
            }
            
            // Dibujar hijos recursivamente
            if (node.childs.get(0) != null) { // No es hoja
                for (int i = 0; i <= node.count; i++) {
                    if (node.childs.get(i) != null) {
                        drawNodes(g2d, node.childs.get(i));
                    }
                }
            }
        }
        
        private void drawLegend(Graphics2D g2d) {
            int legendX = 20;
            int legendY = getHeight() - 100;
            
            g2d.setColor(Color.WHITE);
            g2d.fillRect(legendX - 10, legendY - 10, 200, 80);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(legendX - 10, legendY - 10, 200, 80);
            
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
            
            // Conexiones
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(legendX, legendY + 55, legendX + 15, legendY + 55);
            g2d.drawString("Padre → Hijo", legendX + 20, legendY + 58);
        }
    }
    
    public static <E extends Comparable<E>> void visualizeBTree(BTree<E> tree, String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            BTreeVisualizationPanel<E> panel = new BTreeVisualizationPanel<>(tree);
            
            frame.add(new JScrollPane(panel));
            frame.setSize(1400, 800);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    public static void main(String[] args) {
        System.out.println("Ejercicio 4: Visualización Árbol B");
        
        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};
        
        // Árbol B grado 5
        BTree<Integer> btree5 = new BTree<>(5);
        for (int x : datos) btree5.insert(x);
        
        System.out.println("\nÁrbol B grado 5:");
        System.out.println(btree5);
        visualizeBTree(btree5, "Árbol B Grado 5");
        
        // Árbol B grado 4
        BTree<Integer> btree4 = new BTree<>(4);
        for (int x : datos) btree4.insert(x);
        
        System.out.println("Árbol B grado 4:");
        System.out.println(btree4);
        visualizeBTree(btree4, "Árbol B Grado 4");
    }
}