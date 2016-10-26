package VM;

public class StartVM {

	public static void main(String[] args) {
	
		try {
			new Thread(new VM("filename")).start();
		} catch (AssemblerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
