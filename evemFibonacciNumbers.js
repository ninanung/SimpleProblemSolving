function evenFibonacciNumbers () {
  	var first = 1;
	var second = 2;
	var result = 0;
	var sumOfEven = 2;
	while(result < 2000000) {
		result = first + second;
		first = second;
		second = result;
		if (second % 2 == 0) {
			sumOfEven += second;
		}
  	}
	return sumOfEven;
}
