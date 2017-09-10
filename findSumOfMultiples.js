function findSumOfMultiples (one, two) {
    var MAX = 1000;
    var result = 0;
    var first = one;
    var second = two;
    while (first < 1000) {
        result += first;
        first *= 2;
    }
    while (second < 1000) {
        result += first;
        second *= 2;
    }
    return result;
}
