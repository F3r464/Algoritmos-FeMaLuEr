package Extra;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    AVLTree arbol =
            new AVLTree();

    Panel panel =
            new Panel();

    JTextField txtCodigo;
    JTextField txtNombre;

    JTextArea historial;

    public Dashboard() {

        setTitle(
                "Biblioteca AVL");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE);

        setLayout(
                new BorderLayout());

        // MENU
        JPanel menu =
                new JPanel();

        menu.setLayout(null);

        menu.setPreferredSize(
                new Dimension(280, 700));

        menu.setBackground(
                new Color(62,39,35));

        // TITULO
        JLabel titulo =
                new JLabel(
                        "BIBLIOTECA");

        titulo.setForeground(
                Color.WHITE);

        titulo.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        28));

        titulo.setBounds(
                40,
                30,
                250,
                40);

        menu.add(titulo);

        // CODIGO
        JLabel lblCodigo =
                new JLabel("Código");

        lblCodigo.setForeground(
                Color.WHITE);

        lblCodigo.setBounds(
                30,
                120,
                100,
                20);

        menu.add(lblCodigo);

        txtCodigo =
                new JTextField();

        txtCodigo.setBounds(
                30,
                145,
                200,
                35);

        menu.add(txtCodigo);

        // NOMBRE
        JLabel lblNombre =
                new JLabel("Libro");

        lblNombre.setForeground(
                Color.WHITE);

        lblNombre.setBounds(
                30,
                200,
                100,
                20);

        menu.add(lblNombre);

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                30,
                225,
                200,
                35);

        menu.add(txtNombre);

        // BOTONES
        JButton registrar =
                new JButton(
                        "Registrar");

        registrar.setBounds(
                30,
                300,
                200,
                40);

        menu.add(registrar);

        JButton buscar =
                new JButton(
                        "Buscar");

        buscar.setBounds(
                30,
                360,
                200,
                40);

        menu.add(buscar);

        JButton retirar =
                new JButton(
                        "Retirar");

        retirar.setBounds(
                30,
                420,
                200,
                40);

        menu.add(retirar);

        JButton catalogo =
                new JButton(
                        "Catálogo");

        catalogo.setBounds(
                30,
                480,
                200,
                40);

        menu.add(catalogo);

        // HISTORIAL
        historial =
                new JTextArea();

        historial.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(
                        historial);

        scroll.setBounds(
                20,
                550,
                230,
                90);

        menu.add(scroll);

        add(menu,
                BorderLayout.WEST);

        // PANEL AVL
        panel.setBackground(
                new Color(
                        245,
                        230,
                        204));

        add(panel,
                BorderLayout.CENTER);

        // ======================
        // REGISTRAR
        // ======================
        registrar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText());

                String nombre =
                        txtNombre.getText();

                if (nombre.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ingrese nombre");

                    return;
                }

                NodoAVL existe =
                        arbol.buscar(
                                arbol.raiz,
                                codigo);

                if (existe != null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Código ya registrado");

                    return;
                }

                arbol.registrarLibro(
                        codigo,
                        nombre);

                panel.setRaiz(
                        arbol.raiz);

                historial.append(
                        "Libro registrado\n");

                historial.append(
                        codigo
                                + " - "
                                + nombre
                                + "\n\n");

                txtCodigo.setText("");
                txtNombre.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Código inválido");
            }
        });

        // ======================
        // BUSCAR
        // ======================
        buscar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText());

                String nombre =
                        txtNombre.getText();

                NodoAVL libro =
                        arbol.buscar(
                                arbol.raiz,
                                codigo);

                if (libro == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Libro no encontrado");

                    return;
                }

                if (!libro.nombre.equalsIgnoreCase(nombre)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Datos incorrectos");

                    return;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Libro encontrado");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Error");
            }
        });

        // ======================
        // RETIRAR
        // ======================
        retirar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText());

                String nombre =
                        txtNombre.getText();

                NodoAVL libro =
                        arbol.buscar(
                                arbol.raiz,
                                codigo);

                if (libro == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Libro no existe");

                    return;
                }

                if (!libro.nombre.equalsIgnoreCase(nombre)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Datos incorrectos");

                    return;
                }

                arbol.retirarLibro(
                        codigo);

                panel.setRaiz(
                        arbol.raiz);

                historial.append(
                        "Libro retirado\n");

                historial.append(
                        codigo
                                + " - "
                                + nombre
                                + "\n\n");

                txtCodigo.setText("");
                txtNombre.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Error");
            }
        });

        // ======================
        // CATALOGO
        // ======================
        catalogo.addActionListener(e -> {

            StringBuilder sb =
                    new StringBuilder();

            sb.append(
                    "CATÁLOGO AVL\n\n");

            arbol.inOrden(
                    arbol.raiz,
                    sb);

            historial.setText(
                    sb.toString());
        });
    }
}