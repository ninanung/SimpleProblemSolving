public class LargestPrimeFactors {
	public static long findPrimeFactor(long num) {
		long first = 1;
		long result = 0;
		while (first <= num) {
			if (num % first == 0) {
				long second = 1;
				long resultOfSecond = 0;
				while (first >= second) {
					if (first % second == 0) {
						resultOfSecond += second;
					}
					second++;
				}
				if(resultOfSecond == second) {
					result = first;
				}
			}
			first++;
		}
		return result;
	}
	public static void main(String[] args) {
		long result = findPrimeFactor(600851475143);
		System.out.println(result);
	}
}
