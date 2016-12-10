package VM;




import java.io.*;

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
		int i = 0;
		String ret = "Fehler";
		while (ret == "Fehler" && i < Command.arrayString.length) {
			if (newCom.equals(Command.arrayString[i])) {
				ret = Command.arrayStringCode[i];
			}
			i++;
		}
		return ret;
	}

	private static int methode(String command) {
		String befehl = "";
		String[] array = command.split(" ");
		command = "";
		String com = array[0];

		if (!array[0].startsWith("/")) {
			command += search(array[0]);

			switch (com) {
			case "NOP":
				command += search(array[0]);
				break;
			case "RTS":
				command += search(array[0]);
				break;
			case "LOAD":
				befehl = Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "JIZ":
				befehl =Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "JIH":
				befehl =Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "JMP":
				befehl = Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "JSR":
				befehl = Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "POP":
				befehl =Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "PUSH":
				befehl = Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "ADD":
				befehl = "00" + Integer.toBinaryString(Integer.parseInt(array[2])) + Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "SUB":
				befehl = "00" + Integer.toBinaryString(Integer.parseInt(array[2])) + Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "MUL":
				befehl = "00" + Integer.toBinaryString(Integer.parseInt(array[2])) +Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "DIV":
				befehl = "00" + Integer.toBinaryString(Integer.parseInt(array[2])) + Integer.toBinaryString(Integer.parseInt(array[1])) + command;
				break;
			case "MOV":
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
				break;
			default:
				befehl = "-1";
			}
		}
		return Integer.parseInt(befehl);
	}

	public int[] getListe() {
		return befehlsListe;
	}

}
