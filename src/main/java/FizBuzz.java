
public class FizBuzz {

	private Printer printer;

	public FizBuzz(Printer printer) {
		this.printer = printer;
	}

	public void doIt() {
		for (int i = 1; i <= 100; i++) {
			if(i % 3 == 0 && i % 5 == 0) {
				print("FizzBuzz");
			} else if(i % 3 == 0) {
				print("Fizz");
			} else if(i % 5 == 0) {
				print("Buzz");
			} else {
				print(String.valueOf(i));
			}
		}
	}
	
	private void print(String s) {
		printer.print(s);
	}

	
}
