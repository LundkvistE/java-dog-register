
// Elis Lundkvist ellu8410
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Owner implements Comparable<Owner> {

	private final String name;
	private ArrayList<Dog> dogs;

	public Owner(String name) {
		this.name = name.toUpperCase();
		this.dogs = new ArrayList<>();
	}

	public boolean addDog(Dog dog) {
		if (dog == null) {
			return false;
		}
		if (dog.getOwner() != null && dog.getOwner() != this) {
			return false;
		}
		if (!this.dogs.contains(dog)) {
			this.dogs.add(dog);
			dog.setOwner(this);
			return true;
		}

		return false;
	}

	public boolean removeDog(Dog removeThisDog) {

		if (removeThisDog != null && this.dogs.contains(removeThisDog)) {
			this.dogs.remove(removeThisDog);
			removeThisDog.setOwner(null);
			return true;
		}
		return false;
	}

	public ArrayList<Dog> getDogs() {
		ArrayList<Dog> listOfOwners = new ArrayList<>(this.dogs);
		Collections.sort(listOfOwners, new Comparator<Dog>() {
			public int compare(Dog dogOne, Dog dogTwo) {
				return dogOne.getName().compareTo(dogTwo.getName());
			}
		});
		return listOfOwners;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		String sumOfString = "Name of owner " + name + "| Dogs: ";
		if (!dogs.isEmpty()) {
			String dogsName = "";
			for (int i = 0; i < dogs.size(); i++) {
				dogsName += dogs.get(i).getName();
				if (i < dogs.size() - 1) {
					dogsName += ", ";
				}
			}
			sumOfString += dogsName;
		} else {
			sumOfString += "No dog";
		}
		return sumOfString;
	}

	public int compareTo(Owner owner) {

		return this.name.compareTo(owner.getName());
	}

}
