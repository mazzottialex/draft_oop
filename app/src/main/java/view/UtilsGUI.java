package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import java.util.Locale;

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
    private static final int EMPTY_BORD = 5;
    private static final String FONT = "Verdana";
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
        URL file = ClassLoader.getSystemResource("attaccante.png");
        if ("P".equals(role)) {
            file = ClassLoader.getSystemResource("portiere.png");
        } else if ("D".equals(role)) {
            file = ClassLoader.getSystemResource("difensore.png");
        } else if ("C".equals(role)) {
            file = ClassLoader.getSystemResource("centrocampista.png");
        }
        final GridBagConstraints gbc = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final JPanel panelGiocatore = new JPanel();
        panelGiocatore.setLayout(layout);
        panelGiocatore.setBorder(new EmptyBorder(0, EMPTY_BORD, 0, EMPTY_BORD));
        final JLabel lblNome = new JLabel(name);
        lblNome.setFont(new Font(FONT, Font.ROMAN_BASELINE, FONT_DIM));
        final JLabel lblValutazione = new JLabel(rating.toString());
        lblNome.setFont(new Font(FONT, Font.ROMAN_BASELINE, FONT_DIM));
        final JLabel lblIcona = new JLabel();
        ImageIcon img = new ImageIcon(file);
        final Image image = img.getImage();
        final Image newimg = image.getScaledInstance(SCALE_IMG_1, SCALE_IMG_1, java.awt.Image.SCALE_SMOOTH);
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
     * Returns a standard JButton with the given label.
     *
     * @param s the text of the button
     * @return a standard JButton
     */
    public static JButton standardButton(final String s) {
    	final JButton btn = new JButton(s.toUpperCase(Locale.getDefault()));
        btn.setFont(new Font(FONT, Font.ROMAN_BASELINE, FONT_DIM));
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setForeground(Color.BLACK);
        return btn;
    }

    /**
     * Returns the width in pixels based on the given percentage of the screen width.
     *
     * @param perc the percentage of the screen width
     * @return the width in pixels
     */
    public static int getWidth(final double perc) {
    	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    	final int screenWidth = gd.getDisplayMode().getWidth();
        return (int) (screenWidth * perc);
    }

    /**
     * Returns the height in pixels based on the given percentage of the screen height.
     *
     * @param perc the percentage of the screen height
     * @return the height in pixels
     */
    public static int getHeight(final double perc) {
    	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int screenHeight = gd.getDisplayMode().getHeight();
        return (int) (screenHeight * perc);
    }
}
