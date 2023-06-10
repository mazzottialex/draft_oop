package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.LogicsSettings;
import controller.LogicsSettingsImpl;
import model.data.Player;

/**
 * Represents a GUI for Settings.
 */
public class Settings extends Base {
    private static final long serialVersionUID = 1L;
    private final JTextField textFieldTeamName;
    private final transient LogicsSettings log;
    private static final Insets INSETS_1 = new Insets(10, 5, 10, 5);
    private static final Insets INSETS_2 = new Insets(2, 2, 2, 2);
    private static final Insets INSETS_3 = new Insets(25, 2, 5, 2);
    private static final Insets INSETS_4 = new Insets(5, 2, 5, 2);
    private static final Insets INSETS_5 = new Insets(45, 2, 5, 2);
    private static final int SCALE_IMG_1 = 65;
    private static final Color COLOR_BG = new Color(0, 64, 128);

    /**
     * Constructs a new Settings object.
     * 
     * @param li list of players
     */
    public Settings(final List<Player> li) {
        log = new LogicsSettingsImpl(li);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        getPanel().setLayout(layout);
        JLabel lblTeamName = new JLabel("Team name:");
        lblTeamName.setForeground(Color.white);
        lblTeamName.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = INSETS_1;
        getPanel().add(lblTeamName, gbc);
        textFieldTeamName = new JTextField();
        textFieldTeamName.setColumns(10);
        textFieldTeamName.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        getPanel().add(textFieldTeamName, gbc);
        JLabel lblChooseLogo = new JLabel("Logo:");
        lblChooseLogo.setForeground(Color.white);
        lblChooseLogo.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(layout);
        List<JButton> liButton = new ArrayList<>();
        for (int i = 0; i < log.getLiLogo().size(); i++) {
            JButton btnLogo = new JButton();
            liButton.add(btnLogo);
            String url = log.getLiLogo().get(i);
            ImageIcon img = new ImageIcon(url);
            Image image = img.getImage();
            Image newimg = image.getScaledInstance(SCALE_IMG_1, SCALE_IMG_1, java.awt.Image.SCALE_SMOOTH);
            img = new ImageIcon(newimg);
            btnLogo.setBackground(Color.white);
            btnLogo.setIcon(img);
            btnLogo.setBorderPainted(false);
            btnLogo.setFocusPainted(false);
            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            gbc.insets = INSETS_2;
            panelLogo.add(btnLogo, gbc);
            btnLogo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    liButton.forEach(b -> b.setBackground(Color.white));
                    btn.setBackground(COLOR_BG);
                    log.setLogo(url);
                }
            });
        }
        gbc.insets = INSETS_3;
        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        getPanel().add(lblChooseLogo, gbc);
        gbc.insets = INSETS_4;
        gbc.gridx = 0;
        gbc.gridy = 2;
        getPanel().add(panelLogo, gbc);
        JButton btnContinue = UtilsGUI.standardButton("Continue");
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!textFieldTeamName.getText().equals("")) {
                    log.setTeamName(textFieldTeamName.getText());
                }
                try {
                    changeJPanel(new CreateTeam(log.getTeamName(), log.getLogo(), log.getLi()));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = INSETS_5;
        getPanel().add(btnContinue, gbc);
    }
}
