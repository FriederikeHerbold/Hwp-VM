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
		case Command.NOP:
			count++;
			break;
		case Command.LOAD:
			memory[0] = getvalue(opcode);
			count++;
			break;
		case Command.MOV:
			if (getto_mem(opcode) == 1) {
				memory[memory[getrx(opcode)]] = memory[getry(opcode)];
			} else if (getfrom_mem(opcode) == 1) {
				memory[getrx(opcode)] = memory[memory[getry(opcode)]];
			}
			count++;
			break;
		case Command.ADD:
			memory[getrx(opcode)] = memory[getrx(opcode)] + memory[getry(opcode)];
			count++;
			break;
		case Command.SUB:
			memory[getrx(opcode)] = memory[getrx(opcode)] - memory[getry(opcode)];
			count++;
			break;
		case Command.MUL:
			memory[getrx(opcode)] = memory[getrx(opcode)] * memory[getry(opcode)];
			count++;
			break;
		case Command.DIV:
			memory[getrx(opcode)] = memory[getrx(opcode)] / memory[getry(opcode)];
			count++;
			break;
		case Command.PUSH:
			stack.push(getrx(opcode));
			scount++;
			count++;
			break;
		case Command.POP:
			scount--;
			memory[getrx(opcode)] = stack.pop();
			count++;
			break;
		case Command.JMP:
			count = getvalue(opcode) + OFFSET;
			break;
		case Command.JIZ:
			if (memory[0] == 0) {
				count = getvalue(opcode) + OFFSET;
			}
			break;
		case Command.JIH:
			if (memory[0] > 0) {
				count = getvalue(opcode) + OFFSET;
			}
			break;
		case Command.JSR:
			stack.push(count + 1);
			scount++;
			count = getvalue(opcode) + OFFSET;
			break;
		case Command.RTS:
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

	private int getcmd(int opcode) {
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
