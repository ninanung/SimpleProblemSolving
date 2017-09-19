package simplesolve;

public class sumSquareDifference {
	public static int sum(int standard) {
		int sumSquare = 0;
		int squareSum = 0;
		for(int i = 1; i <= standard; i++) {
			sumSquare = sumSquare + (i * i);
		}
		for(int j = 1; j <= standard; j++) {
			squareSum += j;
		}
		squareSum *= squareSum;
		int result = squareSum - sumSquare;
		return result;
	}
	public static void main(String[] args) {
		System.out.print(sum(100));
	}
}
