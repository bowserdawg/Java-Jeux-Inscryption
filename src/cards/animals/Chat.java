package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Chat extends AnimalCard{
    
    public Chat(){
        super(DonneFactory.chat());
        addPouvoir(new cards.pouvoir.NombreusesVies());
    }

    @Override
    public String getName()
    {
        return "Chat";
    }
}
