//Author: Jordan Bennett
public class NeuralNetwork
{
    //features or hyperparameters
    private double eta = 0.2;
    private double alpha = 0.5;
    private Topology topology = new Topology ( "2,2,1" );
    private Layers layers = new Layers ( );
    
    //construction
    public NeuralNetwork ( )
    {
        for ( int tI = 0; tI < topology.size ( ); tI ++ )
        {
            layers.add ( new Layer ( ) );
            
            for ( int lI = 0; lI <= topology.get ( tI ); lI ++ ) //<= for bias neuron
            {
                int numberOfWeightsFromNextNeuron = ( tI + 1 < topology.size ( ) ? topology.get ( tI + 1 ) : 0 );
                
                layers.get ( tI ).add ( new Neuron ( eta, alpha, lI, numberOfWeightsFromNextNeuron ) );
                
                Neuron lastNeuronInEachLayer = layers.get ( tI ).get ( layers.get ( tI ).size ( ) - 1 );
                
                lastNeuronInEachLayer.setOutcome ( 1.0 ); //set bias neuron's outcome to 1. (last neuron per layer)
            }
        }
    }
    
    //do forward propagation
    public void doForwardPropagation ( int [ ] inputs )
    {
        //set first layer item
        for ( int iI = 0; iI < inputs.length; iI ++ )
            layers.get ( 0 ).get ( iI ).setOutcome ( inputs [ iI ] );
        
        //do forward prop based on above, starting from non-zero layer
        for ( int tI = 1; tI < topology.size ( ); tI ++ )
        {
            Layer priorLayer = layers.get ( tI - 1 );
            
            for ( int lI = 0; lI < topology.get ( tI ); lI ++ )
                layers.get ( tI ).get ( lI ).doForwardPropagation ( priorLayer );
        }
    }
    
    
    //do backward propagation
    public void doBackwardPropagation  ( int target )
    {
        //set outcome gradient
        Neuron lastNonBiasNeuronInLastLayer = layers.get ( layers.size ( ) - 1 ).get ( 0 );
        lastNonBiasNeuronInLastLayer.setOutcomeGradient ( target );
        
        //set hidden gradient
        //tI = topology iteration index
        for ( int tI = topology.size ( ) - 2; tI > 0; tI -- )
        {
            Layer currentLayer = layers.get ( tI );
            Layer nextLayer = layers.get ( tI + 1 );
            
            //lI = layer iteration index
            for ( int lI = 0; lI < currentLayer.size ( ) - 1; lI ++ )
                currentLayer.get ( lI ).setHiddenGradient ( nextLayer );
        }
        
        //update weights
        //tI = topology iteration index
        for ( int tI = topology.size ( ) - 1; tI > 0; tI -- )
        {
            Layer currentLayer = layers.get ( tI );
            Layer priorLayer = layers.get ( tI - 1 );
            
            //lI = layer iteration index
            for ( int lI = 0; lI < currentLayer.size ( ) - 1; lI ++ )
                currentLayer.get ( lI ).updateWeights ( priorLayer );
        }
    }
    
    //get outcome
    public double getOutcome ( )
    {
        Neuron lastNonBiasNeuronInLastLayer = layers.get ( layers.size ( ) - 1 ).get ( 0 );
        
        return lastNonBiasNeuronInLastLayer.getOutcome ( );
    }
}
