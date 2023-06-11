package view;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.exec.ExecuteException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Color;

/**
 * The Base class, provides a base window for the other GUI.
 */
public class Base extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final Color COLOR_BG = new Color(0, 64, 128);
    private static final double POS = 0.1;
    private static final double WIDTH_PANEL = 0.40;
    private static final double HEIGHT_PANEL = 0.80;
    /**
     * Constructs a new Base object.
     */
    public Base() {
    	super.setTitle("DRAFT");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(UtilsGUI.getWidth(POS), UtilsGUI.getHeight(POS), UtilsGUI.getWidth(WIDTH_PANEL),
                UtilsGUI.getHeight(HEIGHT_PANEL));
        super.setMinimumSize(super.getSize());
        contentPane = new JPanel();
        contentPane.setBackground(COLOR_BG);
        contentPane.setLayout(new BorderLayout());
        add(contentPane);
    }
    /**
     * Returns the content pane of the base window.
     *
     * @return the JPanel content pane
     */
    @SuppressFBWarnings("EI")
    public JPanel getPanel() {
        return contentPane;

    }

    /**
     * Changes the current JPanel with a new JPanel.
     *
     * @param newPanel the new Base object containing the new JPanel
     * @throws ExecuteException
     */
    @SuppressFBWarnings("BC")
    protected void changeJPanel(final Base newPanel) throws ExecuteException {
        if (contentPane.getTopLevelAncestor() instanceof JFrame) {
            JFrame frame = (JFrame) contentPane.getTopLevelAncestor();
            frame.remove(contentPane);
            frame.add(newPanel.getPanel());
            frame.revalidate();
            frame.repaint();
        }
        else {
        	throw new ExecuteException("Content panel not JFrame", ABORT);
        }
    }
}
