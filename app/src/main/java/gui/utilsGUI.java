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
/**
 * Some function that help to program GUI
 */
public class utilsGUI {
	/**
	 * Returns a JPanel for displaying information about a football player.
	 * 
	 * @param name the name of the football player
	 * @param rating the evaluation of the player
	 * @param role the role of the player
	 * @param icon specifies whether to display the player's icon
	 * @return a JPanel containing the player information
	 */
    public static JPanel getPanelCalciatore(final String name,
        final Integer rating, final String role, final Boolean icon) {
        String file = "";
        if (role.equals("P"))
            file = "src/main/resources/portiere.png";
        else if (role.equals("D"))
            file = "src/main/resources/difensore.png";
        else if (role.equals("C"))
            file = "src/main/resources/centrocampista.png";
        else if (role.equals("A"))
            file = "src/main/resources/attaccante.png";
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        JPanel panelGiocatore = new JPanel();
        panelGiocatore.setLayout(layout);
        panelGiocatore.setBorder(new EmptyBorder(0, 5, 0, 5));
        JLabel lblNome = new JLabel(name);
        lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        JLabel lblValutazione = new JLabel(rating.toString());
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
    
    /**
     * Returns a JButton for displaying a football player.
     * 
     * @param name the name of the football player
     * @param role the role of the player
     * @return a JButton displaying the player
     */
    public static JButton getButtonCalciatore(final String name, final String role) {
        String file = "";
        if (role.equals("P"))
            file = "src/main/resources/portiere.png";
        else if (role.equals("D"))
            file = "src/main/resources/difensore.png";
        else if (role.equals("C"))
            file = "src/main/resources/centrocampista.png";
        else if (role.equals("A"))
            file = "src/main/resources/attaccante.png";
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        JButton buttonCalciatore = new JButton();
        buttonCalciatore.setLayout(layout);
        buttonCalciatore.setBackground(new Color(240, 240, 240));
        JLabel lblNome = new JLabel(name);
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
    
    /**
     * Returns a standard JButton with the given label.
     * 
     * @param s the text of the button
     * @return a standard JButton
     */
    public static JButton standardButton(final String s) {
        JButton btn = new JButton(s);
        btn.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btn.setBackground(Color.white);
        btn.setForeground(Color.BLUE);
        return btn;
    }
}