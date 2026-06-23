package Practica11.Interfaz.src;

import javax.swing.*;
import java.awt.*;

public class HashGamePanel extends JFrame {

    private HashTable table;
    private JTextArea log;
    private JPanel grid;

    public HashGamePanel() {

        table = new HashTable(10);

        setTitle("HashVerse - Game");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel background = new JPanel();
        background.setBackground(new Color(20, 20, 20));
        background.setLayout(null);
        background.setBounds(0, 0, 1000, 600);
        add(background);

        JLabel info = new JLabel("Jugador: " + GameData.jugador + " | Crypto: " + GameData.crypto + " (" + GameData.simbolo + ")");
        info.setBounds(20, 10, 800, 30);
        info.setFont(new Font("Consolas", Font.BOLD, 14));
        info.setForeground(Color.GREEN);
        background.add(info);

        JButton insertar = crearBoton("Insertar Jugador");
        insertar.setBounds(20, 50, 180, 35);

        JButton generar = crearBoton("Generar");
        generar.setBounds(210, 50, 150, 35);

        JButton limpiar = crearBoton("Limpiar");
        limpiar.setBounds(370, 50, 150, 35);

        JButton volver = crearBoton("Volver");
        volver.setBounds(530, 50, 150, 35);

        log = new JTextArea();
        log.setBounds(20, 100, 450, 430);
        log.setBackground(Color.BLACK);
        log.setForeground(Color.GREEN);
        log.setFont(new Font("Consolas", Font.PLAIN, 12));
        log.setEditable(false);

        grid = new JPanel();
        grid.setBounds(500, 100, 450, 430);
        grid.setLayout(new GridLayout(10, 1));
        grid.setBackground(new Color(30, 30, 30));

        for (int i = 0; i < 10; i++) {
            JLabel cell = new JLabel(i + " -> vacío");
            cell.setForeground(Color.GREEN);
            cell.setFont(new Font("Consolas", Font.PLAIN, 14));
            grid.add(cell);
        }

        insertar.addActionListener(e -> insertarJugadorManual());
        generar.addActionListener(e -> generarAleatorio());
        limpiar.addActionListener(e -> log.setText(""));

        volver.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        background.add(insertar);
        background.add(generar);
        background.add(limpiar);
        background.add(volver);
        background.add(log);
        background.add(grid);

        setVisible(true);

        refrescarTabla();
    }

    private JButton crearBoton(String texto) {

        JButton b = new JButton(texto);
        b.setBackground(new Color(50, 50, 50));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Consolas", Font.BOLD, 12));
        b.setFocusPainted(false);

        return b;
    }

    private void insertarJugadorManual() {

        String idStr = JOptionPane.showInputDialog("ID del jugador:");
        String nombre = JOptionPane.showInputDialog("Nombre:");

        if (idStr == null || nombre == null) return;

        try {
            int id = Integer.parseInt(idStr);

            Register r = new Register(id, nombre, 100);

            table.insertVisual(r);

            log.append("Insertado: " + r + "\n");

            refrescarTabla();

        } catch (Exception e) {
            log.append("ID inválido\n");
        }
    }

    private void generarAleatorio() {

        int id = (int)(Math.random() * 200);
        String name = "Player" + id;

        Register r = new Register(id, name, 100);

        table.insertVisual(r);

        log.append("Generado: " + r + "\n");

        refrescarTabla();
    }

    private void refrescarTabla() {

        Register[] t = table.getTable();

        for (int i = 0; i < t.length; i++) {

            JLabel label = (JLabel) grid.getComponent(i);

            if (t[i] != null) {
                label.setText(i + " -> " + t[i].getNombre() + " (ID " + t[i].getId() + ")");
                label.setForeground(Color.GREEN);
            } else {
                label.setText(i + " -> vacío");
                label.setForeground(Color.GRAY);
            }
        }
    }
}