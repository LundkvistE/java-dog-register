import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {

	private static ArrayList<InputStream> myInputStream = new ArrayList<>();
	private Scanner scanner;

	public InputReader(InputStream name) {
		if (myInputStream.contains(name))
			throw new IllegalStateException("There is already another instance of InputReader");

		this.scanner = new Scanner(name);
		myInputStream.add(name);
	}

	public InputReader() {
		this(System.in);
	}

	public int getInt(String string) {
		System.out.println(string + "?> ");
		int number = scanner.nextInt();
		scanner.nextLine();
		return number;
	}

	public double getDouble(String string) {
		System.out.println(string + "?> ");
		double doubleNumber = scanner.nextDouble();
		scanner.nextLine();
		return doubleNumber;
	}

	public String getLine(String string) {
		System.out.println(string + "?> ");
		return scanner.nextLine();
	}
}