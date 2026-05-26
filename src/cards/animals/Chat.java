package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Chat extends AnimalCard{
    
    public Chat(){
        super(DonneFactory.chat());
    }

    @Override
    public String getName()
    {
        return "Chat";
    }
}
