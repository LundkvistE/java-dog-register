//Elis Lundkvist ellu8410
import java.util.ArrayList; //importera paketen som behövs.
import java.util.Comparator;

public class DogSorter {
    
    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogs, int startValue) { //nextDog metoden vi använde i förra uppgiften
        int minIndex = startValue;
        
        for(int i = startValue + 1; i < dogs.size(); i++) {
            if(comparator.compare(dogs.get(i), dogs.get(minIndex)) < 0 ) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    private static void swapDogs(ArrayList<Dog> dogs, int indexOne, int indexTwo) { //swapDogs metoden som vi använde i förförra uppgiften.
		Dog temp = dogs.get(indexOne);
		
		dogs.set(indexOne, dogs.get(indexTwo));
		dogs.set(indexTwo, temp);
	}

    
    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dogs) { // yttre loopen. två argument.
            int changeCount = 0;   //räknaren för byten.
            
            for (int i = 0; i < dogs.size(); i++) { //gå igenom listan 
                int minIndex = nextDog(comparator, dogs, i) ; //minIndex är nextDog
                
                
                if (minIndex != i) { // om minIndex inte är på i:s plats ska de bytas
                    swapDogs(dogs, i, minIndex); // byter plats om så är fallet
                    changeCount++; //ökar räkningen med 1.
                } 

              }
                return changeCount; // returnera hur många byten som gjorts.
            }
        
}