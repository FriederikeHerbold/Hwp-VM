package VM;

import java.util.Scanner;

public class VM extends Thread {
	Assembler myAssembler;

	public static void main(String[] args) throws AssemblerException {

		Scanner sc = new Scanner(System.in);
		VM myVM = new VM(sc.nextLine());
		myVM.start();
	}

	/**
	 * This constructor creates a VM and its Assembler with the Code of the
	 * Text-File with the name Filename.
	 */
	public VM(String Filename) throws AssemblerException {
		myAssembler = new Assembler(Filename);
	}
}
