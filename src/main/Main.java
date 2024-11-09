import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    Planet[] bodies;
    private static final int RESOLUTION_HEIGHT = 1440, RESOLUTION_WIDTH = 3440;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("circular or number? (c/n): ");
        String in = kb.nextLine();
        switch(in){
            case "n":
                System.out.print("Enter number of planets: ");
                DataPackage dp = make(kb.nextInt());
                new Simulator(dp).start();
                new GuiManager(dp);
                break;

            case "c":
                DataPackage dp1 = constructTripleCircle();
                new Simulator(dp1).start();
                new GuiManager(dp1);
                break;

            default:
                System.out.println("lmao");
        }
        kb.close();
    }

    public static DataPackage make(int planets){
        
        Random r = new Random();
        double dt = 0.00001;
        LinkedList<Planet> list = new LinkedList<Planet>();
        double[] pos = new double[2];
        double mass;
        double[] velo = new double[2];

        for(int x=0; x<planets; x++){
            pos[0] = 20*r.nextDouble()-10;
            pos[1] = 10*r.nextDouble()-5;
            mass = r.nextDouble(1,2);
            velo[0] = 2*r.nextDouble()-1;
            velo[1] = 2*r.nextDouble()-1;

            list.add(new Planet(pos, velo, mass));
            
        }

        Planet[] link = new Planet[list.size()];
        for(int x=0; x<list.size() && list.get(x)!=null; x++){
            link[x] = new Planet(list.get(x));
            link[x].setTime(dt);
        }

        return new DataPackage(link, .0000001, new DimensionScale(RESOLUTION_WIDTH, RESOLUTION_HEIGHT));
        
    }

    public static DataPackage constructTripleCircle(){
        double dt = 0.00001;
        double radius = 2;
        double speed = 1.73205080757;
        LinkedList<Planet> list = new LinkedList<Planet>();
        double[][] pos = {
            {0,radius},
            {0.866025403784*radius,-0.5*radius},
            {-0.866025403784*radius,-0.5*radius}
        };
        double[] mass = {1,1,1};
        double[][] velo = {
            {-speed,0},
            {0.5*speed,0.866025403784*speed},
            {0.5*speed, -0.866025403784*speed}
        };

        for(int x=0; x<mass.length; x++)
            list.add(new Planet(pos[x], velo[x], mass[x]));

        Planet[] link = new Planet[list.size()];
        for(int x=0; x<list.size() && list.get(x)!=null; x++){
            link[x] = new Planet(list.get(x));
            link[x].setTime(dt);
        }

        return new DataPackage(link, .0000001, new DimensionScale(RESOLUTION_WIDTH, RESOLUTION_HEIGHT));
    }

}

