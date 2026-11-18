//Elis Lundkvist ellu8410

import java.util.ArrayList;
import java.util.Comparator;

public class DogRegister {

	private static final String END_COMMAND = "exit";
	private OwnerCollection ownerCollection;
	private InputReader input;
	private DogCollection dogCollection;

	public DogRegister() {
		this.input = new InputReader();
		this.ownerCollection = new OwnerCollection();
		this.dogCollection = new DogCollection();
	}

	public static void main(String[] args) {
		new DogRegister().start();
	}

	private void start() {
		runCommandLoop();
		shutDown();
	}

	private void runCommandLoop() {
		String command;
		do {

			command = readCommand();
			command = command.toUpperCase();
			System.out.println(command);
			handleCommand(command);
		} while (!command.equalsIgnoreCase(END_COMMAND));

	}

	private String readCommand() {
		System.out.println("Enter command?>");
		return input.getLine("");
	}

	private void handleCommand(String command) {
		switch (command.toLowerCase()) {
		case "register new dog":
			registerNewDog();
			break;
		case "register new owner":
			registerNewOwner();
			break;
		case "remove owner":
			removeOwner();
			break;
		case "remove dog":
			removeDog();
			break;
		case "list owners":
			listOwners();
			break;
		case "list dogs":
			listDogs();
			break;
		case "increase age":
			increaseAge();
			break;
		case "give dog to owner":
			giveDogToOwner();
			break;
		case "remove dog from owner":
			removeDogFromOwner();
			break;
		case END_COMMAND:
			break;
		default:
			System.out.println("Error: Command is not valid. ");
		}
	}

	private void listOwners() {
		if (ownerCollection.getOwners().isEmpty()) {
			System.out.println("Error: No owners registered");
			return;
		}
		System.out.println("Registered owners");

		for (Owner owner : ownerCollection.getOwners()) {
			String dogNames = owner.getDogs().isEmpty() ? "No dogs" : owner.getDogs().toString();
			System.out.println(owner.getName() + " | Dogs: " + dogNames);

		}

	}

	private void listDogs() {
		if(dogCollection.getDogs().isEmpty()) {
			System.out.println("Error: No dogs are registrered");
			return;
		}
		
		double minTailLength = input.getDouble("Enter minimum tail length:");
		ArrayList<Dog> dogs = dogCollection.getDogsByTailLength(minTailLength);
		
		if(dogs.isEmpty()) {
			System.out.println("No dogs found with a tail length of " + minTailLength);
			return;
		}
		Comparator<Dog> comparator = Comparator.comparing(Dog :: getTailLength);
		DogSorter.sortDogs(comparator, dogs);
			System.out.println("Dogs meeting the criteria");
			for (Dog dog : dogs) {
				System.out.println(dog);
			}
	}
	
	private void registerNewDog() {
		String name;
		String breed;
		name = input.getLine("Enter dog name").trim();
		while (name.isEmpty()) {
			System.out.println("Error: A blank string is not allowed, try again");
			name = input.getLine("Enter dog name").trim();
		}

		if (!name.isEmpty() && dogCollection.containsDog(name)) {
			System.out.println("Error: The dog " + name + " already exists");
			return;
		}

		do {
			breed = input.getLine("Enter dog breed").trim();
			if (breed.isEmpty()) {
				System.out.println("Error: A blank string is not allowed, try again!");
			}
		} while (breed.isEmpty());

		int age = input.getInt("Enter dog age");
		int weight = input.getInt("Enter dog weight?>");

		if (dogCollection.addDog(new Dog(name, breed, age, weight))) {
			System.out.println("The dog " + name + " has been added to the register");
		}
	}

	private void registerNewOwner() {
		String name;

		do {
			name = input.getLine("Enters owners name").trim();
			if (name.isEmpty()) {
				System.out.println("Error: A blank string is not allowed");
			}
		} while (name.isEmpty());

		if (ownerCollection.containsOwner(name)) {
			System.out.println("Error: The owner " + name + " already exists");
			return;
		}

		Owner newOwner = new Owner(name);
		if (ownerCollection.addOwner(newOwner)) {
			System.out.println("The owner " + name + " has been added to the register.");
		} else {
			System.out.println("Error: Failed to add owner " + name);

		}

	}

	private void removeOwner() { // Kommandot removeOwner.
		if (ownerCollection.getOwners().isEmpty()) { // Om samlingen med hjälp av getOwners är tom.
			System.out.println("Error: No owners to remove"); // Skriv ut Error-meddelandet.
			return; // Returnera.
		}
		String ownerName;
		do {
			ownerName = input.getLine("Enter owners name to remove").trim();
			if (ownerName.isEmpty()) {
				System.out.println("Error: A blank string is not allowed");
			}
		} while (ownerName.isEmpty());

		Owner owner = ownerCollection.getOwner(ownerName);

		if (owner == null) {
			System.out.println("Error: Owner not found");
			return;
		}
		for (Dog dog : dogCollection.getDogs()) {
			if (dog.getOwner() == owner) {
				dog.setOwner(null);
				dogCollection.removeDog(dog);
			}
		}
		if (ownerCollection.removeOwner(owner)) {
			System.out.println("The owner " + ownerName + " has been removed");
		} else {
			System.out.println("Error: Failed to remove the owner " + ownerName);
		}
	}

	private void removeDog() {
		if (dogCollection.getDogs().isEmpty()) {
			System.out.println("Error: No dogs in the register");
			return;
		}

		String dogName;
		do {
			dogName = input.getLine("Enter dog name").trim();
			if (dogName.isEmpty()) {
				System.out.println("Error: A blank string is not allowed, try again");
			}
		} while (dogName.isEmpty());

		Dog dogToRemove = dogCollection.getDog(dogName);
		if (dogToRemove == null) {
			System.out.println("Error: The dog " + dogName + " doesn't exist");
			return;
		}

		Owner owner = dogToRemove.getOwner();
		if (owner != null) {
			owner.removeDog(dogToRemove);
			System.out.println("The dog " + dogName + " has been removed from the register");

		}
		if (dogCollection.removeDog(dogToRemove)) {
			System.out.println("The dog " + dogName + " has been removed from the register.");
		} else {
			System.out.println("Error: Failed to remove the dog " + dogName + " from the register");
		}
	}

    private void increaseAge() {
		if (dogCollection == null || dogCollection.getDogs().isEmpty()) {
			System.out.println("Error: No dogs in the register");
			return;
		}
		String dogName;
		do {
			dogName = input.getLine("Enter dog name to increase age:").trim();
			if (dogName.isEmpty()) {
				System.out.println("Error: A blank string is not allowed, try again.");
			}
		} while (dogName.isEmpty());

		Dog dogToIncrease = dogCollection.getDog(dogName);
		if (dogToIncrease == null) {
			System.out.println("Error: The dog " + dogName + " does not exist");
			return;
		}
		dogToIncrease.updateAge();
		System.out.println("The new age is " + dogToIncrease.getAge());

	}

	private void giveDogToOwner() {
		if (dogCollection.getDogs().isEmpty() || ownerCollection.getOwners().isEmpty()) {
			System.out.println("Error: The dog does not exist");
			return;
		}

		String dogName;
		String ownerName;
		do {
			dogName = input.getLine("Enter dog name to give to owner:").trim();
		} while (dogName.isEmpty());
		Dog dogToGive = dogCollection.getDog(dogName);
		if (dogToGive == null || dogToGive.getOwner() != null) {
			System.out.println(
					"Error: " + (dogToGive == null ? "The dog does not exist" : "The dog already has an owner"));
			return;
		}

		do {
			ownerName = input.getLine("Enter owner name:").trim();
		} while (ownerName.isEmpty());
		Owner ownerToGiveDog = ownerCollection.getOwner(ownerName);
		if (ownerToGiveDog == null) {
			System.out.println("Error: The owner does not exist");
			return;
		}

		System.out.println(dogToGive.setOwner(ownerToGiveDog) ? "The dog " + dogName + " has been given to " + ownerName
				: "Error: Failed to give the dog to owner");
	}

	private void removeDogFromOwner() {
		if (ownerCollection.getOwners().isEmpty()) {
			System.out.println("Error: No owners registered");
			return;
		}
		if (dogCollection.getDogs().isEmpty()) {
			System.out.println("Error: No dogs registered");
			return;
		}

		String dogName;
		do {
			dogName = input.getLine("Enter dog name");
			if (dogName.isEmpty()) {
				System.out.println("Error: A blank string is not allowed, try again");
			}
		} while (dogName.isEmpty());

		Dog dog = dogCollection.getDog(dogName);
		if (dog == null || dog.getOwner() == null) {
			System.out.println(
					"Error: The dog " + dogName + " can't be removed because it was not found or has no owner.");
			return;
		}

		Owner owner = dog.getOwner();
		owner.removeDog(dog);
		dog.setOwner(null);
		System.out.println("The dog " + dogName + " has been removed from its owner");
	}

	private void shutDown() {
		System.out.println("Shutting down");

	}

}
