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
 * The Start class represents the starting point of the application
 */
public class Start extends Base {
    private static final long serialVersionUID = 1L;
    private final LogicStart log = new LogicStartImpl();

    /**
     * Constructs a new Start object
     * Initializes the season and online status
     * Sets up the GUI components and event listeners
     */
    public Start() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        contentPane.setLayout(gridBagLayout);
        ImageIcon img = new ImageIcon("src/main/resources/icon.png");
        Image image = img.getImage(); 
        Image newImg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newImg);
        JLabel labelIcon = new JLabel(img);
        labelIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Image newImg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                ImageIcon img = new ImageIcon(newImg);
                labelIcon.setIcon(img);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                Image newImg = image.getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                ImageIcon img = new ImageIcon(newImg);
                labelIcon.setIcon(img);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                changeJPanel(new Home(log.getFirstSeason(), log.getOnline(), true));
            }
        });
        contentPane.add(labelIcon);
    }
}
