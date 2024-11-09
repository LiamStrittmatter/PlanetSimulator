import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetPanel extends JPanel {

    private List<Planet> ballsUp;
    private Planet[] system;

    public PlanetPanel() {
        ballsUp = new ArrayList<Planet>(25);
        Planet ex = new Planet();

        for (int index = 0; index < 20 + random(90); index++) {
            ballsUp.add(new Planet(ex));
        }
    }

    public PlanetPanel(Planet[] in){
        system = in;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Planet ball : system) {
            ball.paint(g2d);
        }
        g2d.dispose();
    }

    public Planet[] getBalls() {
        return system;
    }
    
    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
}