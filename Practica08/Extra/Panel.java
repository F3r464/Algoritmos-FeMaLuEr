package Extra;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private NodoAVL raiz;

    public void setRaiz(
            NodoAVL raiz) {

        this.raiz = raiz;

        repaint();
    }

    @Override
    protected void paintComponent(
            Graphics g) {

        super.paintComponent(g);

        if (raiz != null) {

            dibujar(
                    g,
                    raiz,
                    getWidth() / 2,
                    60,
                    getWidth() / 4);
        }
    }

    private void dibujar(
            Graphics g,
            NodoAVL nodo,
            int x,
            int y,
            int espacio) {

        // IZQUIERDA
        if (nodo.izquierda != null) {

            g.drawLine(
                    x,
                    y,
                    x - espacio,
                    y + 70);

            dibujar(
                    g,
                    nodo.izquierda,
                    x - espacio,
                    y + 70,
                    espacio / 2);
        }

        // DERECHA
        if (nodo.derecha != null) {

            g.drawLine(
                    x,
                    y,
                    x + espacio,
                    y + 70);

            dibujar(
                    g,
                    nodo.derecha,
                    x + espacio,
                    y + 70,
                    espacio / 2);
        }

        // NODO
        g.setColor(
                new Color(141,110,99));

        g.fillOval(
                x - 25,
                y - 25,
                50,
                50);

        g.setColor(Color.WHITE);

        g.drawString(
                nodo.codigo + "",
                x - 10,
                y + 5);

        // BF
        g.setColor(Color.BLACK);

        g.drawString(
                "BF: " + nodo.bf,
                x - 20,
                y + 40);
    }
}