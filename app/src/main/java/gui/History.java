package gui;
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
import data.Squadra;
import logics.LogicsHistory;
import logics.LogicsHistoryImpl;
import manageData.LogicsFile;
import manageData.LogicsFileImpl;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
public class History extends Base {
	private LogicsHistory log;
	public History(final String season, final Boolean online) {
		log=new LogicsHistoryImpl(season, online);
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(new BorderLayout());
		JButton btnHome=utilsGUI.standardButton("Home");
		JPanel panelBtn=new JPanel();
		panelBtn.setBackground(getBackground());
		panelBtn.setPreferredSize(new Dimension(50, 50));
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeJPanel(new Home(log.getSeason(), log.getOnline()));
			}
		});
		panelBtn.add(btnHome);
		contentPane.add(panelBtn, BorderLayout.NORTH);
		JPanel panelLi = new JPanel();
		panelLi.setLayout(layout);
		panelLi.setBackground(new Color(0, 64, 128));
		int count=0;
		for (Squadra team : log.getLiTeam()) {
			JPanel panelTeam=new JPanel();
			panelTeam.setBackground(getForeground());
			JLabel lblLogo=new JLabel();
			ImageIcon img=new ImageIcon(team.getStemma());
			Image image = img.getImage(); // transform it 
			Image newImg = image.getScaledInstance(55, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newImg);
			lblLogo.setBackground(Color.white);
			lblLogo.setIcon(img);
			lblLogo.setBorder(new EmptyBorder(new Insets(5, 0, 5, 25)));
			JLabel lblNomeSquadra=new JLabel(team.getNomeSquadra());
			lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
			lblNomeSquadra.setForeground(Color.white);
			panelTeam.add(lblNomeSquadra);
			JLabel lblRating=new JLabel("Valutazione: "+team.getValutazione());
			lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
			lblRating.setForeground(Color.white);
			lblRating.setBorder(new EmptyBorder(new Insets(2, 25, 2, 25)));
			panelTeam.add(lblRating);
			panelTeam.add(lblRating);
			JButton btnVedi=utilsGUI.standardButton("Vedi");
			btnVedi.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					changeJPanel(new SquadraGui(team, log.getSeason(), log.getOnline()));	
				}
			});
			panelTeam.add(btnVedi);
			gbc.gridy=count;
			panelLi.add(panelTeam, gbc);
			count++;
		}
		JScrollPane scrollPane=new JScrollPane(panelLi);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
}
