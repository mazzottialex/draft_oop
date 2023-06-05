package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * The Base class, provides a base window for the other GUI
 */
public class Base extends JFrame {
    private static final long serialVersionUID = 1L;
    protected JPanel contentPane;
    protected Boolean online;
    protected String season;

    /**
     * Constructs a new Base object
     */
    public Base() {
        setTitle("DRAFT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 700);
        setMinimumSize(getSize());
        setBackground(new Color(0, 64, 128));
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setLayout(new BorderLayout());
        add(contentPane);
    }

    /**
     * Returns the content pane of the base window
     *
     * @return the JPanel content pane
     */
    public JPanel getPanel() {
        return contentPane;
    }

    /**
     * Changes the current JPanel with a new JPanel
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
