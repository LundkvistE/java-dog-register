
//Elis Lundkvist ellu8410
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DogCollection {

	private ArrayList<Dog> dogs = new ArrayList<>();

	public boolean addDog(Dog dog) {
		if (dog == null || containsDog(dog.getName())) {
			return false;
		}

		dogs.add(dog);
		return true;
	}

	public boolean removeDog(String nameDog) {
		Dog dog = getDog(nameDog);
		if (dog != null) {

			Owner owner = dog.getOwner();
			if (owner != null) {
				return false;
			}

			dogs.remove(dog);
			return true;
		}
		return false;
	}

	public boolean removeDog(Dog dog) {
		if (dog.getOwner() != null) {
			return false;
		}
		if (dogs.contains(dog)) {
			dogs.remove(dog);
			return true;
		}

		return false;
	}

	public boolean containsDog(String nameDog) {
		for (Dog dog : dogs) {
			if (dog.getName().trim().equals(nameDog.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	public boolean containsDog(Dog dog) {
		return dogs.contains(dog);
	}

	public Dog getDog(String dog) {
		for (Dog firstDog : dogs) {
			if (firstDog.getName().equals(dog.toUpperCase())) {
				return firstDog;
			}
		}

		return null;
	}

	public ArrayList<Dog> getDogs() {
		ArrayList<Dog> dogsSorted = new ArrayList<>(dogs);

		Collections.sort(dogsSorted, new Comparator<Dog>() {
			public int compare(Dog firstDog, Dog secondDog) {

				int compareNames = firstDog.getName().compareTo(secondDog.getName());
				if (compareNames != 0) {
					return compareNames;
				}
				return Double.compare(firstDog.getTailLength(), secondDog.getTailLength());
			}
		});
		return dogsSorted;
	}

	public ArrayList<Dog> getDogsByTailLength(double minLength) {
		ArrayList<Dog> dogsWithMinimumTail = new ArrayList<>();

		for (Dog dog : dogs) {
			if (dog.getTailLength() >= minLength) {
				dogsWithMinimumTail.add(dog);
			}
		}

		dogsWithMinimumTail.sort(Comparator.comparingDouble(Dog::getTailLength).thenComparing(Dog::getName));
		return dogsWithMinimumTail;
	}

}
