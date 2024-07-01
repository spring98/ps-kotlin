package jun30

/**
 * https://howudong.tistory.com/106
 */
import kotlin.math.*

data class Item(val weight: Int, val value: Int)
fun main() {
    val n = 4
    val k = 7
//    val items = listOf(Pair(6, 13), Pair(4, 8), Pair(3, 6), Pair(5, 12))
    val items = listOf(Item(6, 13), Item(4, 8), Item(3, 6), Item(5, 12))

    val dp = Array(n+1) { IntArray(k+1) }

//    dp[i][w] = max(dp[i-1][w], Vi + dp[i-1][w-wi])

    for (i in 1..n) {
        for (w in 0..k) {
            val (wi, vi) = items[i-1]

            if (w-wi < 0) {
                dp[i][w] = dp[i-1][w]
            }
            else {
                dp[i][w] = max(dp[i-1][w], vi + dp[i - 1][w - wi])
            }
        }
    }

    println(dp.contentDeepToString())
}

