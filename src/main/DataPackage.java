public class DataPackage {
    Planet[] solarSystem;
    double dt;
    long framesToCompute;
    DimensionScale dimS;

    public DataPackage(Planet[] solarSystem, double interval, DimensionScale dim){
        
        this.solarSystem = new Planet[solarSystem.length];
        for(int x=0; x<solarSystem.length; x++)
            this.solarSystem[x] = solarSystem[x];

        dt = interval;

        dimS = dim;
    }
    
    public DataPackage(DataPackage copy){
        this(copy.solarSystem, copy.dt, copy.dimS);
    }

    public DataPackage copy(){
        return new DataPackage(solarSystem, dt, dimS);
    }
}
