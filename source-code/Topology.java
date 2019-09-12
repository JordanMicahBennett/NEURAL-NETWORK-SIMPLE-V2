//Author: Jordan Bennett
import java.util.ArrayList;

public class Topology extends ArrayList <Integer>
{
    private String description;
    
    public Topology ( String description )
    {
        String [ ] items = description.split ( "," );
        
        for ( int iI = 0; iI < items.length; iI ++ )
            add ( Integer.parseInt ( items [ iI ] ) );
    }
}
