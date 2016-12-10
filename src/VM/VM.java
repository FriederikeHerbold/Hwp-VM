import java.util.Stack;

public class VM {

	int[] memory = new int[4095];
	Stack<Integer> stack = new Stack<Integer>();
	int count;
	int offset;
	private int prog_Max;
	boolean beendet = false;

	VM(int[] code, int off) {
		offset = off;
		count = 0 + off;
		prog_Max = 500 + off;
		for (int i = 0; i < prog_Max && i < code.length; i++) {
			memory[i + offset] = code[i];
		}
	}

	public void start() {
		while (!beendet && count < prog_Max) {
			beendet = tu(memory[count]);
		}
	}

	private boolean tu(int opcode) {

		switch (getcmd(opcode)) {
		case Command.NOP:
			count++;
			break;
		case Command.LOAD:
			memory[0] = getvalue(opcode);
			count++;
			break;
		case Command.MOV:
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
		case Command.ADD:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) + (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.SUB:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) - (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.MUL:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) * (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.DIV:
			memory[getrx(opcode)] = (memory[getrx(opcode)] << 20) / (memory[getry(opcode)]);
			memory[getrx(opcode)] = (memory[getrx(opcode)]) >> 20;
			count++;
			break;
		case Command.PUSH:
			stack.push(getrx(opcode));
			count++;
			break;
		case Command.POP:
			memory[getrx(opcode)] = stack.pop();
			count++;
			break;
		case Command.JMP:
			count = getvalue(opcode) + offset;
			break;
		case Command.JIZ:
			if (memory[0] == 0) {
				count = getvalue(opcode) + offset;
			}
			break;
		case Command.JIH:
			if (memory[0] > 0) {
				count = getvalue(opcode) + offset;
			}
			break;
		case Command.JSR:
			stack.push(count + 1);
			count = getvalue(opcode) + offset;
			break;
		case Command.RTS:
			if (!stack.isEmpty()) {
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

	public void print() {
		for (int i = 0; i < memory.length; i++) {
			System.out.println("" + i + " | " + memory[i]);
		}
	}
}
