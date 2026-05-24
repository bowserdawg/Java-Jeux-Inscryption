package cards.types;

public class Volant implements Typable{

    @Override
    public String getType() {
        return "Volant";
    }

    @Override
    public boolean isNotRegular() {
        return true;
    }
}
