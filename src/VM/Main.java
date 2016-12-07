package VM;

public class Main {
	public static final int OFFSET = 20;

	public static void main(String[] args) {

		int[] programm;
		String filename = "";
		Assembler assembler = new Assembler(filename, OFFSET);
		programm = assembler.eingang();
		VM lauf = new VM(programm, OFFSET);
		lauf.start();

	}

}
