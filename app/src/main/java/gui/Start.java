package gui;
import javax.swing.JLabel;
import logics.LogicStart;
import logics.LogicStartImpl;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The Start class represents the starting point of the application.
 */
public class Start extends Base {
    private static final long serialVersionUID = 1L;
    private final transient LogicStart log = new LogicStartImpl();
    private static final int SCALE_IMG_1 = 120;
    private static final int SCALE_IMG_2 = 180;
    /**
     * Constructs a new Start object.
     * Initializes the season and online status
     * Sets up the GUI components and event listeners
     */
    public Start() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        getPanel().setLayout(gridBagLayout);
        ImageIcon img = new ImageIcon("src/main/resources/icon.png");
        Image image = img.getImage();
        Image newImg = image.getScaledInstance(SCALE_IMG_1, SCALE_IMG_1, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newImg);
        JLabel labelIcon = new JLabel(img);
        labelIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(final MouseEvent e) {
                // TODO Auto-generated method stub
            }
            @Override
            public void mousePressed(final MouseEvent e) {
                // TODO Auto-generated method stub
            }
            @Override
            public void mouseExited(final MouseEvent e) {
                Image newImg = image.getScaledInstance(SCALE_IMG_1, SCALE_IMG_1, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newImg);
                labelIcon.setIcon(img);
            }
            @Override
            public void mouseEntered(final MouseEvent e) {
                Image newImg = image.getScaledInstance(SCALE_IMG_2, SCALE_IMG_2, java.awt.Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(newImg);
                labelIcon.setIcon(img);
            }
            @Override
            public void mouseClicked(final MouseEvent e) {
                changeJPanel(new Home(log.getFirstSeason(), log.getOnline(), true));
            }
        });
        getPanel().add(labelIcon);
    }
}
