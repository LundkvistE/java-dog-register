//Elis Lundkvist ellu8410
public class Dog {

	private static final int MAXIMUM_AGE = 100000;
	private static final double TAX_TAIL_LENGTH = 3.7;

	private final String name;
	private final String breed;
	private int age;
	private int weight;
	private Owner owner;

	public Dog(String name, String breed, int age, int weight) {
		this.name = name.toUpperCase();
		this.breed = breed.toUpperCase();
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public void updateAge() {
		if (age < MAXIMUM_AGE) {
			this.age++;
		} else {
			System.out.println("Over maximum age");
		}
	}

	public double getTailLength() {
		if (breed.equalsIgnoreCase("Dachshund") || breed.equalsIgnoreCase("Tax")) {
			return TAX_TAIL_LENGTH;
		} else {
			return age * weight / 10.0;
		}
	}

	public String toString() {
		return "Name: " + name + " " + "Breed: " + breed + " " + "Weight: " + age + " " + "Weight: " + weight + " "
				+ "Taillength: " + getTailLength() + (owner != null ? "; Owner: " + owner.getName() : "");

	}

	public boolean setOwner(Owner ownerOfDog) {
		if (ownerOfDog == null && getOwner() != null) {
			owner.removeDog(this);

			this.owner = null;
			return true;
		}

		if (this.owner != null || this.owner == ownerOfDog || ownerOfDog.getDogs().contains(this)) {
			return false;
		}

		boolean ownerSet = ownerOfDog.addDog(this);

		if (ownerSet) {
			this.owner = ownerOfDog;
		}
		return true;

	}

	public Owner getOwner() {
		return owner;
	}
}
