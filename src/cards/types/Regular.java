package cards.types;

public class Regular implements Typable{


    @Override
    public String getType() {
        return "Regular";
    }

    @Override
    public boolean isNotRegular() {
        return false;
    }
}
