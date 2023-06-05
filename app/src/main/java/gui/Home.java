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
import javax.swing.SwingConstants;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * The Home class represents the home screen of the application
 */
public class Home extends Base {
    private static final long serialVersionUID = 1L;
	private final LogicsHome log;
	
	/**
     * Constructs a new Home object
     *
     * @param season_ the selected season as a String
     * @param online    the online status as a Boolean value
     */
    public Home(final String season_, final Boolean online) {
        this.season = season_;
        this.online = online;
        log = new LogicsHomeImpl(season, online);
        log.loadStagione(season);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        JButton btnStart = new JButton();
        ImageIcon img = new ImageIcon("src/main/resources/start.png");
        Image image = img.getImage(); 
        Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);
        btnStart.setIcon(img);
        btnStart.setBackground(getForeground());
        btnStart.setPreferredSize(new Dimension(150, 40));
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStart.setBackground(Color.GREEN);
                Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newimg);
                btnStart.setIcon(img);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStart.setBackground(new Color(0, 64, 128));
                Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newimg);
                btnStart.setIcon(img);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    changeJPanel(new Impostazioni(log.getLi()));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(140, 0, 80, 0);
        contentPane.add(btnStart, gbc);
        JPanel panelSelectioned = new JPanel();
        panelSelectioned.setPreferredSize(new Dimension(70, 40));
        JLabel lblSeasonSelected = new JLabel("Season selected:");
        lblSeasonSelected.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        panelSelectioned.add(lblSeasonSelected);
        JLabel lblSeason = new JLabel(season);
        lblSeason.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        panelSelectioned.add(lblSeason);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 8, 0);
        contentPane.add(panelSelectioned, gbc);
        JPanel panelLoad = new JPanel();
        panelLoad.setBackground(new Color(240, 240, 240));
        panelLoad.setLayout(new BoxLayout(panelLoad, BoxLayout.X_AXIS));
        JButton btnLoad = new JButton("Choose a season:");
        btnLoad.setHorizontalAlignment(SwingConstants.RIGHT);
        btnLoad.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnLoad.setBorderPainted(false);
        btnLoad.setBackground(getForeground());
        btnLoad.setRolloverEnabled(true);
        btnLoad.setFocusPainted(false);
        String[] array = log.getSeason().toArray(new String[log.getSeason().size()]);
        JComboBox<String> comboBoxLoad = new JComboBox<>(array);
        comboBoxLoad.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                season = comboBoxLoad.getItemAt(comboBoxLoad.getSelectedIndex());
                if (log.loadStagione(season)) {
                    lblSeason.setText(season);
                } else
                    JOptionPane.showMessageDialog(null, "Loading error...");
            }
        });
        panelLoad.add(btnLoad);
        panelLoad.add(comboBoxLoad);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(panelLoad, gbc);
        JPanel panelDownLoad = new JPanel();
        panelDownLoad.setBackground(new Color(240, 240, 240));
        panelDownLoad.setLayout(new BoxLayout(panelDownLoad, BoxLayout.X_AXIS));
        JButton btnDownload = new JButton("Download season");
        JLabel labelWarning = new JLabel("");
        JComboBox<String> comboBoxDownload = new JComboBox<>(array);
        if (!online) {
            btnDownload.setEnabled(false);
            comboBoxDownload.setEnabled(false);
            labelWarning = new JLabel("Offline mode");
            labelWarning.setForeground(Color.yellow);
            labelWarning.setFont(new Font("DejaVu Sans", Font.ITALIC, 12));
        }
        btnDownload.setHorizontalAlignment(SwingConstants.RIGHT);
        btnDownload.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnDownload.setBorderPainted(false);
        btnDownload.setBackground(getForeground());
        btnDownload.setRolloverEnabled(true);
        btnDownload.setFocusPainted(false);
        panelDownLoad.add(btnDownload);
        comboBoxDownload.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Wait a few seconds");
                season = comboBoxDownload.getItemAt(comboBoxDownload.getSelectedIndex());
                if (log.downloadSeason(season)) {
                    lblSeason.setText(season);
                    JOptionPane.showMessageDialog(null, "Download completed");
                } else
                    JOptionPane.showMessageDialog(null, "Errore nel caricamento");
            }
        });
        panelDownLoad.add(comboBoxDownload);
        gbc.gridx = 0;
        gbc.gridy = 3;
        //gbc.anchor=100;
        contentPane.add(panelDownLoad, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(labelWarning, gbc);
        JButton btnArchivio = utilsGUI.standardButton("Archivio");
        btnArchivio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changeJPanel(new Archivio(log.getLi(), season, online));
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(btnArchivio, gbc);
        JButton btnStorico = utilsGUI.standardButton("Storico");
        btnStorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeJPanel(new Storico(season, online));
            }
        });
        gbc.gridy = 6;
        contentPane.add(btnStorico, gbc);
    }
}