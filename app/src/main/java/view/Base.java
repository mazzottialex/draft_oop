package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        setTitle("DRAFT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(UtilsGUI.getWidth(POS), UtilsGUI.getHeight(POS), UtilsGUI.getWidth(WIDTH_PANEL),
                UtilsGUI.getHeight(HEIGHT_PANEL));
        setMinimumSize(getSize());
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

    public final JPanel getPanel() {
        JPanel panel = contentPane;
        return panel;
    }

    /**
     * Changes the current JPanel with a new JPanel.
     *
     * @param newPanel the new Base object containing the new JPanel
     */
    protected void changeJPanel(final Base newPanel) {
        JFrame frame = (JFrame) contentPane.getTopLevelAncestor();
        frame.remove(contentPane);
        frame.add(newPanel.getPanel());
        frame.revalidate();
        frame.repaint();
    }
}
