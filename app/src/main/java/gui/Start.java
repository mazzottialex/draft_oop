package gui;
import java.awt.EventQueue;
import javax.swing.JLabel;
import logics.LogicStart;
import logics.LogicStartImpl;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Start extends Base {
    private final LogicStart log = new LogicStartImpl();
    private final String stagioneDefault = "2022-2023";
    public static void main(String[] args)
    		throws FileNotFoundException, IOException, ClassNotFoundException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Start frame = new Start();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Start() {
        stagione = stagioneDefault;
        online = log.getOnline();
        GridBagLayout gridBagLayout = new GridBagLayout();
        contentPane.setLayout(gridBagLayout);
        ImageIcon img = new ImageIcon("src/main/resources/icon.png");
        Image image = img.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newimg);
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
                Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                ImageIcon img = new ImageIcon(newimg);
                labelIcon.setIcon(img);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Image newimg = image.getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                ImageIcon img = new ImageIcon(newimg);
                labelIcon.setIcon(img);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                changeJPanel(new Home(stagione, online));
            }
        });
        contentPane.add(labelIcon);
    }
}
