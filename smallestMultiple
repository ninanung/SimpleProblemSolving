package simplesolve;

public class smallestMultiple {
	public static int divide() {
		int standard = 1;
		int guess = 0;
		int result = 0;
		while(true) {
			for(int i = 20; i > 0; i--) {
				if(standard % i == 0) {
					guess = i;
					continue;
				}
				else break;
			}
			result = standard;
			if(guess == 1) return result; 
			standard++;
		}
	}
	public static void main(String[] args) {
		System.out.print(divide());
	}
}
