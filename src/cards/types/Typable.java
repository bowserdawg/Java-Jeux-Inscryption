package cards.types;

public interface Typable {

    /**
     * Donne une representation en texte du nom de la classe.
     * @return Un String avec le nom de la classe.
     */
    public String getType();

    /**
     * Pour savoir si la classe n'est pas de type Regular.
     * @return Un boolean indiquant si la classe est Regular ou non.
     */
    public boolean isNotRegular();
}
