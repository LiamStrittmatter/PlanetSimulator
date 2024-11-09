import java.awt.*;

import javax.swing.*;

public class GuiManager extends JPanel {
    
    public GuiManager(DataPackage dp){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setLayout(new BorderLayout());
                PlanetPanel panel = new PlanetPanel(dp.solarSystem);
                frame.add(panel);
                frame.setSize(3000, 1000);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
                frame.toFront();

                new Thread(new GuiDriver(panel, dp.dimS)).start();

            }
        });
    }
}