package VM;

import java.io.*;
import java.util.ArrayList;

public class Assembler {
	
	private ArrayList<Integer> memory;
	
	public int getOrder(int index){
		return memory.get(index);
	}
	
	public int getSize(){
		return memory.size();
	}
	
	public Assembler(String Filename) throws AssemblerException{
		try{
			BufferedReader file= new BufferedReader(new FileReader(Filename));
			String befehl=null;
			
			while((befehl=file.readLine())!=null){
				if(befehl.length()!=0)
					memory.add(Integer.parseInt(befehl));
			}
				file.close();
		}catch(Exception e){
			throw new AssemblerException(e.getMessage());
		}
	}

}
