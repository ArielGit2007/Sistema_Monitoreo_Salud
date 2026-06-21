package Interfaz;

import Negocio.sistemaSalud;

import javax.swing.*;
import java.awt.*;

public class ventana {
    public JPanel panelContenedor = new JPanel(new BorderLayout());
    public sistemaSalud sistema = new sistemaSalud();

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame Vent = new JFrame("Sistema de Salud - Principal");
        Vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vent.setSize(800, 500);
        Vent.setLocationRelativeTo(null);
        Vent.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("src/icono-salud.png");
        Vent.setIconImage(icon.getImage());

        ventana v = new ventana();

        Vent.add(v.panelContenedor);

        v.crearPanelRegistro();
        v.crearPanelPrincipal();

        try{
            v.sistema.cargarDatosGuardados();
        }catch(Exception e){
            e.printStackTrace();
        }

        if(v.sistema.getPersonaActual()!=null){
            v.mostrarPanel(v.panelPrincipal);
        }
        else{
            v.mostrarPanel(v.panelRegistro);

        }


        Vent.setVisible(true);
    }
    private JPanel panelRegistro;
    private JPanel panelPrincipal;
    private JPanel panelDatos;
    private JPanel panelReporte;
    private JPanel panelHistorial;
    private void mostrarPanel(JPanel panel) {
        panelContenedor.removeAll();
        panelContenedor.add(panel);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }
    private boolean validarDatos(String nombre, String apellido,
                                 int edad, double altura, double peso,
                                 int estres, double sueno,
                                 int fc, double agua,
                                 int duracion) {

        if (nombre.trim().isEmpty() || apellido.trim().isEmpty()) return false;

        if (edad <= 0 || edad > 120) return false;

        if (altura <= 0 || altura > 3) return false;

        if (peso <= 0 || peso > 500) return false;

        if (estres < 0 || estres > 10) return false;

        if (sueno < 0 || sueno > 24) return false;

        if (fc <= 0 || fc > 250) return false;

        if (agua < 0 || agua > 10) return false;

        if (duracion <= 0 || duracion > 1440) return false;

        return true;
    }
    private void crearPanelRegistro() {

        panelRegistro = new JPanel(new BorderLayout());
        panelRegistro.setBackground(new Color(240, 248, 255));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 170, 255));
        panelSuperior.setPreferredSize(new Dimension(1000, 120));
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Sistema de Salud");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Primer registro del usuario");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelSuperior.add(Box.createVerticalGlue());
        panelSuperior.add(titulo);
        panelSuperior.add(Box.createVerticalStrut(8));
        panelSuperior.add(subtitulo);
        panelSuperior.add(Box.createVerticalGlue());

        panelRegistro.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField textNombre = new JTextField(20);
        JTextField textApellido = new JTextField(20);
        JTextField textEdad = new JTextField(20);
        JTextField textAltura = new JTextField(20);
        JTextField textPeso = new JTextField(20);
        JComboBox<String> comboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        JTextField textEstres = new JTextField(20);
        JTextField textSueno = new JTextField(20);
        JTextField textFC = new JTextField(20);
        JTextField textAgua = new JTextField(20);
        JTextField textActividad = new JTextField(20);
        JTextField textDuracion = new JTextField(20);

        String[] etiquetas = {
                "Nombre",
                "Apellido",
                "Edad",
                "Altura (m)",
                "Peso (kg)",
                "Género",
                "Nivel de Estrés",
                "Horas de Sueño",
                "Frecuencia Cardíaca",
                "Consumo de Agua (L)",
                "Actividad Física",
                "Duración (min)"
        };

        Component[] campos = {
                textNombre,
                textApellido,
                textEdad,
                textAltura,
                textPeso,
                comboGenero,
                textEstres,
                textSueno,
                textFC,
                textAgua,
                textActividad,
                textDuracion
        };

        for (int i = 0; i < etiquetas.length; i++) {

            gbc.gridx = 0;
            gbc.gridy = i;

            JLabel lbl = new JLabel(etiquetas[i]);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
            panelForm.add(lbl, gbc);

            gbc.gridx = 1;
            campos[i].setPreferredSize(new Dimension(250, 30));
            panelForm.add(campos[i], gbc);
        }

        panelRegistro.add(new JScrollPane(panelForm), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnRegistrar.setBackground(new Color(0, 170, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);

        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLimpiar.setFocusPainted(false);

        panelBotones.add(btnRegistrar);
        panelBotones.add(Box.createHorizontalStrut(20));
        panelBotones.add(btnLimpiar);

        panelRegistro.add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> {
            try {

                String nombre = textNombre.getText();
                String apellido = textApellido.getText();
                int edad = Integer.parseInt(textEdad.getText());
                double altura = Double.parseDouble(textAltura.getText());
                double peso = Double.parseDouble(textPeso.getText());
                String genero = (String) comboGenero.getSelectedItem();
                int estres = Integer.parseInt(textEstres.getText());
                double sueno = Double.parseDouble(textSueno.getText());
                int fc = Integer.parseInt(textFC.getText());
                double agua = Double.parseDouble(textAgua.getText());
                String actividad = textActividad.getText();
                int duracion = Integer.parseInt(textDuracion.getText());

                if (!validarDatos(nombre, apellido, edad, altura, peso, estres, sueno, fc, agua, duracion)) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor ingresa valores válidos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }


                sistema.registrarPersona(nombre, apellido, edad, altura, peso, genero,
                        estres, sueno, fc, agua, actividad, duracion);

                JOptionPane.showMessageDialog(null, "Registro exitoso");

                mostrarPanel(panelPrincipal);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Por favor ingresa valores válidos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpiar.addActionListener(e -> {
            textNombre.setText("");
            textApellido.setText("");
            textEdad.setText("");
            textAltura.setText("");
            textPeso.setText("");
            textEstres.setText("");
            textSueno.setText("");
            textFC.setText("");
            textAgua.setText("");
            textActividad.setText("");
            textDuracion.setText("");
            comboGenero.setSelectedIndex(0);
        });
    }
    private void crearPanelPrincipal() {

        panelPrincipal = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("SISTEMA DE SALUD", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(new Color(74, 176, 243));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.setBackground(new Color(195, 250, 241));

        JPanel centro = new JPanel();
        centro.setBackground(Color.WHITE);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.setMaximumSize(new Dimension(300, 300));
        centro.setPreferredSize(new Dimension(300, 250));
        centro.setBackground(new Color(195, 250, 241));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton btnVerDatos = new JButton("1. Ver datos");
        JButton btnActualizar = new JButton("2. Actualizar datos");
        JButton btnReporte = new JButton("3. Generar reporte");
        JButton btnHistorial = new JButton("4. Acceder al historial");

        Dimension btssize = new Dimension(200, 30);

        btnVerDatos.setPreferredSize(btssize);
        btnActualizar.setPreferredSize(btssize);
        btnReporte.setPreferredSize(btssize);
        btnHistorial.setPreferredSize(btssize);

        btnVerDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReporte.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);

        Color azul = new Color(0, 120, 215);
        Font font = new Font("Sans Serif", Font.PLAIN, 16);

        btnVerDatos.setBackground(azul);
        btnVerDatos.setForeground(Color.WHITE);
        btnVerDatos.setFont(font);
        btnVerDatos.setFocusPainted(false);
        btnVerDatos.setBorderPainted(false);

        btnActualizar.setBackground(azul);
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(font);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);

        btnReporte.setBackground(azul);
        btnReporte.setForeground(Color.WHITE);
        btnReporte.setFont(font);
        btnReporte.setFocusPainted(false);
        btnReporte.setBorderPainted(false);

        btnReporte.setBackground(azul);
        btnReporte.setForeground(Color.WHITE);
        btnReporte.setFont(font);
        btnReporte.setFocusPainted(false);
        btnReporte.setBorderPainted(false);

        btnHistorial.setBackground(azul);
        btnHistorial.setForeground(Color.WHITE);
        btnHistorial.setFont(font);
        btnHistorial.setFocusPainted(false);
        btnHistorial.setBorderPainted(false);

        btnVerDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerDatos.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerDatos.setBackground(new Color(0, 120, 215));
            }
        });

        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(0, 120, 215));
            }
        });
        btnReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporte.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporte.setBackground(new Color(0, 120, 215));
            }
        });

        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistorial.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistorial.setBackground(new Color(0, 120, 215));
            }
        });

        centro.add(Box.createVerticalStrut(15));
        centro.add(btnVerDatos);
        centro.add(Box.createVerticalStrut(10));
        centro.add(btnActualizar);
        centro.add(Box.createVerticalStrut(10));
        centro.add(btnReporte);
        centro.add(Box.createVerticalStrut(10));
        centro.add(btnHistorial);
        centro.add(Box.createVerticalGlue());

        panelPrincipal.add(centro, BorderLayout.CENTER);


    }
}