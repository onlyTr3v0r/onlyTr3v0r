import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import java.awt.Graphics2D.*;

public class shapeDrawer extends Frame {
    public static void main(String[] args) {
        new shapeDrawer();
    }

    public shapeDrawer() {
        super("shapeDrawer");

        setLocationRelativeTo(null);
        setSize(700, 300);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        int[] xArr = {100, 200, 150};
        int[] yArr = {200, 100, 150};
        Polygon polygon = new Polygon(xArr, yArr, 3);
        g2d.draw(polygon);
    }
}
