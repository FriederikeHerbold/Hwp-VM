package VM;

public class decodierung {
	// muss noch an der richtigen stelle eingefügt werden
	int eingang =0;
	int befehl = eingang & 0b111;
	int wert;
	int rx;
	int ry;
	int from_mem;
	int to_mem;
	
	{
	//	if (befehl == 0b0001 || befehl == 0b1001 || befehl == 0b1010 || befehl == 0b1011 || befehl == 0b1100) {
			wert = (eingang << 4) & 0b111111111111;

	//	} else {
			rx = (eingang << 4) & 0b1111;
			ry = (eingang << 8) & 0b1111;
			from_mem = (eingang << 12) & 0b1;
			to_mem = (eingang << 13) & 0b1;
	//	}
	}
}
