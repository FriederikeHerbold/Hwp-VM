package VM;

public interface Command {

	public static final int NOP = 0000;
	public static final int LOAD = 0001;
	public static final int MOV = 0010;
	public static final int ADD = 0011;
	public static final int SUB = 0100;
	public static final int MUL = 0101;
	public static final int DIV = 0110;
	public static final int PUSH = 0111;
	public static final int POP = 1000;
	public static final int JMP = 1001;
	public static final int JIZ = 1010;
	public static final int JIH = 1011;
	public static final int JSR = 1100;
	public static final int RTS = 1101;

	final static String[] arrayString = { "NOP", "LOAD", "MOV", "ADD", "SUB", "MUL", "DIV", "PUSH", "POP", "JMP", "JIZ",
			"JIH", "JSR", "RTS" };
	final static String[] arrayStringCode = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101" };

}
