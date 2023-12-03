import javax.swing.*;
import java.awt.*;

public class Task1 extends JFrame {
    private static final int r = 30;
    private int cX = 100;
    private int cY = 100;
    private int dx = 5;
    private int dy = 5;

    public Task1() {
        setTitle("Circle in the frame");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(50, e -> {
            updatePosition();
            repaint();
        });
        timer.start();
    }

    private void updatePosition() {
        cX += dx;
        cY += dy;

        if (cX - r < 0 || cX + r > getWidth()) {
            dx = -dx;
        }
        if (cY - r < 0 || cY + r > getHeight()) {
            dy = -dy;
        }
    }

    @Override
    public void paint(Graphics с) {
        super.paint(с);
        с.setColor(Color.BLUE);
        с.fillOval(cX - r, cY - r, 2 * r, 2 * r);
    }
}
