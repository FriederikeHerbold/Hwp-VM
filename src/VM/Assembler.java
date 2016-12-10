package VM;

import java.io.*;
import java.util.ArrayList;

public class Assembler {
	
	private int[] befehlsListe = new int[500];
	private int counter;

	public Assembler(String pfad) {

		BufferedReader file = null;
		try {

			file = new BufferedReader(new FileReader(pfad));

			String befehl = null;
			counter = 0;
			int code;
			while ((befehl = file.readLine()) != null) {
				code = methode(befehl);
				if (code != -1 && counter < befehlsListe.length) {

					befehlsListe[counter++] = code;

				}
			}
			file.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}
	private static String search(String newCom) {
		String returnString="";
		for(int i=0;i<Command.arrayString.length;i++){
			if(newCom.equals(Command.arrayString[i])){
				returnString+=Command.arrayStringCode[i];
			}
		}
		return returnString;
	}
	public static String methode(String command){
		String befehl ="";
		
		if(!command.startsWith("/")){
			String [] array=command.split(" ");
			command="";
				command+=search(array[0]);
				
				if(array[0].equals("NOP") || array[0].equals("RTS")){
					befehl=command;
					
				}else if(array[0].equals("LOAD") || array[0].equals("JIZ") ||  array[0].equals("JIH") ||  array[0].equals("JMP") ||  array[0].equals("JSR") || array[0].equals("POP") || array[0].equals("PUSH")){
					
					befehl=Integer.toBinaryString(Integer.parseInt(array[1]))+command;
					
					
				}else if(array[0].equals("ADD") || array[0].equals("SUB") || array[0].equals("MUL") || array[0].equals("DIV")){
					
					befehl="00"+Integer.toBinaryString(Integer.parseInt(array[2]))+Integer.toBinaryString(Integer.parseInt(array[1]))+command;	
					
				}else if(array[0].equals("MOV")){
					if(array[1].startsWith("(") && array[1].endsWith(")")){
						if(array[2].startsWith("(") && array[2].endsWith(")")){
							
							befehl="11"+Integer.toBinaryString(Integer.parseInt(array[2].substring(1,array[2].length()-1)))+
							Integer.toBinaryString(Integer.parseInt(array[1].substring(1,array[1].length()-1)))+command;
						}else{
							befehl="10"+Integer.toBinaryString(Integer.parseInt(array[2]))+
									Integer.toBinaryString(Integer.parseInt(array[1].substring(1,array[1].length()-1)))+command;
						}
						
					}else{
						if(array[2].startsWith("(") && array[2].endsWith(")")){
							befehl="01"+Integer.toBinaryString(Integer.parseInt(array[2].substring(1,array[2].length()-1)))
							+Integer.toBinaryString(Integer.parseInt(array[1]))+command;
						}else{
							befehl="00"+Integer.toBinaryString(Integer.parseInt(array[2]))+Integer.toBinaryString(Integer.parseInt(array[1]))+command;
						}
					}
				}else{
					befehl="-1";
				}
			
		}else{
			befehl="-1";
		}
		return Integer.parseInt(befehl);
	}

}
