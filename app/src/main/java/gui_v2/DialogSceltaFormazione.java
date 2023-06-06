package gui_v2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.Module;

public class DialogSceltaFormazione extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private Module modulo;
    private Module selezionato;
    private final JButton okButton;
    public DialogSceltaFormazione(final Frame parent, final Boolean modale, final List < Module > liModuli) throws FileNotFoundException, ClassNotFoundException, IOException {
        super(parent, modale);
        setBounds(100, 100, 700, 300);
        setMinimumSize(getSize());
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        List<JButton> liBtn = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JButton btnModulo = new JButton(liModuli.get(i).toString());
            liBtn.add(btnModulo);
            btnModulo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    selezionato = liModuli.get(liBtn.indexOf(btn));
                    liBtn.forEach(el -> el.setBackground(new Color(240, 240, 240)));
                    btn.setBackground(Color.LIGHT_GRAY);
                }
            });
            contentPanel.add(btnModulo);
        }
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(final ActionEvent e) {
	        	modulo = selezionato;
	        	dispose();
	        }
        });
        final JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(final ActionEvent e) {
        		dispose();
        	}
        });  
    }
    public Module getModulo() {
        return this.modulo;
    }
}
