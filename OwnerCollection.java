//Elis Lundkvist ellu8410

import java.util.ArrayList;
import java.util.Collections;

public class OwnerCollection {
	private Owner[] owners;
	private int size;

	public OwnerCollection() {
		this.owners = new Owner[10];
		this.size = 0;
	}

	public boolean addOwner(Owner owner) {
		if (owner == null || containsOwner(owner.getName())) {
			return false;
		}
		if (size == owners.length) {
			Owner[] newArray = new Owner[owners.length + 1];

			for (int i = 0; i < owners.length; i++) {
				newArray[i] = owners[i];
			}
			owners = newArray;
		}

		owners[size] = owner;
		size++;
		return true;
	}

	public boolean removeOwner(String name) {
		if (name == null || size == 0) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (owners[i].getName().equals(name)) {
				if(owners[i].getDogs().size() > 0) {
				    return false;
				}
				for(Dog dog : owners[i].getDogs()) {
					dog.setOwner(null);
				}
				//for(int j = i; j < size - 1; j++) {
				//	owners[j] = owners[j + 1];
					//if (!owners[i].getName().isEmpty()) {
					//return false;
				//}
				//for (Dog dog : owners[i].getDogs()) {
					//dog.setOwner(null);
				//}
				owners[i] = owners[size - 1];
				owners[size - 1] = null;
				size--;

				return true;
			}
		}
		return false;

	}

	public boolean removeOwner(Owner owner) {
		if (owner == null || size == 0) {
			return false;
		}
		if (owner.getDogs().size() > 0) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (owners[i].equals(owner)) {
				for (Dog dog : owner.getDogs()) {
					dog.setOwner(null);
				}

				owners[i] = owners[size - 1];
				owners[size - 1] = null;
				size--;
				return true;
			}
		}
		return false;
	}

	public boolean containsOwner(String name) {
		if (name == null || size == 0) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (owners[i].getName().trim().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsOwner(Owner name) {
		if (name == null || size == 0) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (owners[i].equals(name)) {
				return true;
			}
		}
		return false;
	}

	public Owner getOwner(String name) {
		if (name == null || size == 0) {
			return null;
		}
		for (int i = 0; i < size; i++) {
			if (owners[i] != null && owners[i].getName().equalsIgnoreCase(name)) {
				return owners[i];
			}
		}
		return null;
	}

	public ArrayList<Owner> getOwners() {
		ArrayList<Owner> ownerList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (owners[i] != null) {
				ownerList.add(owners[i]);
			}
		}

		Collections.sort(ownerList, (firstOwner, secondOwner) -> firstOwner.getName().compareToIgnoreCase(secondOwner.getName()));
		return ownerList;
	}

}
