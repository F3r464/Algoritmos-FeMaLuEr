package ExtraP7;

import javax.swing.*;
import java.awt.*;

// 1. Estructura básica del Nodo
class Nodo {
    int dato;
    Nodo izquierdo, derecho;

    public Nodo(int dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
}

// 2. Panel donde se dibuja el árbol
class PanelArbol extends JPanel {
    private Nodo raiz;
    private final int radio = 20; // Tamaño del círculo del nodo
    private final int vGap = 50;  // Espacio vertical entre niveles

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
        repaint(); // Redibuja el panel cada vez que cambia el árbol
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            dibujarArbol(g, getWidth() / 2, 30, raiz, getWidth() / 4);
        }
    }

    private void dibujarArbol(Graphics g, int x, int y, Nodo nodo, int hGap) {
        g.setColor(Color.WHITE);
        g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
        g.setColor(Color.BLACK);
        g.drawOval(x - radio, y - radio, 2 * radio, 2 * radio);
        g.drawString(String.valueOf(nodo.dato), x - 5, y + 5);

        if (nodo.izquierdo != null) {
            g.drawLine(x, y, x - hGap, y + vGap);
            dibujarArbol(g, x - hGap, y + vGap, nodo.izquierdo, hGap / 2);
        }
        if (nodo.derecho != null) {
            g.drawLine(x, y, x + hGap, y + vGap);
            dibujarArbol(g, x + hGap, y + vGap, nodo.derecho, hGap / 2);
        }
    }
}

// 3. Ventana principal (JFrame)
public class VisualizadorBST extends JFrame {
    private Nodo raiz;
    private PanelArbol panel;

    public VisualizadorBST() {
        setTitle("Visualizador de Árbol BST");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new PanelArbol();
        add(panel, BorderLayout.CENTER);

        // Ejemplo: Insertar algunos datos automáticamente
        insertar(50); insertar(30); insertar(70); insertar(20); insertar(40);
        panel.setRaiz(raiz);
    }

    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo insertarRec(Nodo actual, int dato) {
        if (actual == null) return new Nodo(dato);
        if (dato < actual.dato) actual.izquierdo = insertarRec(actual.izquierdo, dato);
        else if (dato > actual.dato) actual.derecho = insertarRec(actual.derecho, dato);
        return actual;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VisualizadorBST().setVisible(true));
    }
}
