package VM;

public class Main {
	public static final int OFFSET = 20;
	private static final String pfad = "C:/Users/fherb/Desktop/HS/Programme/Hwp-VM/src/VM/";

	public static void main(String[] args) {

		int[] programm;
		// int[] test = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		String filename = "Test1.txt";
		Assembler assembler = new Assembler(pfad + filename);
		programm = assembler.getListe();
		VM lauf = new VM(programm, OFFSET);
		lauf.print();
		lauf.start();

	}

}
