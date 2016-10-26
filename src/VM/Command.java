package VM;

public interface Command {

	public static final int NOP=0;
	public static final int LOAD=1;
	public static final int MOV=2;
	public static final int ADD=3;
	public static final int SUB=4;
	public static final int MUL=5;
	public static final int DIV=6;
	public static final int PUSH=7;
	public static final int POP=8;
	public static final int JMP=9;
	public static final int JIZ=10;
	public static final int JIH=11;
	public static final int JSR=12;
	public static final int RTS=13;
	
	
	
}
