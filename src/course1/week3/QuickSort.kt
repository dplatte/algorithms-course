package course1.week3

import java.io.File

//Your task is to compute the total number of comparisons used to sort the given input file by QuickSort.
// As you know, the number of comparisons depends on which elements are chosen as pivots,
// so we'll ask you to explore three different pivoting rules.

//You should not count comparisons one-by-one. Rather, when there is a recursive call on a subarray of length m,
// you should simply add m-1 to your running total of comparisons.
// (This is because the pivot element is compared to each of the other m-1 elements in the subarray in this recursive call.)

//For the first part of the programming assignment, you should always use the first element of the array as the pivot element.

//Compute the number of comparisons (as in Problem 1), always using the final element of the given array as the pivot element.

//Compute the number of comparisons (as in Problem 1), using the "median-of-three" pivot rule.
// [The primary motivation behind this rule is to do a little bit of extra work to get much better performance on input arrays
// that are nearly sorted or reverse sorted.]
// In more detail, you should choose the pivot as follows.
// Consider the first, middle, and final elements of the given array.
// (If the array has odd length it should be clear what the "middle" element is;
// for an array with even length 2k, use the k{th} element as the "middle" element.

var count = 0

enum class PivotType {
    FIRST,
    LAST,
    MEDIAN_OF_THREE
}

fun main() {

    //var test1 = arrayOf(8,7,5,3,4,2,6,1)

    //Defined test file is a text file with an integer on each line 1-100000 in some non-sorted order
    val fileLines = File("C:\\Users\\dusti\\Downloads\\QuickSort.txt").readLines()
    //Initialize test arrays with size matching test file elements
    val test1 = Array(fileLines.size){0}
    val test2 = Array(fileLines.size){0}
    val test3 = Array(fileLines.size){0}

    //Populate the arrays from the test file
    for((index, value) in fileLines.withIndex()) {
        test1[index] = value.toInt()
        test2[index] = value.toInt()
        test3[index] = value.toInt()
    }

    //Test1: First element as pivot
    quickSort(test1, 0, test1.size - 1, PivotType.FIRST)
    println(count)

    //Reset count
    count = 0
    //Test2: Last element as pivot
    quickSort(test2, 0, test2.size - 1, PivotType.LAST)
    println(count)

    //Reset count
    count = 0
    //Test3: Median of 3 (first, middle, last) element as pivot
    quickSort(test3, 0, test3.size - 1, PivotType.MEDIAN_OF_THREE)
    println(count)

}

fun quickSort(x: Array<Int>, l: Int, r: Int, type: PivotType) {

    if(l > r) {
        return
    }

    count += (r - l)

    if(type == PivotType.LAST) {
        //Swap first and last elements
        val temp = x[l]
        x[l] = x[r]
        x[r] = temp
    } else if(type == PivotType.MEDIAN_OF_THREE) {
        val a = x[l]
        val b = x[(r + l) / 2]
        val c = x[r]
        if ((a < b && b < c) || (c < b && b < a)) {
            //Middle element is chosen as pivot
            //Swap middle and first
            x[l] = b
            x[(r + l) / 2] = a
        } else if ((b < c && c < a) || (a < c && c < b)) {
            //Last element is chosen as pivot
            //Swap last and first
            x[l] = c
            x[r] = a
        }
        //No need to do anything if first element is already the median of the three
    }

    val j = partition(x, l, r)

    quickSort(x, l, j - 1, type)
    quickSort(x, j + 1, r, type)


}

fun partition(x: Array<Int>, l: Int, r: Int): Int {

    val p = x[l]
    var i = l + 1

    for(j in (l+1)..r) {
        if(x[j] < p) {
            val temp = x[j]
            x[j] = x[i]
            x[i] = temp
            i++
        }
    }

    //Place the pivot element in its correct position
    val temp = x[l]
    x[l] = x[i-1]
    x[i-1] = temp

    return i - 1
}