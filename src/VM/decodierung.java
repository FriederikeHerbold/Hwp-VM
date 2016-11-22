package VM;

import java.util.Stack;

public class decodierung {
	final int OFFSET = 20;

	int[] memory = new int[4095];
	Stack<Integer> stack = new Stack<Integer>();
	int scount = 0;
	int count = 0 + OFFSET;
	boolean beendet = false;
	{
		while (!beendet || count < memory.length) {
			beendet = tu(memory[count]);

		}
	}

	public boolean tu(int opcode) {

		switch (getcmd(opcode)) {
		case Command.arrayInt[0]:
			count++;
			break;
		case Command.arrayInt[1]:
			memory[0] = getvalue(opcode);
			count++;
			break;
		case Command.arrayInt[2]:
			if (getto_mem(opcode) == 1 && getfrom_mem(opcode) == 1) {
				memory[memory[getrx(opcode)]] = memory[memory[getry(opcode)]];
			} else if (getto_mem(opcode) == 1) {
				memory[memory[getrx(opcode)]] = memory[getry(opcode)];
			} else if (getfrom_mem(opcode) == 1) {
				memory[getrx(opcode)] = memory[memory[getry(opcode)]];
			} else {
				memory[getrx(opcode)] = memory[getry(opcode)];
			}
			count++;
			break;
		case Command.arrayInt[3]:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) + (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.arrayInt[4]:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) - (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.arrayInt[5]:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) * (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.arrayInt[6]:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) / (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.arrayInt[7]:
			stack.push(getrx(opcode));
			scount++;
			count++;
			break;
		case Command.arrayInt[8]:
			scount--;
			memory[getrx(opcode)] = stack.pop();
			count++;
			break;
		case Command.arrayInt[9]:
			count = getvalue(opcode) + OFFSET;
			break;
		case Command.arrayInt[10]:
			if (memory[0] == 0) {
				count = getvalue(opcode) + OFFSET;
			}
			break;
		case Command.arrayInt[11]:
			if (memory[0] > 0) {
				count = getvalue(opcode) + OFFSET;
			}
			break;
		case Command.arrayInt[12]:
			stack.push(count + 1);
			scount++;
			count = getvalue(opcode) + OFFSET;
			break;
		case Command.arrayInt[13]:
			if (!stack.isEmpty()) {
				scount--;
				count = stack.pop();
			} else {
				return true;// beebden des programms while(!beendet)
			}
			break;
		}
		return false;
	}

	private static int getcmd(int opcode) {
		return (opcode & 15); // 1111
	}

	private int getrx(int opcode) {
		return (opcode << 4) & 15; // 1111
	}

	private int getry(int opcode) {
		return (opcode << 8) & 15; // 1111
	}

	private int getvalue(int opcode) {
		return (opcode << 4) & 4095; // 111111111111
	}

	private int getto_mem(int opcode) {
		return (opcode << 12) & 1;
	}

	private int getfrom_mem(int opcode) {
		return (opcode << 13) & 1;
	}
}
