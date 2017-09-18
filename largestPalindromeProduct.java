package loop;

public class palindrome {
	public static boolean guessSame(String guess) {
		StringBuffer str = new StringBuffer();
		String reverse;
		str.append(guess);
		reverse = str.reverse().toString();
		if(guess.equals(reverse)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int result = 0;
		int multi = 0;
		String multiString;
		for(int a = 100; a <= 999; a++) {
			for(int b = 100; b <= 999; b++) {
				multi = a * b;
				multiString = String.valueOf(multi);
				if(guessSame(multiString)) {
					if(result < multi) result = multi;
				}
			}
		}
		System.out.print(result);
	}
}
