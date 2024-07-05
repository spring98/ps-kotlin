package jul05.goorm

import java.util.Scanner
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = LongArray(n+1)
    val inputArr = readLine().split(" ").map { it.toInt() }

    for (i in 0 until n) {
        arr[i+1] = inputArr[i].toLong()
    }

    for(i in 1 until n+1) {
        arr[i] = arr[i] + arr[i-1]
    }

    for (i in 0 until m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        val result = arr[b] - arr[a-1]

        if (result > 0) {
            println("+$result")
        }

        if (result < 0) {
            println("$result")
        }

        if (result.toInt() == 0) {
            println("0")
        }
    }
}