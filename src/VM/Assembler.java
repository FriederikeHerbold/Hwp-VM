package VM;

import java.io.*;

import java.util.ArrayList;

public class Assembler {

	ArrayList<Integer> befehlsListe;

	/**
	 * 
	 * Diese Funktion liefert den Befehl am gewünschten Index der Befehlsliste
	 * 
	 */

	public int getOrder(int index) {

		return befehlsListe.get(index);

	}

	/**
	 * 
	 * Dieser Konstruktor erstellt eine Befehlsliste aus einem Textdokument mit
	 * 
	 * dem Filename des übergebenen Strings Auf diese Befehlsliste kann dann mit
	 * 
	 * int getOrder(int index) zugegriffen werden. Falls Ausnahmen Auftreten,
	 * 
	 * wird eine AssemblerException geworfen.
	 * 
	 */

	public Assembler(String Filename) throws AssemblerException {

		try {

			BufferedReader file = new BufferedReader(new FileReader(Filename));

			String befehl = null;

			while ((befehl = file.readLine()) != null) {

				befehlsListe.add(Integer.parseInt(befehl));

			}

		} catch (Exception e) {

			throw new AssemblerException(e.getMessage());

		}

	}

}