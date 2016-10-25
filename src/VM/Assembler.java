package VM;

import java.io.File;

public class Assembler {
	
	int[] befehlsListe;
	
	public int getOrder(int index){
		return befehlsListe[index];
	}
	
	public Assembler(String Filename){
		Object File=new File(Filename);
	}

}
