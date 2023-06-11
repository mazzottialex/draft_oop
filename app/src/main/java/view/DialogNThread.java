package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A dialog for selecting the number of threads for scraping.
 */
public class DialogNThread extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private static final int FONT_DIM = 14;
    private static final int PANEL_X = 200;
    private static final int PANEL_Y = 200;
    private static final int PANEL_WIDTH = 350;
    private static final int PANEL_HEIGHT = 150;
    private static final Integer[] ARRAY = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

    private int nThread = 0;

    /**
     * Creates the dialog.
     * 
     * @param parent The parent frame of the dialog.
     */
    public DialogNThread(final Frame parent) {
        super(parent, true);
        setBounds(PANEL_X, PANEL_Y, PANEL_WIDTH, PANEL_HEIGHT);
        super.getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        final JComboBox<Integer> comboBox = new JComboBox<>(ARRAY);
        final JLabel labelThread = new JLabel("Choose thread number for scraping: ");
        labelThread.setFont(new Font("DejaVu Sans", Font.PLAIN, FONT_DIM));
        comboBox.setFont(new Font("DejaVu Sans", Font.PLAIN, FONT_DIM));
        contentPanel.add(labelThread);
        contentPanel.add(comboBox);
        super.getContentPane().add(contentPanel, BorderLayout.CENTER);
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        super.getContentPane().add(buttonPane, BorderLayout.SOUTH);
        final JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                nThread = comboBox.getItemAt(comboBox.getSelectedIndex());
                dispose();
            }
        });
        buttonPane.add(okButton);
        super.getRootPane().setDefaultButton(okButton);
        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(final ActionEvent e) {
                dispose();
           }
        });
        buttonPane.add(cancelButton);
    }
    /**
     * Returns the selected number of threads.
     * 
     * @return The selected number of threads.
     */
    public int getNThread() {
        return this.nThread;
    }
}
