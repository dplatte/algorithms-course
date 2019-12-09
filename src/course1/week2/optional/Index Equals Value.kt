package course1.week2.optional

//You are given a sorted (from smallest to largest) array A of n distinct integers which can be positive,
//negative, or zero. You want to decide whether or not there is an index i such that A[i] = i.
//Design the fastest algorithm that you can for solving this problem.

fun main() {

    val testCases = arrayOf(
        Pair(arrayOf(0,1,2,3,4,5), true),
        Pair(arrayOf(1,2,3,4,5,6,7,8,9,10), false),
        Pair(arrayOf(-1,0,1,2,3,4,5,6,8), true),
        Pair(arrayOf(0), true),
        Pair(arrayOf(-1,1), true),
        Pair(arrayOf(0,12,13,14,15,16,17,18,19,20,21,22,23,23,24), true),
        Pair(arrayOf(-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8), false),
        Pair(arrayOf(-2,-1,2), true)
    )

    for(testCase in testCases) {
        println(indexEqualsValueExists(testCase.first, 0))
    }
}

fun indexEqualsValueExists(x: Array<Int>, startingIndex: Int): Boolean {

    //println(x.joinToString(","))
    //println("StartingIndex: $startingIndex")

    val n = x.size / 2;

    //println("N: $n")

    val adjustedIndex = n + startingIndex

    //Base cases
    if(x[n] == adjustedIndex) {
        return true;
    } else if(x.size == 1) {
        return false;
    }


    if(x[n] > adjustedIndex) {
        return indexEqualsValueExists(x.copyOfRange(0, n),startingIndex)
    } else {
        return indexEqualsValueExists(x.copyOfRange(n, x.size), adjustedIndex)
    }

}