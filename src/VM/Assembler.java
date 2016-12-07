package VM;

import java.io.*;

import java.util.ArrayList;

public class Assembler {

 	private int[] befehlsListe=new int[500];
	private int counter;
	
	public Assembler(String pfad) {

		BufferedReader file=null;
		try {

			 file = new BufferedReader(new FileReader(pfad));

			String befehl = null;
			counter=0;
			int code;
			while ((befehl = file.readLine()) != null) {
				code=methode(befehl);
				if(code)!=-1 && befehlsListe.length<500){
				 
					befehlsListe[counter++]=code;
					
				}
			}
			

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}finally{
			file.close();
		}

	}
	private static int search(String newCom) {
		for(int i=0;i<Command.arrayString.length;i++){
			if(newCom.equals(Command.arrayString[i])){
				return Command.arrayStringCode[i];
			}
		}
		return 0;
	}
	private static int methode(String command){
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
			befehl="-1";
		}
		return Integer.parseInt(befehl);
	}

	public int getOrder(int index) {
		return befehlsListe.get(index);
	}
	public int getSize(){
		return befehlsListe.size();
	}
	
	public int[] getListe(){
		return befehlsliste;
	}

}
