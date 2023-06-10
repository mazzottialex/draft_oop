package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import logics.LogicsHome;
import logics.LogicsHomeImpl;

/**
 * The Home class represents the home screen of the application.
 */
public class Home extends Base {
    private static final long serialVersionUID = 1L;
    private final transient LogicsHome log;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 40;
    private static final int SCALED_IMAGE_1 = 160;
    private static final int SCALED_IMAGE_2 = 250;
    private static final Insets BUTTON_INSETS = new Insets(140, 0, 80, 0);
    private static final Insets PANEL_INSETS = new Insets(8, 0, 8, 0);
    private static final int PANEL_WIDTH = 70;
    private static final int PANEL_HEIGHT = 30;
    private static final int FONT_SIZE = 14;
    private static final URL START_IMAGE_PATH = ClassLoader.getSystemResource("start.png");
    private static final Color BACKGROUND_COLOR = new Color(0, 64, 128);
    private static final Color BACKGROUND_COLOR_2 = Color.GREEN;
    private static final int GRID_Y = 5;

    /**
     * Constructs a new Home object.
     *
     * @param seasonDefault the selected season as a String
     * @param online        the online status as a Boolean value
     * @param first         true if home is called by Start, False otherwise
     */
    public Home(final String seasonDefault, final Boolean online, final Boolean first) {
        JButton btnDownload = UtilsGUI.standardButton("Download season:");
        log = new LogicsHomeImpl(seasonDefault, online);
        if (!log.checkBrowser() && first) {
            btnDownload.setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "Google Chrome or Firefox not installed, or not correctly installed (read README)");
        }
        if (!log.getOnline() && first) {
            JOptionPane.showMessageDialog(null,
                    "You are in offline mode, check your connection and restart the application for online mode");
            btnDownload.setEnabled(false);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        getPanel().setLayout(layout);
        JButton btnStart = new JButton();
        ImageIcon img = new ImageIcon(START_IMAGE_PATH);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(SCALED_IMAGE_2, SCALED_IMAGE_2, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);
        btnStart.setIcon(img);
        btnStart.setBackground(getForeground());
        btnStart.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                btnStart.setBackground(BACKGROUND_COLOR_2);
                Image newimg = image.getScaledInstance(SCALED_IMAGE_1, SCALED_IMAGE_1, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newimg);
                btnStart.setIcon(img);
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                btnStart.setBackground(BACKGROUND_COLOR);
                Image newimg = image.getScaledInstance(SCALED_IMAGE_2, SCALED_IMAGE_2, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newimg);
                btnStart.setIcon(img);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    changeJPanel(new Settings(log.getLi()));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = BUTTON_INSETS;
        getPanel().add(btnStart, gbc);
        JPanel panelSelectioned = new JPanel();
        panelSelectioned.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JLabel lblSeasonSelected = new JLabel("Season selected:");
        lblSeasonSelected.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_SIZE));
        panelSelectioned.add(lblSeasonSelected);
        JLabel lblSeason = new JLabel(log.getSeason());
        lblSeason.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_SIZE));
        panelSelectioned.add(lblSeason);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = PANEL_INSETS;
        getPanel().add(panelSelectioned, gbc);
        JButton btnLoad = UtilsGUI.standardButton("Choose a season:");
        String[] array = log.getLiSeasons().toArray(new String[log.getLiSeasons().size()]);
        JComboBox<String> comboBoxLoad = new JComboBox<>(array);
        comboBoxLoad.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_SIZE));
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                log.setSeason(comboBoxLoad.getItemAt(comboBoxLoad.getSelectedIndex()));
                if (log.loadStagione(log.getSeason())) {
                    lblSeason.setText(log.getSeason());
                } else {
                    JOptionPane.showMessageDialog(null, "Loading error...");
                }
            }
        });
        JComboBox<String> comboBoxDownload = new JComboBox<>(array);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        getPanel().add(btnLoad, gbc);
        gbc.gridx = 1;
        getPanel().add(comboBoxLoad, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        getPanel().add(btnDownload, gbc);
        comboBoxDownload.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_SIZE));
        btnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btnDownload);
                DialogNThread dialog = new DialogNThread(parent);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                int nThread = dialog.getNThread();
                if (nThread > 0) {
                    JOptionPane.showMessageDialog(null, "Wait a few seconds");
                    log.setSeason(comboBoxDownload.getItemAt(comboBoxDownload.getSelectedIndex()));
                    if (log.downloadSeason(log.getSeason(), nThread)) {
                        lblSeason.setText(log.getSeason());
                        JOptionPane.showMessageDialog(null, "Download completed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error loading... decrease number of thread");
                    }
    			}
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        getPanel().add(comboBoxDownload, gbc);
        gbc.gridwidth = 2;
        JButton btnArchive = UtilsGUI.standardButton("Archive");
        btnArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    changeJPanel(new Archive(log.getLi(), log.getSeason(), log.getOnline()));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 4;
        getPanel().add(btnArchive, gbc);
        JButton btnHistory = UtilsGUI.standardButton("History");
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new History(log.getSeason(), log.getOnline()));
            }
        });
        gbc.gridy = GRID_Y;
        getPanel().add(btnHistory, gbc);
    }
}
