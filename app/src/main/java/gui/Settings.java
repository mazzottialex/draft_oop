package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import data.Calciatore;
import gui_v2.Formazione;
import logics.LogicsSettings;
import logics.LogicsSettingsImpl;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
public class Settings extends Base {
    private JTextField textFieldTeamName;
    private LogicsSettings log;
    public Settings(List<Calciatore> li) {
    	log=new LogicsSettingsImpl(li);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        JLabel lblTeamName = new JLabel("Team name:");
        lblTeamName.setForeground(Color.white);
        lblTeamName.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 10, 5);
        contentPane.add(lblTeamName, gbc);
        textFieldTeamName = new JTextField();
        textFieldTeamName.setColumns(10);
        textFieldTeamName.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(textFieldTeamName, gbc);
        JLabel lblChooseLogo = new JLabel("Logo:");
        lblChooseLogo.setForeground(Color.white);
        lblChooseLogo.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(layout);
        List<JButton> liButton = new ArrayList<>();
        for (int i = 0; i < log.getLiLogo().size(); i++) {
            JButton btnLogo = new JButton();
            liButton.add(btnLogo);
            String url = log.getLiLogo().get(i);
            ImageIcon img = new ImageIcon(url);
            Image image = img.getImage();
            Image newimg = image.getScaledInstance(65, 70, java.awt.Image.SCALE_SMOOTH); 
            img = new ImageIcon(newimg);
            btnLogo.setBackground(Color.white);
            btnLogo.setIcon(img);
            btnLogo.setBorderPainted(false);
            btnLogo.setFocusPainted(false);
            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            gbc.insets = new Insets(2, 2, 2, 2);
            panelLogo.add(btnLogo, gbc);
            btnLogo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    liButton.forEach(b -> b.setBackground(Color.white));
                    btn.setBackground(new Color(0, 64, 128));
                    log.setLogo(url);
                }
            });
        };
        gbc.insets = new Insets(25, 2, 5, 2);
        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblChooseLogo, gbc);
        gbc.insets = new Insets(5, 2, 5, 2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(panelLogo, gbc);
        JButton btnContinue = utilsGUI.standardButton("Continue");
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldTeamName.getText().equals(new String("")))
                    log.setTeamName(textFieldTeamName.getText());
                try {
					changeJPanel(new CreaSquadra(log.getTeamName(), log.getLogo(), log.getLi()));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(45, 2, 5, 2);
        contentPane.add(btnContinue, gbc);
    }
}
