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
	
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}finally{
			fin.close();
		}
	}
	private static int search(String newCom) {
		int number=-1;
		for(int i=0;i<Command.arrayString.length && number==-1;i++){
			if(newCom.equals(Command.arrayString[i])){
				number=i;
			}
		}
		return number;
	}
	public static int methode(String command1){
		int befehl =0;
		
		if(!command1.startsWith("/")){
			String [] array=command1.split(" ");
			int command=0;
				command=search(array[0]);
				
				if(array[0].equals("NOP") || array[0].equals("RTS")){
					befehl=command;
					
				}else if(array[0].equals("LOAD") || array[0].equals("JIZ") ||  array[0].equals("JIH") ||  array[0].equals("JMP") ||  array[0].equals("JSR") || array[0].equals("POP") || array[0].equals("PUSH")){
					
					befehl=(Integer.parseInt(array[1])<<4)+command;
					
					
				}else if(array[0].equals("ADD") || array[0].equals("SUB") || array[0].equals("MUL") || array[0].equals("DIV")){
					
					befehl=((Integer.parseInt(array[2])<<4)+Integer.parseInt(array[1])<<4)+command;	
					
				}else if(array[0].equals("MOV")){
					if(array[1].startsWith("(") && array[1].endsWith(")")){
						if(array[2].startsWith("(") && array[2].endsWith(")")){
							
							befehl=(1<<13)+(1<<12)+((Integer.parseInt(array[2].substring(1,array[2].length()-1))<<4)+Integer.parseInt(array[1].substring(1,array[1].length()-1))<<4)+command;
						}else{
							befehl=(1<<13)+(0<<12)+((Integer.parseInt(array[2])<<4)+Integer.parseInt(array[1].substring(1,array[1].length()-1))<<4)+command;
						}
						
					}else{
						if(array[2].startsWith("(") && array[2].endsWith(")")){
							befehl=(0<<13)+(1<<12)+((Integer.parseInt(array[2].substring(1,array[2].length()-1))<<4+Integer.parseInt(array[1]))<<4)+command;
						}else{
							befehl=(0<<13)+(0<<12)+((Integer.parseInt(array[2]))<<4+Integer.parseInt(array[1])<<4)+command;
						}
					}
				}else{
					befehl=command;
				}
			
		}else{
			befehl=-1;
		}
		return befehl;
	}
	public int[] getListe() {
		return befehlsListe;
	}
}
