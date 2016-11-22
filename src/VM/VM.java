package VM;

import java.util.Stack;

public class VM implements Runnable {

	private Assembler myAssembler;
	private Stack<Integer> stack = new Stack<Integer>();
	private int prgCNT, indexRx, indexRy, cmd, opCode, to_Mem, from_Mem;
	private int[] reg = new int[4096];

	public VM(String fileName) throws AssemblerException {

		myAssembler = new Assembler(fileName);

	}

	@Override
	public void run() {
		do {
			opCode = myAssembler.getOrder(prgCNT);
			cmd = (opCode) & 0b1111;
			indexRx = (opCode >> 4) & 0b1111;
			indexRy = (opCode >> 8) & 0b1111;

			makeMenu(cmd);

		} while (prgCNT < myAssembler.getSize());

	}

	private void makeMenu(int command) {
		decodierung dec = new decodierung();
		boolean ende;
		do {
			ende = dec.tu(command);
		} while (!ende);
	}

	private int getValue(int opCode) {
		return (opCode >> 4) & 0b1111;
	}

	private int getToMem(int opCode) {
		return (opCode >> 13) & 1;
	}

	private int getFromMem(int opCode) {
		return (opCode >> 12) & 1;
	}
}
