package VM;

import java.util.Stack;

public class VM implements Runnable {

	private Assembler myAssembler;
	private Stack<Integer> stack=new Stack<Integer>();
	private int prgCNT,indexRx,indexRy,cmd,opCode,to_Mem,from_Mem;
	private int [] reg=new int[4096];
	
	
	public VM (String fileName) throws AssemblerException {
		
		myAssembler=new Assembler(fileName);

	}


	@Override
	public void run() {
		do{
			opCode=myAssembler.getOrder(prgCNT);
			cmd= (opCode) & 0b1111;
			indexRx=(opCode>>4)& 0b1111;
			indexRy=(opCode >>8)& 0b1111;
			to_Mem=(opCode>>12) & 1;
			from_Mem=(opCode>>13) & 1;
			
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
			if(to_Mem==1){
				reg[indexRx]=reg[indexRy];
			}else if(from_Mem==1){
				reg[indexRy]=reg[indexRx];
			}
			prgCNT++;
			break;
		case(Command.ADD):
			reg[indexRy] = reg[indexRy] + reg[indexRy];
			prgCNT++;
			break;
		case(Command.SUB):
			reg[indexRx]-=reg[indexRy];
			prgCNT++;
		case(Command.MUL):
			reg[indexRx]*=reg[indexRy];
			prgCNT++;
			break;
		case (Command.DIV):
			reg[indexRx]/=reg[indexRy];
			prgCNT++;
		case(Command.PUSH):
			stack.push(reg[indexRx]);
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
	
}
