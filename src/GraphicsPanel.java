import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class GraphicsPanel extends JPanel implements ActionListener {
    private static int comparisons;
    private Timer timer;
    private ArrayList<Rectangle> rectArray;
    private int numOfRects;
    public GraphicsPanel(String name, int x) {
        if (x == 6) {
            timer = new Timer(30, this);
        }
        else {
            timer = new Timer(50, this);
        }
        timer.start();
        if (x != 6) {
            rectArray = new ArrayList<>();
            numOfRects = x;
            for (int i = 6; i < numOfRects * 6; i += 6) {
                Rectangle rectangle = new Rectangle(10, i);
                rectArray.add(rectangle);
            }
            setFocusable(true); // this line of code + one below makes this panel active for keylistener events
            requestFocusInWindow(); // see comment above
        }
        else {
            rectArray = new ArrayList<>();
            int size = 75;
            for (int i = 0; i < 6; i ++) {
                Rectangle rectangle = new Rectangle(100, size);
                size += 75;
                rectArray.add(rectangle);
            }
            setFocusable(true); // this line of code + one below makes this panel active for keylistener events
            requestFocusInWindow(); // see comment above
        }
        Collections.shuffle(rectArray);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        setBackground(Color.black);
        if (rectArray.size() != 6) {
            int xCoord = 5;
            for (int i = 0; i < rectArray.size(); i++) {
                g.setColor(Color.WHITE);
                g.fillRect(xCoord, (int) (10 + 520 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
                xCoord += 12;
            }
        }
        else {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.RED);
            g.drawString("*DISCLAIMER* Bogo sort is very inefficient so we only use 6 lines to show it", 100, 30);
            g.setFont(new Font ("Arial", Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString("Comparisons: " + comparisons, 70, 50);
            int xCoord1 = 150;
            for (int i = 0; i < rectArray.size(); i++) {
                g.setColor(Color.WHITE);
                g.fillRect(xCoord1, (int) (35 + 500 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
                xCoord1 += 110;
            }
            if (!arraySortedOrNot(rectArray, 6)) {
                int xCoord2 = 150;
                g.setColor(Color.red);
                for (int i = 0; i < rectArray.size(); i++) {
                    comparisons ++;
                    Collections.shuffle(rectArray);
                    g.fillRect(xCoord2, (int) (35 + 500 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
                    g.setColor(Color.WHITE);
                    xCoord2 += 110;
                }
            }
            int xCoord3 = 150;
            for (int i = 0; i < rectArray.size(); i++) {
                g.fillRect(xCoord3, (int) (35 + 500 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
                g.setColor(Color.WHITE);
                xCoord3 += 110;
            }
        }
    }
    // Found on geeksforgeeks to make my life easier, hope its alright
    boolean arraySortedOrNot(ArrayList<Rectangle> a, int n) {
        // base case
        if (n == 1 || n == 0)
            return true;

        return a.get(n - 1).getHeight() >= a.get(n - 2).getHeight() && arraySortedOrNot(a, n - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}