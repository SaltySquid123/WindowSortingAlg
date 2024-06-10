import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel implements ActionListener{
    private JButton sort1;
    private JButton sort2;
    private JButton sort4;
    private JFrame enclosingFrame;
    public WelcomePanel(JFrame frame){
        //Builds a welcome frame containing 4 buttons with sorting methods, have to update the name of the sorting methods
        enclosingFrame = frame;
        sort1 = new JButton("Ermm");
        sort2 = new JButton("Selection");
        sort4 = new JButton("Bogo");
        add(sort1);
        add(sort2);
        add(sort4);
        sort1.addActionListener(this);
        sort1.setFocusable(false);

        sort2.addActionListener(this);
        sort2.setFocusable(false);

        sort4.addActionListener(this);
        sort4.setFocusable(false);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.red);
        g.drawString("Choose a sorting method: ", 38, 60);
        sort1.setLocation(50, 150);
        sort2.setLocation(145, 150);
        sort4.setLocation(260, 150);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == sort1){
                MainFrame f = new MainFrame(79, 1);
                enclosingFrame.setVisible(false);
            }
            else if (button == sort2){
                MainFrame f = new MainFrame(79, 2);
                enclosingFrame.setVisible(false);
            }
            else {
                MainFrame f = new MainFrame(6, 4);
                enclosingFrame.setVisible(false);
            }
        }
    }
}