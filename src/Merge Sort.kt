import java.util.Arrays

fun main() {

    val test1 = arrayOf(7,6,5,4,3,2,1)

    val result = mergeSort(test1)

    println(Arrays.toString(result))

}


private fun mergeSort(a: Array<Int>): Array<Int> {
    if(a.size == 1) {
        return a
    }

    val left = mergeSort(a.copyOfRange(0, a.size / 2))
    val right = mergeSort(a.copyOfRange(a.size / 2, a.size))

    return merge(left, right)

}

private fun merge(b: Array<Int>, c: Array<Int>): Array<Int> {
    val d = Array(b.size + c.size){0}

    var i = 0
    var j = 0

    for(k in 0 until d.size) {
        when {
            j == c.size -> {
                d[k] = b[i]
                i++
            }
            i == b.size -> {
                d[k] = c[j]
                j++
            }
            b[i] < c[j] -> {
                d[k] = b[i]
                i++
            }
            else -> {
                d[k] = c[j]
                j++
            }
        }

    }

    return d
}