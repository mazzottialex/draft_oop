package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Some function that help to program GUI.
 */
public final class UtilsGUI {
    private static final int FONT_DIM = 14;
    private static final int SCALE_IMG_1 = 30;
    private static final int SCALE_IMG_2 = 90;
    private static final int EMPTY_BORD = 5;
    private static final Color BACKGROUND = new Color(240, 240, 240);

    private UtilsGUI() {
        // non istanziabile
    }

    /**
     * Returns a JPanel for displaying information about a football player.
     *
     * @param name   the name of the football player
     * @param rating the evaluation of the player
     * @param role   the role of the player
     * @param icon   specifies whether to display the player's icon
     * @return a JPanel containing the player information
     */
    public static JPanel getPanelCalciatore(final String name, final Integer rating, final String role,
            final Boolean icon) {
        URL file = null;
        if (role.equals("P")) {
            file = ClassLoader.getSystemResource("portiere.png");
        } else if (role.equals("D")) {
            file = ClassLoader.getSystemResource("difensore.png");
        } else if (role.equals("C")) {
            file = ClassLoader.getSystemResource("centrocampista.png");
        } else if (role.equals("A")) {
            file = ClassLoader.getSystemResource("attaccante.png");
        }
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        JPanel panelGiocatore = new JPanel();
        panelGiocatore.setLayout(layout);
        panelGiocatore.setBorder(new EmptyBorder(0, EMPTY_BORD, 0, EMPTY_BORD));
        JLabel lblNome = new JLabel(name);
        lblNome.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_DIM));
        JLabel lblValutazione = new JLabel(rating.toString());
        lblNome.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_DIM));
        JLabel lblIcona = new JLabel();
        ImageIcon img = new ImageIcon(file);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(SCALE_IMG_1, SCALE_IMG_1, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        lblIcona.setIcon(img);
        gbc.gridy = 0;
        if (icon) {
            panelGiocatore.add(lblIcona, gbc);
        }
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
        if (role.equals("P")) {
            file = "src/main/resources/portiere.png";
        } else if (role.equals("D")) {
            file = "src/main/resources/difensore.png";
        } else if (role.equals("C")) {
            file = "src/main/resources/centrocampista.png";
        } else if (role.equals("A")) {
            file = "src/main/resources/attaccante.png";
        }
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        JButton buttonCalciatore = new JButton();
        buttonCalciatore.setLayout(layout);
        buttonCalciatore.setBackground(BACKGROUND);
        JLabel lblNome = new JLabel(name);
        lblNome.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_DIM));
        JLabel lblIcona = new JLabel();
        ImageIcon img = new ImageIcon(file);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(SCALE_IMG_2, SCALE_IMG_2, java.awt.Image.SCALE_SMOOTH);
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
        JButton btn = new JButton(s.toUpperCase());
        btn.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_DIM));
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setForeground(Color.BLACK);
        return btn;
    }

    /**
     * Returns the width in pixels based on the given percentage of the screen width.
     *
     * @param percentage the percentage of the screen width
     * @return the width in pixels
     */
    public static int getWidth(final double perc) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        return (int) (screenWidth * perc);
    }

    /**
     * Returns the height in pixels based on the given percentage of the screen height.
     *
     * @param percentage the percentage of the screen height
     * @return the height in pixels
     */
    public static int getHeight(final double perc) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenHeight = gd.getDisplayMode().getHeight();
        return (int) (screenHeight * perc);
    }
}
