package VM;

import static gdi.MakeItSimple.*;

public class VM extends Thread{
	
	Assembler myAssembler;

	public static void main(String[] args) throws AssemblerException {
		VM myVM=new VM(readLine());
		myVM.start();
	}
	
	/**This constructor creates a VM and its Assembler with the Code of the Text-File with the name Filename.*/
	public VM(String Filename) throws AssemblerException{
		myAssembler=new Assembler(Filename);
	}

}