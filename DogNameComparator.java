//Elis Lundkvist ellu8410
import java.util.Comparator; // Implementera comparator paketet.

public class DogNameComparator implements Comparator <Dog>{ // Klassen som implementerar komparatorn i Dog.
	
	public int compare(Dog firstDog, Dog secondDog) { // Jämför de två firstDog och secondDog från Dog.
		return firstDog.getName().compareToIgnoreCase(secondDog.getName()); // Returnerar ett värde beroende på vad de olika dog har för namn. Sorterar därefter.
	}

}

