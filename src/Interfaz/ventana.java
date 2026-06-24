package Interfaz;

import Modelo.Persona;
import Modelo.historial;
import Negocio.sistemaSalud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
            v.crearPanelRegistro();
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

        if (agua < 0 ) return false;

        if (duracion < 0 ) return false;

        return true;
    }
    private void crearPanelRegistro() {

        Persona p = new Persona();
        p=sistema.getPersonaActual();
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


        if (p==null){
            panelSuperior.add(Box.createVerticalGlue());
            panelSuperior.add(titulo);
            panelSuperior.add(Box.createVerticalStrut(8));
            panelSuperior.add(subtitulo);
            panelSuperior.add(Box.createVerticalGlue());
        }
        else {
            panelSuperior.add(Box.createVerticalStrut(8));
            panelSuperior.add(titulo);
            panelSuperior.setPreferredSize(new Dimension(1000, 75));
        }

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


        boolean IsUpdate = (p != null);
        if (IsUpdate) {
            textNombre.setText(p.getNombre());
            textApellido.setText(p.getApellido());

            textNombre.setEditable(false);
            textApellido.setEditable(false);
        } else {
            textNombre.setEditable(true);
            textApellido.setEditable(true);
        }

        String[] etiquetas = {
                "Nombre",
                "Apellido",
                "Edad",
                "Altura (m)",
                "Peso (kg)",
                "Género",
                "Nivel de Estrés (1-10)",
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

        JLabel errNombre = new JLabel();
        JLabel errApellido = new JLabel();
        JLabel errEdad = new JLabel();
        JLabel errAltura = new JLabel();
        JLabel errPeso = new JLabel();
        JLabel errEstres = new JLabel();
        JLabel errSueno = new JLabel();
        JLabel errFC = new JLabel();
        JLabel errAgua = new JLabel();
        JLabel errActividad = new JLabel();
        JLabel errDuracion = new JLabel();

        for (int i = 0; i < etiquetas.length; i++) {

            gbc.gridy = i;

            // label izquierda
            gbc.gridx = 0;
            JLabel lbl = new JLabel(etiquetas[i]);
            panelForm.add(lbl, gbc);

            // campo centro
            gbc.gridx = 1;
            campos[i].setPreferredSize(new Dimension(250, 30));
            panelForm.add(campos[i], gbc);

            // error derecha
            gbc.gridx = 2;

            JLabel err = null;

            switch (i) {
                case 0 ->err=errNombre;
                case 1 ->err=errApellido;
                case 2 -> err = errEdad;
                case 3 -> err = errAltura;
                case 4 -> err = errPeso;
                case 6 -> err = errEstres;
                case 7 -> err = errSueno;
                case 8 -> err = errFC;
                case 9 -> err = errAgua;
                case 10 -> err = errActividad;
                case 11 -> err = errDuracion;
            }

            if (err != null) {
                err.setForeground(Color.RED);
                err.setPreferredSize(new Dimension(120, 20));
                panelForm.add(err, gbc);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panelForm);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBlockIncrement(64);
        panelRegistro.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);

        JButton btnRegistrar = new JButton("Registrar");
        if (p != null) {
            btnRegistrar.setText("Actualizar");
        }
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
        if (p != null) {
            JButton btnVolver;
            btnVolver=crearBotonVolver();
            btnVolver.setBackground(new Color(0, 170, 255));
            btnVolver.setForeground(Color.WHITE);
            btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnVolver.setFocusPainted(false);
            btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnVolver.setBackground(new Color(33, 159, 255));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnVolver.setBackground(new Color(0, 170, 255));
                }
            });
            panelBotones.add(Box.createHorizontalStrut(20));
            panelBotones.add(btnVolver);
        }

        panelRegistro.add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> {
            try {
                clearErrors(errNombre,errApellido,errEdad, errAltura, errPeso, errEstres,
                        errSueno, errFC, errAgua, errActividad, errDuracion);

                if (textNombre.getText().trim().isEmpty()) {
                    setError(errNombre, "Requerido");
                }

                if (textApellido.getText().trim().isEmpty()) {
                    setError(errApellido, "Requerido");
                }

                if (textEdad.getText().trim().isEmpty()) {
                    setError(errEdad, "Requerido");
                }else {
                    try {
                        int edad = Integer.parseInt(textEdad.getText());

                        if (edad <= 0 || edad > 120) {
                            setError(errEdad, "Edad inválida");
                        }

                    } catch (NumberFormatException a) {
                        setError(errEdad, "Debe ser número");
                    }
                }

                if (textAltura.getText().trim().isEmpty()) {
                    setError(errAltura, "Requerido");
                }else {
                    try {
                        double altura = Double.parseDouble(textAltura.getText());

                        if (altura <= 0 || altura > 3) {
                            setError(errAltura, "Altura inválida");
                        }

                    } catch (NumberFormatException a) {
                        setError(errAltura, "Debe ser número");
                    }
                }

                if (textPeso.getText().trim().isEmpty()) {
                    setError(errPeso, "Requerido");

                }else {
                    try {
                        double peso = Double.parseDouble(textPeso.getText());

                        if (peso <= 0 || peso > 500) {
                            setError(errPeso, "Peso inválido");
                        }

                    } catch (NumberFormatException a) {
                        setError(errPeso, "Debe ser número");
                    }
                }

                if (textEstres.getText().trim().isEmpty()) {
                    setError(errEstres, "Requerido");
                }else{
                    try {
                        int estres = Integer.parseInt(textEstres.getText());

                        if (estres < 0 || estres > 10) {
                            setError(errEstres, "Estrés inválido");
                        }

                    } catch (NumberFormatException a) {
                        setError(errEstres, "Debe ser número");
                    }
                }


                if (textSueno.getText().trim().isEmpty()) {
                    setError(errSueno, "Requerido");
                }else{
                    try{
                        double sueño = Double.parseDouble(textSueno.getText());
                        if (sueño < 0 || sueño > 24) {
                            setError(errSueno, "Sueño inválido");

                        }

                    }catch(NumberFormatException a){
                        setError(errSueno, "Debe ser número");

                    }
                }

                if (textFC.getText().trim().isEmpty()) {
                    setError(errFC, "Requerido");

                }else {
                    try{
                        int fc = Integer.parseInt(textFC.getText());
                        if (fc <= 0 || fc > 250) {
                            setError(errFC, "F.C. inválida");

                        }

                    }catch(NumberFormatException a){
                        setError(errFC, "Debe ser número");
                    }
                }

                if (textAgua.getText().trim().isEmpty()) {
                    setError(errAgua, "Requerido");
                }else {
                    try{
                        double agua = Double.parseDouble(textAgua.getText());
                        if (agua < 0) {
                            setError(errAgua, "Agua inválida");

                        }

                    }catch(NumberFormatException a){
                        setError(errAgua, "Debe ser número");

                    }
                }

                if (textActividad.getText().trim().isEmpty()) {
                    setError(errActividad, "Requerido");
                }

                if (textDuracion.getText().trim().isEmpty()) {
                    setError(errDuracion, "Requerido");

                }else {
                    try{
                        int duracion = Integer.parseInt(textDuracion.getText());
                        if (duracion < 0) {
                            setError(errDuracion, "Duración inválida");
                        }

                    }catch(NumberFormatException a){
                        setError(errDuracion, "Debe ser número");
                    }
                }


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
            boolean esUpdate = sistema.getPersonaActual() != null;
            if(!esUpdate){
                textNombre.setText("");
                textApellido.setText("");
            }

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
        JButton btnSalir = new JButton("5. Salir");

        Dimension btssize = new Dimension(200, 30);

        btnVerDatos.setPreferredSize(btssize);
        btnActualizar.setPreferredSize(btssize);
        btnReporte.setPreferredSize(btssize);
        btnHistorial.setPreferredSize(btssize);
        btnSalir.setPreferredSize(btssize);

        btnVerDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReporte.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        btnSalir.setBackground(azul);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(font);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);

        btnVerDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerDatos.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerDatos.setBackground(new Color(0, 120, 215));
            }
        });
        btnVerDatos.addActionListener(e -> {
            crearPanelDatos();
            mostrarPanel(panelDatos);
        });

        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(0, 120, 215));
            }
        });
        btnActualizar.addActionListener(e -> {
            crearPanelRegistro();
            mostrarPanel(panelRegistro);});

        btnReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporte.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporte.setBackground(new Color(0, 120, 215));
            }
        });
    btnReporte.addActionListener(e -> {
        crearPanelReporte();
            mostrarPanel(panelReporte);
    });


        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistorial.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistorial.setBackground(new Color(0, 120, 215));
            }
        });
        btnHistorial.addActionListener(e -> {
            crearPanelHistorial();
            mostrarPanel(panelHistorial);
        });


        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(0, 120, 215));
            }
        });
        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estás seguro de que deseas salir del sistema?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
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
        centro.add(Box.createVerticalStrut(10));
        centro.add(btnSalir);
        centro.add(Box.createVerticalGlue());

        panelPrincipal.add(centro, BorderLayout.CENTER);


    }


    private void crearPanelDatos(){
        panelDatos = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Datos del usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titulo.setForeground(new Color(74, 176, 243));
        panelDatos.add(titulo, BorderLayout.NORTH);
        panelDatos.setBackground(new Color(195, 250, 241));

        JTextArea areaDatos = new JTextArea();
        areaDatos.setEditable(false);
        areaDatos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaDatos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        Persona p = sistema.getPersonaActual();
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(p.getNombre()).append(" ").append(p.getApellido()).append("\n");
        sb.append("Edad: ").append(p.getEdad()).append("\n");
        sb.append("Altura: ").append(p.getAltura()).append(" m\n");
        sb.append("Peso: ").append(p.getPeso()).append(" kg\n");
        sb.append("Género: ").append(p.getGenero()).append("\n");
        sb.append("Nivel de Estrés: ").append(p.med.getNivelEstres()).append("\n");
        sb.append("Horas de Sueño: ").append(p.med.getHorasSueño()).append("\n");
        sb.append("Frecuencia Cardíaca: ").append(p.med.getFrecuenciaCardiaca()).append("\n");
        sb.append("Consumo de Agua: ").append(p.med.getConsumoAgua()).append(" L\n");
        sb.append("Actividad Física: ").append(p.actF.tipoActividad).append("\n");
        sb.append("Duración de Actividad: ").append(p.actF.duracionMinutos).append(" min\n");

        JButton btnVolver;
        btnVolver=crearBotonVolver();
        btnVolver.setPreferredSize(new Dimension(200, 30));
        btnVolver.setBackground(new Color(0, 120, 215));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(0, 120, 215));
            }
        });

        areaDatos.setText(sb.toString());

        panelDatos.add(new JScrollPane(areaDatos), BorderLayout.CENTER);
        panelDatos.add(btnVolver, BorderLayout.SOUTH);
    }
    private void crearPanelHistorial(){
        panelHistorial = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Historial", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titulo.setForeground(new Color(74, 176, 243));
        panelHistorial.add(titulo, BorderLayout.NORTH);
        panelHistorial.setBackground(new Color(195, 250, 241));
        String[] columnas = {
                "Nombre",
                "Edad",
                "Peso (kg)",
                "Altura (m)",
                "IMC",
                "Estrés",
                "Sueño (h)",
                "F.C.",
                "Agua (L)",
                "Actividad",
                "Duración (min)"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);

        tabla.setRowHeight(25);
        tabla.setEnabled(false);

        JScrollPane scroll = new JScrollPane(tabla);
        panelHistorial.add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();

        JButton btn3 = new JButton("Últimos 3");
        JButton btn5 = new JButton("Últimos 5");
        JButton btn10 = new JButton("Últimos 10");
        JButton btnVolver = crearBotonVolver();

        btn3.addActionListener(e -> {
            if (sistema.obtenerHistorial().Personas.size() < 4) {
                JOptionPane.showMessageDialog(null,
                        "No hay suficientes registros en el historial",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                cargarHistorial(modelo, 3);
            }
        });
        btn5.addActionListener(e -> {
            if (sistema.obtenerHistorial().Personas.size() < 6) {
                JOptionPane.showMessageDialog(null,
                        "No hay suficientes registros en el historial",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                cargarHistorial(modelo, 5);
            }
        });
        btn10.addActionListener(e -> {
            if (sistema.obtenerHistorial().Personas.size() < 11) {
                JOptionPane.showMessageDialog(null,
                        "No hay suficientes registros en el historial",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                cargarHistorial(modelo, 10);
            }
        });

        panelInferior.add(btn3);
        panelInferior.add(btn5);
        panelInferior.add(btn10);
        panelInferior.add(btnVolver);

        panelHistorial.add(panelInferior, BorderLayout.SOUTH);


    }

    private void crearPanelReporte(){
        panelReporte = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Reporte de salud", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titulo.setForeground(new Color(74, 176, 243));
        panelReporte.add(titulo, BorderLayout.NORTH);


        sistema.cargarDatosGuardados();
        JTextArea areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setBackground(new Color(195, 250, 241));
        areaReporte.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        areaReporte.setText(sistema.generarReporte());
        areaReporte.setLineWrap(true);
        areaReporte.setWrapStyleWord(true);

        panelReporte.add(areaReporte, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(areaReporte);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panelReporte.add(scroll, BorderLayout.CENTER);

        JButton btnVolver;
        btnVolver=crearBotonVolver();
        btnVolver.setBackground(new Color(0, 170, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(33, 159, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(0, 170, 255));
            }
        });

        panelReporte.add(btnVolver, BorderLayout.SOUTH);


    }
    private void cargarHistorial(DefaultTableModel modelo, int cantidad) {

        historial h = sistema.obtenerHistorial();

        h.mostrarUltimos(cantidad);

        modelo.setRowCount(0);

        for (int i = 0; i < h.ultimosPersonas.size(); i++) {

            Persona p = h.ultimosPersonas.get(i);

            double imc = p.getPeso() / (p.getAltura() * p.getAltura());

            modelo.addRow(new Object[]{
                    p.getNombre() + " " + p.getApellido(),
                    p.getEdad(),
                    p.getPeso(),
                    p.getAltura(),
                    String.format("%.2f", imc),
                    p.med.getNivelEstres(),
                    p.med.getHorasSueño(),
                    p.med.getFrecuenciaCardiaca(),
                    p.med.getConsumoAgua(),
                    p.actF.tipoActividad,
                    p.actF.duracionMinutos
            });
        }
    }

    private void clearErrors(JLabel... labels) {
        for (JLabel l : labels) {
            l.setText("");
        }
    }

    private void setError(JLabel lbl, String msg) {
        lbl.setText(msg);
        lbl.setForeground(Color.RED);
    }

    private JButton crearBotonVolver() {
        JButton btn = new JButton("← Volver al menú");
        btn.addActionListener(e -> mostrarPanel(panelPrincipal));
        return btn;
    }
}