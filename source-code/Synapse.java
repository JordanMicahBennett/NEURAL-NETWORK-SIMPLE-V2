//Author: Jordan Bennett

public class Synapse 
{
    //features
    private double weight;
    private double deltaWeight;
    
    public double getWeight ( )
    {
        return weight;
    }
    public double getDeltaWeight ( )
    {
        return deltaWeight;
    }
    public void setDeltaWeight ( double value )
    {
        deltaWeight = value;
    }
    public void setWeight ( double value )
    {
        weight = value;
    }
}
