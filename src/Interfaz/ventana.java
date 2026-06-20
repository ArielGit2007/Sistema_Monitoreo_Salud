package Interfaz;

import javax.swing.*;

public class ventana {
   public static void main(String[] args) {
       try {
           UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
       } catch (Exception e) {
           e.printStackTrace();
       }
       JFrame Vent = new JFrame("Ventana");
       Vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Vent.setSize(800, 600);
       Vent.setVisible(true);
       ImageIcon icon = new ImageIcon("src/icono-salud.png");
       Vent.setIconImage(icon.getImage());
   }
}
