import javax.swing.SwingUtilities;
import java.awt.*;

public class GuiDriver implements Runnable {
    private DimensionScale d;
    private PlanetPanel parent;

    public GuiDriver(PlanetPanel parent, DimensionScale dS) {
        this.parent = parent;
        d = dS;
    }

    @Override
    public void run() {

        for (Planet ball : getParent().getBalls()) {
            int[] coords = d.getCoordFromPosition(ball.getPosition());
            ball.setLocation(new Point(coords[0], coords[1]));
        }

        while (getParent().isVisible()) {

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getParent().repaint();
                }
            });

            for (Planet ball : getParent().getBalls()) {
                move(ball);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex){}

        }

    }

    public PlanetPanel getParent() {
        return parent;
    }

    public void move(Planet body) {
        int[] coords = d.getCoordFromPosition(body.getPosition());
        body.setLocation(new Point(coords[0], coords[1]));
    }

    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
}