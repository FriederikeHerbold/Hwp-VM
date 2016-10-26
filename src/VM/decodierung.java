package VM;

public class decodierung {
	final int NOP = 0;
	final int LOAD = 1;
	final int MOV = 2;
	final int ADD = 3;
	final int SUB = 4;
	final int MUL = 5;
	final int DIV = 6;
	final int PUSH = 7;
	final int POP = 8;
	final int JMP = 9;
	final int JIZ = 10;
	final int JIH = 11;
	final int JSR = 12;
	final int RTS = 13;

	int[] speicher = new int[4095];
	int[] stack = new int[100];
	int scount = 0;
	int count = 0;

	public boolean tu(int eingang) {

		int befehl = eingang & 15; // 1111
		int wert = (eingang << 4) & 4095; // 111111111111
		int rx = (eingang << 4) & 15; // 1111
		int ry = (eingang << 8) & 15; // 1111
		int from_mem = (eingang << 12) & 1;
		int to_mem = (eingang << 13) & 1;
		{
			switch (befehl) {
			case NOP:
				count++;
				break;
			case LOAD:
				speicher[0] = wert;
				count++;
				break;
			case MOV:
				if (to_mem == 1) {
					speicher[speicher[rx]] = speicher[ry];
				} else if (from_mem == 1) {
					speicher[rx] = speicher[speicher[ry]];
				}
				count++;
				break;
			case ADD:
				speicher[rx] = speicher[rx] + speicher[ry];
				count++;
				break;
			case SUB:
				speicher[rx] = speicher[rx] - speicher[ry];
				count++;
				break;
			case MUL:
				speicher[rx] = speicher[rx] * speicher[ry];
				count++;
				break;
			case DIV:
				speicher[rx] = speicher[rx] / speicher[ry];
				count++;
				break;
			case PUSH:
				stack[scount] = rx;
				scount++;
				count++;
				break;
			case POP:
				scount--;
				speicher[rx] = stack[scount];
				count++;
				break;
			case JMP:
				count = wert;
				break;
			case JIZ:
				if (speicher[0] == 0) {
					count = wert;
				}
				break;
			case JIH:
				if (speicher[0] > 0) {
					count = wert;
				}
				break;
			case JSR:
				stack[scount] = (count + 1);
				scount++;
				count = wert;
				break;
			case RTS:
				if (scount != 0) {
					scount--;
					wert = stack[scount];
					count = wert;
				} else {
					return true;// beebden des programms while(!beendet)
				}
				break;
			}
			return false;
		}
	}
}
