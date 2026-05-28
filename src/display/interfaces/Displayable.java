package display.interfaces;

/**
 * Cette interface permet à la classe NewDislay de dessiner l'objet grâce
 * à la méthode NewDisplay.draw.
 * getDisplayableFormArray et getDisplayableFormString doivent renvoyer le meme resultat
 * pour que l'interface fonctionne normallement !
*/
public interface Displayable {

    public char[][] getDisplayableFormArray();
}
