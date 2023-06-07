package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

/**
 * The Base class, provides a base window for the other GUI
 */
public class Base extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 700;
    private static final Color COLOR_BG = new Color(0, 64, 128);
    /**
     * Constructs a new Base object.
     */
    public Base() {
        setTitle("DRAFT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, WIDTH, HEIGHT);
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
    public JPanel getPanel() {
        return contentPane;
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
