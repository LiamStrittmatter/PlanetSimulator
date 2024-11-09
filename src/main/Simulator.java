public class Simulator extends Thread {
    
    private DataPackage data;
    Planet[] bodies;
    private final double GRAVITATION_CONSTANT = 10;//0.000000000066743;
    
    public Simulator(DataPackage init){
        data = new DataPackage(init);
        initArray();
    }

    public void run() {
        while(true){
            simulate(4000);
            System.out.println("("+bodies[0].getVelocity(0)+", "+bodies[0].getPosition(0)+")");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulate(long steps){
        for(int time=0; time<steps; time++){
            for(int x=0; x<bodies.length; x++)
                updateTelemetry(x);
            step();
        }
    }

    
    private void updateTelemetry(int idx){
        double[] force = {netForce(idx)[0], netForce(idx)[1]};
        bodies[idx].changeVelocity(acceleration(force, bodies[idx].getMass()));
    }

    private void step(){
        for(Planet p: bodies)
            p.changePosition();
    }

    private double resultant(double[] in){
        double sum = 0;
        for(double x: in)
            sum += (x*x);
        return Math.sqrt(sum);
    }

    private double[] acceleration(double[] force, double mass){
        double[] out = new double[force.length];
        for(int dim=0; dim<force.length; dim++)
            out[dim] = force[dim]/mass;
        return out;
    }

    private void initArray(){
        bodies = data.solarSystem;
    }
    
    private double[] netForce(int index){
        int x=0, y=1;
        double[] out = {0.0, 0.0};
        for(int n=0; n<bodies.length; n++){
            if(n!=index){
                out[x] += getForce(index, n, x);
                out[y] += getForce(index, n, y);
            }
        }
        return out;
    }

    private double getForce(int ref, int other, int dir){
        double component = delta(ref, other, dir);
        double hypotenuse = absoluteDistance(ref, other);
        double func = component/hypotenuse;
        return func*forceMag(ref, other);
    }

    private double forceMag(int ref, int other){
        double distance = absoluteDistance(ref, other);
        double denom = distance*distance;
        if(denom<0.01)
            return (GRAVITATION_CONSTANT*bodies[ref].getMass()*bodies[other].getMass())/(0.01);
        return (GRAVITATION_CONSTANT*bodies[ref].getMass()*bodies[other].getMass())/(distance*distance);
    }

    private double absoluteDistance(int ref, int other){
        double x = delta(ref, other, 0);
        double y = delta(ref, other, 1);
        return Math.sqrt(x*x+y*y);
    }

    private double delta(int ref, int other, int dir){
        return (double)(bodies[other].getPosition(dir)-bodies[ref].getPosition(dir));
    }
    
}
