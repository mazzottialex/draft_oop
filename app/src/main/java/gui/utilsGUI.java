package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class utilsGUI {
    public static JPanel getPanelCalciatore(final String calciatore,
        final Integer valutazione, final String ruolo, final Boolean icon) {
        String file = "";
        if (ruolo.equals("P"))
            file = "src/main/resources/portiere.png";
        else if (ruolo.equals("D"))
            file = "src/main/resources/difensore.png";
        else if (ruolo.equals("C"))
            file = "src/main/resources/centrocampista.png";
        else if (ruolo.equals("A"))
            file = "src/main/resources/attaccante.png";
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        JPanel panelGiocatore = new JPanel();
        panelGiocatore.setLayout(layout);
        panelGiocatore.setBorder(new EmptyBorder(0, 5, 0, 5));
        JLabel lblNome = new JLabel(calciatore);
        lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        JLabel lblValutazione = new JLabel(valutazione.toString());
        lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        JLabel lblIcona = new JLabel();
        ImageIcon img = new ImageIcon(file);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newimg);
        lblIcona.setIcon(img);
        gbc.gridy = 0;
        if (icon)
            panelGiocatore.add(lblIcona, gbc);
        gbc.gridy = 1;
        panelGiocatore.add(lblNome, gbc);
        gbc.gridy = 2;
        panelGiocatore.add(lblValutazione, gbc);
        return panelGiocatore;
    }
    public static JButton getButtonCalciatore(final String calciatore, final String ruolo) {
        String file = "";
        if (ruolo.equals("P"))
            file = "src/main/resources/portiere.png";
        else if (ruolo.equals("D"))
            file = "src/main/resources/difensore.png";
        else if (ruolo.equals("C"))
            file = "src/main/resources/centrocampista.png";
        else if (ruolo.equals("A"))
            file = "src/main/resources/attaccante.png";
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        JButton buttonCalciatore = new JButton();
        buttonCalciatore.setLayout(layout);
        buttonCalciatore.setBackground(new Color(240, 240, 240));
        JLabel lblNome = new JLabel(calciatore);
        lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        JLabel lblIcona = new JLabel();
        ImageIcon img = new ImageIcon(file);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newimg);
        lblIcona.setIcon(img);
        gbc.gridy = 0;
        buttonCalciatore.add(lblIcona, gbc);
        gbc.gridy = 1;
        buttonCalciatore.add(lblNome, gbc);
        return buttonCalciatore;
    }
    public static JButton standardButton(final String s) {
        JButton btn = new JButton(s);
        btn.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btn.setBackground(Color.white);
        btn.setForeground(Color.BLUE);
        return btn;
    }
}