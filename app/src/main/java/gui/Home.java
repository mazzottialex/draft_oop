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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * The Home class represents the home screen of the application.
 */
public class Home extends Base {
    private static final long serialVersionUID = 1L;
	private final LogicsHome log;
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 40;
	private static final int SCALED_IMAGE_1 = 160;
	private static final int SCALED_IMAGE_2 = 250;
	/**
     * Constructs a new Home object
     *
     * @param seasonDefault the selected season as a String
     * @param online the online status as a Boolean value
     * @param first true if home is called by Start, False otherwise
     */
    public Home(final String seasonDefault, final Boolean online, final Boolean first) {
    	JButton btnDownload = utilsGUI.standardButton("Download season:");
    	log = new LogicsHomeImpl(seasonDefault, online);
        if (!log.checkBrowser() && first) {
        	btnDownload.setEnabled(false);
        	JOptionPane.showMessageDialog(null, "Google Chrome or Firefox not installed,"
        			+ "or not correctly installed (read README)");
        }
        if (!log.getOnline() && first) {
        	JOptionPane.showMessageDialog(null, "You are in offline mode,"
        			+ " check your connection and restart the application for online mode");
            btnDownload.setEnabled(false);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        getPanel().setLayout(layout);
        JButton btnStart = new JButton();
        ImageIcon img = new ImageIcon("src/main/resources/start.png");
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
                btnStart.setBackground(Color.GREEN);
                Image newimg = image.getScaledInstance(SCALED_IMAGE_1, SCALED_IMAGE_1, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newimg);
                btnStart.setIcon(img);
            }
            public void mouseExited(final java.awt.event.MouseEvent evt) {
                btnStart.setBackground(new Color(0, 64, 128));
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
        gbc.gridwidth=2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(140, 0, 80, 0);
        getPanel().add(btnStart, gbc);
        JPanel panelSelectioned = new JPanel();
        panelSelectioned.setPreferredSize(new Dimension(70, 30));
        JLabel lblSeasonSelected = new JLabel("Season selected:");
        lblSeasonSelected.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        panelSelectioned.add(lblSeasonSelected);
        JLabel lblSeason = new JLabel(log.getSeason());
        lblSeason.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        panelSelectioned.add(lblSeason);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 8, 0);
        getPanel().add(panelSelectioned, gbc);
        JButton btnLoad = utilsGUI.standardButton("Choose a season:");
        String[] array = log.getLiSeasons().toArray(new String[log.getLiSeasons().size()]);
        JComboBox<String> comboBoxLoad = new JComboBox<>(array);
        comboBoxLoad.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.setSeason(comboBoxLoad.getItemAt(comboBoxLoad.getSelectedIndex())); 
                if (log.loadStagione(log.getSeason())) {
                    lblSeason.setText(log.getSeason());
                } else
                    JOptionPane.showMessageDialog(null, "Loading error...");
            }
        });
        JComboBox<String> comboBoxDownload = new JComboBox<>(array);
        gbc.gridwidth=1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        getPanel().add(btnLoad, gbc);
        gbc.gridx = 1;
        getPanel().add(comboBoxLoad, gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        getPanel().add(btnDownload,gbc);
        comboBoxDownload.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Wait a few seconds");
                log.setSeason(comboBoxDownload.getItemAt(comboBoxDownload.getSelectedIndex())); 
                if (log.downloadSeason(log.getSeason())) {
                    lblSeason.setText(log.getSeason());
                    JOptionPane.showMessageDialog(null, "Download completed");
                } else
                    JOptionPane.showMessageDialog(null, "Error loading...");
            }
        });
        gbc.gridx=1;
        gbc.gridy=3;
        getPanel().add(comboBoxDownload,gbc);
        gbc.gridwidth=2;
        JButton btnArchive = utilsGUI.standardButton("Archive");
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
        JButton btnHistory = utilsGUI.standardButton("History");
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new History(log.getSeason(), log.getOnline()));
            }
        });
        gbc.gridy = 5;
        getPanel().add(btnHistory, gbc);
    }
}
