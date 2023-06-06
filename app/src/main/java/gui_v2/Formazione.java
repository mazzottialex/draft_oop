package gui_v2;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import data.Player;
import data.Module;
import gui.Base;
import gui.utilsGUI;
import logics.LogicsFormazione;
import logics.LogicsFormazioneImpl;
public class Formazione extends Base {
    private List<Module> liModuli;
    private Module modulo;
    private LogicsFormazione log;
    private final static int N_MODULI = 5;
    public Formazione(final List<Player> li, final String nomeSquadra, final String stemma) {
        log = new LogicsFormazioneImpl();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        JButton btnScegli = utilsGUI.standardButton("Scegli Modulo");
        liModuli = log.getRandomMod(N_MODULI);
        btnScegli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                JPanel panel = (JPanel) btn.getParent();
                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
                try {
                    DialogSceltaFormazione dialog = new DialogSceltaFormazione(parent, true, liModuli);
                    dialog.setVisible(true);
                    modulo = dialog.getModulo();
                    if (modulo != null) {
                        panel.remove(btn);
                        JLabel lblModulo = new JLabel("Modulo Scelto:" + modulo.toString());
                        lblModulo.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
                        lblModulo.setForeground(Color.white);
                        gbc.gridy = 0;
                        gbc.insets = new Insets(10, 0, 10, 0);
                        panel.add(lblModulo, gbc);
                        JButton btnProsegui = utilsGUI.standardButton("Prosegui");
                        btnProsegui.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(final ActionEvent e) {
                                try {
									changeJPanel(new Draft(li, modulo, nomeSquadra, stemma));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
                            }
                        });
                        gbc.gridy = 1;
                        panel.add(btnProsegui, gbc);
                    }
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        contentPane.add(btnScegli);
    }
}
