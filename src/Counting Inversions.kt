import java.io.File

fun main() {

    //Defined test file is a text file with an integer on each line 1-100000 in some non-sorted order
    val fileLines = File("C:\\Users\\Dustin\\Downloads\\IntegerArray.txt").readLines()
    //Initialize array with size matching test file elements
    val test1 = Array(fileLines.size){0}

    //Populate the array from the test file
    for((index, value) in fileLines.withIndex()) {
        test1[index] = value.toInt()
    }

    //Call method on test data
    val inversionsPair = countInversions(test1)

    //Print results from method and test data
    println(inversionsPair.first)

}

//Recursive method
private fun countInversions(a: Array<Int>): Pair<Long, Array<Int>> {
    //Base Case
    if(a.size == 1) {
        return Pair(0, a)
    }

    //Recurse through left and right, keeping track of inversion counts
    val (leftCount, leftArray) = countInversions(a.copyOfRange(0, a.size / 2))
    val (rightCount, rightArray) = countInversions(a.copyOfRange(a.size / 2, a.size))

    //Merge and count split inversions
    val (splitCount, splitArray) = mergeSortAndCountInversions(leftArray, rightArray)

    //Add all inversion counts and return
    return Pair(leftCount + rightCount + splitCount, splitArray)

}

//Merge method
private fun mergeSortAndCountInversions(b: Array<Int>, c: Array<Int>): Pair<Long, Array<Int>> {
    val d = Array(b.size + c.size){0}
    var count = 0L
    var i = 0
    var j = 0

    //Loop through entire output array
    for(k in 0 until d.size) {
        when {
            //Add the rest of the left array if right is complete
            j == c.size -> {
                d[k] = b[i]
                i++
            }
            //Add the rest of the right array if left is complete
            i == b.size -> {
                d[k] = c[j]
                j++
            }
            //Add element from left if it is less than first right element
            b[i] < c[j] -> {
                d[k] = b[i]
                i++
            }
            //Add element from right if it is less than first left element
            //Increase count because right < left means an inversion is present
            //Count should increase by however many elements are remaining in left array
            //because all of them will have inversions for this element in the right array
            else -> {
                d[k] = c[j]
                count += b.size - i
                j++
            }
        }
    }

    return Pair(count, d)
}