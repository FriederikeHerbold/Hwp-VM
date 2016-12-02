package VM;

import java.io.*;

import java.util.ArrayList;

public class Assembler {

	ArrayList<Integer> befehlsListe;

	public int getOrder(int index) {

		return befehlsListe.get(index);

	}

	public Assembler(String Filename) throws AssemblerException {

		BufferedReader file=null;
		try {

			 file = new BufferedReader(new FileReader(Filename));

			String befehl = null;

			while ((befehl = file.readLine()) != null) {
				if(!befehl.equals("NO"){
					befehlsListe.add(methode(befehl));
				}
			}

		} catch (Exception e) {

			throw new AssemblerException(e.getMessage());

		}finally{
		   file.close();	
		}

	}
	private static int search(String newCom) {
		for(int i=0;i<Command.arrayString.length;i++){
			if(newCom.equals(Command.arrayString[i])){
				return Command.arrayInt[i];
			}
		}
		return 0;
	}
	public static String methode(String command){
		String befehl ="";
		String [] array=command.split(" ");
		command="";
		if(!array[0].startsWith("/")){
			
				command+=search(array[0]);
				
				if(array[0].equals("NOP") || array[0].equals("RTS")){
					befehl=command;
					
				}else if(array[0].equals("LOAD") || array[0].equals("JIZ") ||  array[0].equals("JIH") ||  array[0].equals("JMP") ||  array[0].equals("JSR") || array[0].equals("POP") || array[0].equals("PUSH")){
					
					befehl=array[1]+command;
					
					
				}else if(array[0].equals("ADD") || array[0].equals("SUB") || array[0].equals("MUL") || array[0].equals("DIV")){
					
					befehl="00"+array[2]+array[1]+command;	
					
				}else if(array[0].equals("MOV")){
					if(array[1].startsWith("(") && array[1].endsWith(")")){
						if(array[2].startsWith("(") && array[2].endsWith(")")){
							befehl="11"+array[2].substring(1,array[2].length()-1)+array[1].substring(1,array[1].length()-1)+command;
						}else{
							befehl="10"+array[2]+array[1].substring(1,array[1].length()-1)+command;
						}
						
					}else{
						if(array[2].startsWith("(") && array[2].endsWith(")")){
							befehl="01"+array[2].substring(1,array[2].length()-1)+array[1]+command;
						}else{
							befehl="00"+array[2]+array[1]+command;
						}
					}
				}
			
		}else{
			befehl="NO";
		}
		return befehl;
	}


}
