package VM;


public interface Command {

	final static String [] arrayString={"NOP","LOAD","MOV","ADD","SUB","MUL","DIV","PUSH","POP","JMP","JIZ","JIH","JSR","RTS"};
	  final static int   [] arrayStringCode={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011",
						 "1100","1101"};
	
	
}
