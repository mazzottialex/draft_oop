package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.Player;
import data.Team;
import logics.LogicsHistory;
import logics.LogicsHistoryImpl;
public class ViewTeam extends Base {
    private final List<String> pos = List.of("A", "C", "D", "P");
    private final LogicsHistory log;
    private JPanel panelTeam = new JPanel();
    public ViewTeam(Team team, String season, Boolean online) {
    	log=new LogicsHistoryImpl(season, online);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 2, 2);
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        panelTeam.setBackground(getForeground());
        JButton btnArchive = utilsGUI.standardButton("Archive");
        btnArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new History(log.getSeason(), log.getOnline()));
            }
        });

        panelTeam.add(btnArchive);
        JLabel lblLogo = new JLabel();
        ImageIcon img = new ImageIcon(team.getLogo());
        Image image = img.getImage(); // transform it 
        Image newimg = image.getScaledInstance(55, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newimg);
        lblLogo.setBackground(Color.white);
        lblLogo.setIcon(img);
        lblLogo.setBorder(new EmptyBorder(new Insets(2, 0, 2, 25)));
        panelTeam.add(lblLogo);
        JLabel lblTeamName = new JLabel(team.getTeamName());
        lblTeamName.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        lblTeamName.setForeground(Color.white);
        panelTeam.add(lblTeamName);
        JLabel lblRating = new JLabel("Valutazione: " + team.getRating());
        lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        lblRating.setForeground(Color.white);
        lblRating.setBorder(new EmptyBorder(new Insets(2, 25, 2, 0)));
        panelTeam.add(lblRating);
        gbc.gridy = 0;
        contentPane.add(panelTeam, gbc);
        JPanel panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        int count = 0;
        for (int i = 0; i < pos.size(); i++) {
            panelPosition = new JPanel();
            for (int j = 0; j < team.getModulo().getN(pos.get(i)); j++) {
                Player p = team.getStartingDesc().get(count);
                JPanel panel = (utilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), true));
                count++;
                panelPosition.add(panel);
            }
            gbc.gridy = i + 1;
            contentPane.add(panelPosition, gbc);
        }
        JLabel lblPanchina = new JLabel("PANCHINA");
        lblPanchina.setForeground(Color.white);
        lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy = 5;
        //contentPane.add(lblPanchina, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);
        //panchinari
        panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        for (int j = 0; j < 7; j++) {
            Player p = team.getSubstitution().get(j);
            if (j < 4)
                gbc.gridy = 0;
            else
                gbc.gridy = 1;
            panelPosition.add(utilsGUI.getPanelCalciatore(p.getName(),
            		p.getRating().getX(), p.getPos(), false), gbc);
        }
        gbc.gridy = 6;
        contentPane.add(panelPosition, gbc);
    }
}
