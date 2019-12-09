package course1.week2.optional

import kotlin.math.max

//You are a given a unimodal array of n distinct elements, meaning that its entries are in increasing
//order up until its maximum element, after which its elements are in decreasing order.
//Give an algorithm to compute the maximum element that runs in O(log n) time.

fun main() {

    //Array of test cases with input and expected output
    val testCases = arrayOf(
        Pair(arrayOf(1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1), 9),
        Pair(arrayOf(8,9,8,7,6,5,4,3,2,1), 9),
        Pair(arrayOf(0), 0),
        Pair(arrayOf(-4,-3,-2,-1,0), 0),
        Pair(arrayOf(-4,-3,-2,-1,0,1,2,3,4,5,6), 6),
        Pair(arrayOf(-1,0), 0),
        Pair(arrayOf(0,1), 1),
        Pair(arrayOf(1,1), 1),
        Pair(arrayOf(6,5,4,3,2,1), 6),
        Pair(arrayOf(4,4,4), 4)
    )

    for(testCase in testCases) {
        var result = findMax(testCase.first)
        if(result == testCase.second) {
            println("Successful test. Input: " + testCase.first.joinToString(",") +
                    ", Output: " + testCase.second)
        } else {
            println("Unsuccessful test. Input: " + testCase.first.joinToString(",") +
                    ", Expected Output: " + testCase.second +
                    ", Actual Output: " + result)
        }
    }

}

fun findMax(x: Array<Int>): Int {

    //Base cases (constant time)
    if(x.size == 1) {
        return x[0]
    } else if(x.size == 2) {
        return max(x[0], x[1])
    }

    //Recursive calls (log(n) time since we split every input in half)
    val n = x.size / 2
    if(x[n] < x[n-1]) {
        return findMax(x.copyOfRange(0, n))
    } else if(x[n] < x[n+1]) {
        return findMax(x.copyOfRange(n + 1, x.size))
    }

    //Another constant base case
    //If we found the max
    return x[n]

}