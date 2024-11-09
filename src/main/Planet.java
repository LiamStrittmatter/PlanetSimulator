import java.awt.*;

public class Planet {

    private Color color;
    private Point location;
    private Dimension size;
    private Point speed;

    private double[] position, velocity, earlyVelo = new double[2];
    private double mass, dt;
    
    
    public Planet(){
        position = new double[2];
        position[0] = 0;
        position[1] = 0;
        velocity = new double[2];
        velocity[0] = 0;
        velocity[1] = 0;
        mass = 1;

        setColor(new Color(random(255), random(255), random(255)));
    }

    private double[] copy(double[] in){
        double[] out = new double[in.length];
        for(int x=0; x<in.length; x++)
            out[x] = in[x];
        return out;
    }

    public Planet(double[] position, double[] velocity, double mass){
        System.out.println("new planet made /s");
        this.position = copy(position);
        this.velocity = copy(velocity);
        this.mass = mass;
        setColor(new Color(random(255), random(255), random(255)));
    }

    public Planet(double[] position, double[] velocity, double mass, Color color){
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        setColor(color);
    }

    public Planet(Planet copy) {

        setColor(copy.getColor());
        this.position = copy.getPosition();
        this.velocity = copy.getVelocity();
        this.mass = copy.getMass();

        speed = new Point(10 - random(20), 10 - random(20));
        size = new Dimension(30, 30);

    }

    public Dimension getSize() {
        return size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public Point getLocation() {
        return location;
    }

    public Point getSpeed() {
        return speed;
    }

    public void setSpeed(Point speed) {
        this.speed = speed;
    }

    protected void paint(Graphics2D g2d) {

        Point p = getLocation();
        if (p != null) {
            g2d.setColor(getColor());
            Dimension size = getSize();
            g2d.fillOval(p.x, p.y, size.width, size.height);
        }

    }





    public double getMass(){
        return mass;
    }
    
    public void changePosition(){
        position[0] += ((earlyVelo[0]+velocity[0])/2)*dt;
        position[1] += ((earlyVelo[1]+velocity[1])/2)*dt;
    }

    public double[] getPosition(){
        double[] out = new double[position.length];
        for(int n=0; n<position.length; n++)
            out[n] = position[n];
        return out;
    }
    
    public double getPosition(int dir){
        return position[dir];
    }

    public void changeVelocity(double[] instantaneousAcceleration){
        earlyVelo[0] = velocity[0];
        velocity[0] += instantaneousAcceleration[0]*dt;
        earlyVelo[1] = velocity[1];
        velocity[1] += instantaneousAcceleration[1]*dt;
    }

    public double[] getVelocity(){
        double[] out = new double[velocity.length];
        for(int n=0; n<velocity.length; n++)
            out[n] = velocity[n];
        return out;
    }
    
    public double getVelocity(int dir){
        return velocity[dir];
    }

    public Planet copy(Planet in){
        return new Planet(position, velocity, mass, color);
    }

    public void setTime(double t){
        dt = t;//*0.999999;
    }




    
    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
}