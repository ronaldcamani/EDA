import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AVLTreeVisualizer<T extends Comparable<T>> extends JPanel {
  private AVLTree<T> tree;
  private static final int NODE_RADIUS = 25;
  private static final int LEVEL_HEIGHT = 80;
  private static final int MIN_HORIZONTAL_DISTANCE = 60;

  public AVLTreeVisualizer(AVLTree<T> avlTree) {
    this.tree = avlTree;
    setPreferredSize(new Dimension(800, 600));
    setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (tree.getRoot() != null) {
      int width = getWidth();
      drawTree(g2d, tree.getRoot(), width / 2, 50, width / 4);
    } else {
      g2d.setFont(new Font("Arial", Font.BOLD, 16));
      g2d.setColor(Color.GRAY);
      String message = "Árbol vacío";
      FontMetrics fm = g2d.getFontMetrics();
      int x = (getWidth() - fm.stringWidth(message)) / 2;
      int y = getHeight() / 2;
      g2d.drawString(message, x, y);
    }
  }

  private void drawTree(Graphics2D g2d, NodeAVL<T> node, int x, int y, int horizontalDistance) {
    if (node == null)
      return;

    // Dibujar las conexiones primero
    if (node.getLeft() != null) {
      int leftX = x - horizontalDistance;
      int leftY = y + LEVEL_HEIGHT;
      drawConnection(g2d, x, y, leftX, leftY);
      drawTree(g2d, node.getLeft(), leftX, leftY, horizontalDistance / 2);
    }

    if (node.getRight() != null) {
      int rightX = x + horizontalDistance;
      int rightY = y + LEVEL_HEIGHT;
      drawConnection(g2d, x, y, rightX, rightY);
      drawTree(g2d, node.getRight(), rightX, rightY, horizontalDistance / 2);
    }

    // Dibujar el nodo encima de las conexiones
    drawNode(g2d, node, x, y);
  }

  private void drawConnection(Graphics2D g2d, int x1, int y1, int x2, int y2) {
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2));
    g2d.drawLine(x1, y1, x2, y2);
  }

  private void drawNode(Graphics2D g2d, NodeAVL<T> node, int x, int y) {
    // Dibujar el círculo del nodo
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);

    // Dibujar el borde del nodo
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2));
    g2d.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);

    // Dibujar el valor del nodo
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, 14));
    String text = node.getData().toString();
    FontMetrics fm = g2d.getFontMetrics();
    int textWidth = fm.stringWidth(text);
    int textHeight = fm.getAscent();
    g2d.drawString(text, x - textWidth / 2, y + textHeight / 2 - 2);

    // Dibujar la altura del nodo (pequeño)
    g2d.setFont(new Font("Arial", Font.PLAIN, 10));
    g2d.setColor(Color.BLUE);
    String heightText = "h:" + node.getHeight();
    FontMetrics fmHeight = g2d.getFontMetrics();
    int heightWidth = fmHeight.stringWidth(heightText);
    g2d.drawString(heightText, x - heightWidth / 2, y + NODE_RADIUS + 15);
  }

  public void updateTree() {
    repaint();
  }

  public static <T extends Comparable<T>> void showTreeVisualization(AVLTree<T> tree, String title) {
    JFrame frame = new JFrame(title);
    AVLTreeVisualizer<T> visualizer = new AVLTreeVisualizer<>(tree);

    frame.add(visualizer);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
