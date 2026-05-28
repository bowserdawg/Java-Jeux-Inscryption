package display.interfaces;

/**
 * Cette interface permet à la classe NewDislay de dessiner l'objet grâce
 * à la méthode NewDisplay.draw.
 * getDimensions doit donner les bonnes dimensions du tableau donne
 * l'objet donné par getDisplayableForm.
*/
public interface Displayable {

    public char[][] getDisplayableForm();
}
