
//Elis Lundkvist ellu8410
import java.util.Comparator; //importera Comparatorn.

public class DogTailNameComparator implements Comparator<Dog> { // klassen som ska finnas.Gäller för listan Dog.

	public int compare(Dog dogA, Dog dogB) { // ange vad som ska jämföras i metoden.
		if (dogA.getTailLength() > dogB.getTailLength()) { // if-sats som jämför svanslängderna.
			return 1; // dogA hamnar efter i sorteringen eftersom den har längre svans.
		} else if (dogA.getTailLength() < dogB.getTailLength()) { // liknande sats men med annat villkor.
			return -1; // dogA hamnar innan i sorteringen eftersom den har kortare svans.
		}
		return dogA.getName().compareTo(dogB.getName()); // returnerar ett heltal i sorteringen beroende på vilket namn
															// som kommer först i ordningen, vilket möjliggör
															// efterfrågade tabellen.
	}
}