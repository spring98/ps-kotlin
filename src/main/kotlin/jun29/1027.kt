package jun29

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val towers = readLine().split(" ").map { it.toInt() }
    var answer = 0

    for (i in 0 until n) {
        var localAnswer = 0

        // 기울기가 점점 작아져야 함
        var lastLeftGradient = 1000000001.0
        for (j in i-1 downTo 0) {

            val gradient = (towers[j].toDouble() - towers[i].toDouble()) / (j - i)
            if (gradient < lastLeftGradient) {
                lastLeftGradient = gradient
                localAnswer += 1
            }
        }

        // 기울기가 점점 커져야 함
        var lastRightGradient = -1000000001.0
        for (j in i+1 until n) {
            val gradient = (towers[j].toDouble() - towers[i].toDouble()) / (j - i)
            if (lastRightGradient < gradient) {
                lastRightGradient = gradient
                localAnswer += 1
            }
        }

        answer = max(answer, localAnswer)
    }

    println(answer)
}