public class DimensionScale {
    public double[] oldScale = {15.0, 10.0};
    private int[] newScale = {3440/2, 1440/2};
    
    public DimensionScale(){

    }

    public DimensionScale(int width, int height){
        newScale[0] = width/2;
        newScale[1] = height/2;
    }

    public DimensionScale(double[] old, int[] nw){
        oldScale = old;
        newScale = nw;
    }

    public int[] getCoordFromPosition(double[] pos){
        int[] out = new int[pos.length];
        out[0] = (int)((pos[0]/oldScale[0])*newScale[0])+newScale[0];
        out[1] = -(int)((pos[1]/oldScale[1])*newScale[1])+newScale[1];
        return out;
    }
}
