import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GraphicsPanel extends JPanel implements ActionListener {
    private static int comparisons;
    private Timer timer;
    private ArrayList<Rectangle> rectArray;
    private int numOfRects;
    private int importantInt;
    private int selectionSortI;
    private int colorIdx;
    private JFrame enclosingFrame;

    public GraphicsPanel(JFrame frame, int x, int y) {
        importantInt = y;
        colorIdx = 0;
        enclosingFrame = frame;
        if (importantInt == 1) {
            timer = new Timer(1, this);
            selectionSortI = 0;
        } else if (importantInt == 2) {
            timer = new Timer(150, this);
            selectionSortI = 0;
        } else {
            timer = new Timer(0, this);
        }
        if (importantInt != 4) {
            rectArray = new ArrayList<>();
            numOfRects = x;
            for (int i = 6; i < numOfRects * 6; i += 6) {
                Rectangle rectangle = new Rectangle(10, i);
                rectArray.add(rectangle);
            }
            setFocusable(true); // this line of code + one below makes this panel active for keylistener events
            requestFocusInWindow(); // see comment above
        } else {
            rectArray = new ArrayList<>();
            int size = 75;
            for (int i = 0; i < 6; i++) {
                Rectangle rectangle = new Rectangle(100, size);
                size += 75;
                rectArray.add(rectangle);
            }
            setFocusable(true); // this line of code + one below makes this panel active for keylistener events
            requestFocusInWindow(); // see comment above
        }
        Collections.shuffle(rectArray);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        setBackground(Color.black);
        if (importantInt == 1) {
            paintSmall(g);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString("Comparisons: " + comparisons, 70, 50);
        } else if (importantInt == 2) {
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString("Comparisons: " + comparisons, 70, 50);
            paintSmall(g);
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.RED);
            g.drawString("*DISCLAIMER* Bogo Sort is very inefficient so we only use 6 lines to show it", 100, 30);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString("Comparisons: " + comparisons, 70, 50);
            paintBig(g);
            if (!arraySortedOrNot(rectArray, 6)) {
                int xCoord2 = 150;
                g.setColor(Color.red);
                for (int i = 0; i < rectArray.size(); i++) {
                    comparisons++;
                    Collections.shuffle(rectArray);
                    g.fillRect(xCoord2, (int) (35 + 500 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
                    g.setColor(Color.WHITE);
                    xCoord2 += 110;
                }
            }
        }
    }

    // Found on geeksforgeeks to make my life easier, hope its alright
    boolean arraySortedOrNot(ArrayList<Rectangle> a, int n) {
        // base case
        if (n == 1 || n == 0) {
            return true;
        }
        return a.get(n - 1).getHeight() >= a.get(n - 2).getHeight() && arraySortedOrNot(a, n - 1);
    }
    boolean isSorted(ArrayList<Rectangle> a){
        for (int i = 0; i < a.size() - 1; i++){
            if (a.get(i).getHeight() > a.get(i + 1).getHeight()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (importantInt == 1) {
            if (selectionSortI < rectArray.size()) {
                colorIdx = selectionSortI;
                if (rectArray.get(selectionSortI + 1).getHeight() < rectArray.get(selectionSortI).getHeight()) {
                    Rectangle min = rectArray.get(selectionSortI + 1);
                    rectArray.set(selectionSortI + 1, rectArray.get(selectionSortI));
                    rectArray.set(selectionSortI, min);
                    comparisons++;
                }
            }
            selectionSortI++;
            if (selectionSortI >= rectArray.size() - 1) {
                selectionSortI = 0;
            }
        } else if (importantInt == 2) {
            if (selectionSortI < rectArray.size()) {
                Rectangle min = rectArray.get(selectionSortI);
                int minIdx = selectionSortI;
                colorIdx = selectionSortI;
                for (int j = selectionSortI; j < rectArray.size(); j++) {
                    if (rectArray.get(j).getHeight() < min.getHeight()) {
                        minIdx = j;
                        min = rectArray.get(j);
                        comparisons++;
                    }
                }
                rectArray.set(minIdx, rectArray.get(selectionSortI));
                rectArray.set(selectionSortI, min);
            }
            selectionSortI++;
        }
    }
    private void paintSmall(Graphics g) {
        int xCoord = 5;
        for (int i = 0; i < rectArray.size(); i++) {
            if (i == colorIdx) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(xCoord, (int) (10 + 520 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
            g.setColor(Color.WHITE);
            xCoord += 12;
        }
    }

    private void paintBig(Graphics g) {
        int xCoord1 = 150;
        for (int i = 0; i < rectArray.size(); i++) {
            g.fillRect(xCoord1, (int) (35 + 500 - rectArray.get(i).getHeight()), (int) rectArray.get(i).getWidth(), (int) rectArray.get(i).getHeight());
            xCoord1 += 110;
        }
    }
}