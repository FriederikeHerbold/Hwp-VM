package VM;

import java.util.Stack;

public class VM implements Runnable {

	private Assembler myAssembler;
	private Stack<Integer> stack=new Stack<Integer>();
	private int prgCNT,cmd,opCode;
	private int [] reg=new int[4096];
	
	
	public VM (String fileName) throws AssemblerException {
		
		myAssembler=new Assembler(fileName);

	}


	@Override
	public void run() {
		do{
			opCode=myAssembler.getOrder(prgCNT);
			cmd= (opCode) & 0b1111;
			
			makeMenu(cmd);
				
		}while(prgCNT<myAssembler.getSize());
		
	}


	private void makeMenu(int command) {
		
	
		switch(command){
			
		case(Command.NOP):
			break;
		case(Command.LOAD):
			reg[0]=getValue(opCode);
			prgCNT++;
			break;
		case(Command.MOV):
			if(getToMem(opCode)==1){
				reg[getIndexRx(opCode)]=reg[getIndexRy(opCode)];
			}else if(getFromMem(opCode)==1){
				reg[getIndexRy(opCode)]=reg[getIndexRx(opCode)];
			}
			prgCNT++;
			break;
		case(Command.ADD):
			reg[getIndexRx(opCode)]+= reg[getIndexRy(opCode)];
			prgCNT++;
			break;
		case(Command.SUB):
			reg[getIndexRx(opCode)]-=reg[getIndexRy(opCode)];
			prgCNT++;
		case(Command.MUL):
			reg[getIndexRx(opCode)]*=reg[getIndexRy(opCode)];
			prgCNT++;
			break;
		case (Command.DIV):
			reg[getIndexRx(opCode)]/=reg[getIndexRy(opCode)];
			prgCNT++;
		case(Command.PUSH):
			stack.push(reg[getIndexRx(opCode)]);
			prgCNT++;
			break;
		case(Command.POP):
			stack.pop();
			break;
		case(Command.JMP):
			prgCNT=getValue(opCode);
			break;
		case(Command.JIZ):
			if(reg[0]==0){
				prgCNT=getValue(opCode);
			}
			break;
		case(Command.JIH):
			if(reg[0]>0){
				prgCNT=getValue(opCode);
			}
			break;
		case(Command.JSR):
			
			stack.push(prgCNT+1);
			
			prgCNT=getValue(opCode);
			
			break;
		case(Command.RTS):
			if(stack.isEmpty()){
				System.exit(0);
			}
			prgCNT=stack.pop();
			
			break;
		default:
			break;
			
		}
	}
	
	private int getValue(int opCode){
		return (opCode>>4) & 0b1111;
	}
	
	private int getToMem(int opCode){
		return (opCode>>13) & 1;
	}
	private int getFromMem(int opCode){
		return (opCode>>12) & 1;
	}
	private int getIndexRx(int opCode){
		return (opCode>>4)& 0b1111;
	}
	private int getIndexRy(int opCode){
		return (opCode>>8) & 0b1111;
	}
}
